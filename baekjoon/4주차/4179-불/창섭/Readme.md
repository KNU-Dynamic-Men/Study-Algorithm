# 불! 

## 1. 개요

https://www.acmicpc.net/problem/4179

## 2. 코드

```java
/** 2020. 7. 29. 오전 9:54:52
 * @author ventulus95
 */

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

	static int[][] getMap, getVisited;
	static int[] getDx = {-1,0,1,0};
	static int[] getDy = {0,1,0,-1};

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String tc = br.readLine();
		StringTokenizer st = new StringTokenizer (tc , " ");
		int getR = Integer.parseInt(st.nextToken());
		int getC = Integer.parseInt(st.nextToken());
		getMap = new int[getR][getC];
		getVisited = new int[getR][getC];
		Queue<Node4179> jq = new LinkedList<>();
		Queue<Node4179> fq = new LinkedList<>();
		for(int i=0; i<getR; i++){
			String t = br.readLine();
			for(int j=0; j<getC; j++){
				char temp = t.charAt(j);
				if(temp=='#'){
					getMap[i][j] = -1;
				}
				else if (temp=='J'){
					getMap[i][j] = 1;
					jq.add(new Node4179(i,j));
				}
				else if(temp == 'F'){
					getMap[i][j] = -2;
					fq.add(new Node4179(i, j));
				}
				else{
					getMap[i][j] = 0;
				}
			}
		}
		int answer = 0;
		while(true){
			answer++;
			int fs  = fq.size();
			while(fs>0){
				fs--;
				Node4179 node = fq.poll();
				int y = node.y;
				int x = node.x;
				for(int i=0; i<4; i++){
					if (x+getDx[i] >= 0 && x+getDx[i] < getC && y+getDy[i]> 0 && y+getDy[i] < getR){
						if(getMap[y+getDy[i]][x+getDx[i]] >=0){
							fq.add(new Node4179(y+getDy[i], x+getDx[i]));
							getMap[y+getDy[i]][x+getDx[i]] = -2;
						}
					}
				}
			}
			int js = jq.size();
			while(js>0){
				js--;
				Node4179 node = jq.poll();
				int y = node.y;
				int x = node.x;
				for(int i=0; i<4; i++){
					if (x+getDx[i] < 0 || x+getDx[i] >= getC || y+getDy[i]< 0 || y+getDy[i] >= getR){
						System.out.println(answer);
						return;
					}
					if(getMap[y+getDy[i]][x+getDx[i]] ==0){
						jq.add(new Node4179(y+getDy[i], x+getDx[i]));
						getMap[y+getDy[i]][x+getDx[i]] = 1;
					}
				}
			}
			if(jq.isEmpty()){
				System.out.println("IMPOSSIBLE");
				return;
			}
		}
	}
}

class Node4179{
	int x,y;

	public Node4179(int y, int x ){
		this.x = x;
		this.y = y;
	}
}
```

## 3. 설명

이런 문제에서 결국 중요한 포인트는 **`"같은 시점에 불과 사람을 동시에 움직이는 것"`**이 가장 중요하다. 결국 이문제에서 핵심으로 문제를 풀려면 불과 사람이 동시간에 움직이여야 한다는 조건이 붙는다.

그 조건을 만족시키기 위해서 결국 할 수 있는 방법을 나는 영 몰라서, 큐 하나에 넣고 Map위치에 불이 있는지를 보고 파악하려고 했으나,
다음과 같은 예외가 발생한다. J가 움직였는데 그 위를 불이 움직여서 실제로는 J는 못가는 위치지만, 큐에서는 J가 움직이는 값이 들어있는 상황이 된다. 그래서 실제로는 사람이 움직일 수 없는데 사람이 들어가 있는 상황이라 이 큐에서 사람이 들어가 있는 위치는 중간에 뺄수가 없다. 그래서  map에 적혀있는 그 불인지 사람인지를 판단해서 처리하려고 하니, 사람 위치에 불이 움직이는 경우라던가 불위치에 사람이 움직이는 경우가 발생할 수 도 있다.  큐하나에 처리하는 방식은 **내 머리에서 만큼은 불가능하다.** 실제로 뭐 두개를 분류할 수 있는 큰 조건을 생각해면 가능할지도 모르겠지만

그럼 두개의 큐를 써야겠다는 생각까지는 도출했으나 이 두개의 큐를 동시에 진행시키기 위해서 내가 가진 두개의 큐를 `while(!q.isEmpty())` 를 통해서 구현되는 방법에서는 두 가지의 큐를 동시간때에 같은 갯수의 빼는 것은 불가능한 건 아니지만, 실제로 구현 난이도가 빡세고 귀찮았다. 
참고로 여담이지만, 이문제와 비슷한 로직을 가진 대부분의 문제들을 여기쯤에서 막혔다. 여기서 더 머리가 안굴러가서 도저히 문제가 안풀렸다.

https://ssu-gongdoli.tistory.com/16

여기서 큰 힌트를 얻어서 문제 해결을 했는데 두가지의 큐를 동시간에 들어간 같은 갯수만큼 빼는 것은 예상보다 쉽다.

```java
int answer = 0;
		while(true){
			answer++;
			int fs  = fq.size();
			while(fs>0){
				fs--;
				Node4179 node = fq.poll();
			}
			int js = jq.size();
			while(js>0){
				js--;
				Node4179 node = jq.poll();
			}
		}
```

애초에 두개의 큐에서 첫 들어간 사이즈만큼만 빼는 것이다. 이러면 한번 행동(그러니까 여기서는 1분당)당 불과 사람이 움직일 수 있는 정도를 강제할 수 있다. 로직상 한번당 움직일수 있는 불의 갯수를 전부 다 빼고 새로 들어간 (이동한 불이나 사람) 것에 대해서는 영향을 줄수가 없다. 이미 큐의 크기가 지정될때는 그 큐안에는 움직이고 난 다음의 값들이 들어있고 새로 움직이는 값들은 들어가있지 않은 상태라고 할 수 있다.

이런식으로 해주면 결국 불이 움직이는 순간과 내가 움직이는 순간이 일치된다. 그러면 시뮬레이션처럼 문제를 해결 할 수 있다

이 문제의 해결 조건인 jq의 다음 움직이는 위치가 사각형의 외부로 나가는 경우 answer를 출력하면 되고, jq가 비어있으면 사람이 더 이상 움직일 수 없다는 뜻으므로 IMPOSSIBLE를 출력해주면된다.

## 4. 결과

![스크린샷 2020-07-29 오후 5 49 24](https://user-images.githubusercontent.com/17822723/88778814-eb9d3d80-d1c3-11ea-9ddc-45406b5416c7.png)

