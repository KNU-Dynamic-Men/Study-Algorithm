# 2447 - 별 찍기 - 10

## 1. 개요

https://www.acmicpc.net/problem/2447

## 2. 코드

```java
/** 2020. 8. 18. 오후 7:20:28
 * @author ventulus95
 */
package codeBaekJoon;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;

public class No2447_star11 {
	
	static char map[][];
	
	static void star(int y, int x, int size){
		if(size==1){
			map[y][x] = '*';
			return;
		}
		int cnt = 0;
		for(int i=0; i<3; i++){
			for(int j=0; j<3; j++){
				if(i==1 && j==1)
					continue;
				star(y+i*(size/3),x+(j*size/3),size/3);
			}
		}
	}

	public static void main(String[] args)throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		int n = Integer.parseInt(br.readLine());
		map = new char[n][n];
		for(int i =0; i<n; i++){
			Arrays.fill(map[i], ' ');
		}
		star(0,0,n);
		for(int i=0; i<n; i++){
			for(int j=0; j<n; j++){
				bw.append(map[i][j]);
			}
			bw.newLine();
		}
		bw.flush();
		bw.close();
	}
}

```

## 3. 풀이 방식

간단하게 **분할 정복식**으로 문제를 풀어야하겠다는 생각은 했었음. 문제는 그것을 여기에 어떤식으로 적용해야할지를 정확하게는 몰라서 Z를 참조해서 분할정복식으로 문제를 해결하는 방식을 취함.

```java
if(size==1){
			map[y][x] = '*';
			return;
		}
```

사이즈가 1이 될때 *을 찍는 방식으로 만드는게 가장 이상적일 것 같아서 구성했고, 문제는 이걸 9개의 섹션을 어떤식으로 순회를 해야하는지가 제일 큰 문제. 처음에는 x,y좌표를 받고 size/3을 한것을 받아서 9방향을 도는게 좋다고생각했으나, `array boundary Error`의 발생으로 다른 방식을 채택해야했었음.

```java
int cnt = 0;
		for(int i=0; i<3; i++){
			for(int j=0; j<3; j++){
				if(i==1 && j==1)
					continue;
				star(y+i*(size/3),x+(j*size/3),size/3);
			}
		}
```

9개의 방향으로 순회하는 것이 중요한거지 그 좌표를 통짜로 더하는것보다는 오히려 size/3한거를 곱해서 부분부분 더하는게 더 좋은 방향. 그래서 가운데 부분을 제외하기위해서 i==1&& j==1 를 통해서 해결

## 4. Tip!

근데... 시간초과 남. why? 그냥 Sysout을 통한 print로 하다보니까 이게 속도가 엄청 느려짐. 즉, 다른 방식으로 풀필요가 있음. Java에서 input Output 속도를 극단적으로 높히기위해서는 객체생성시간 자체를 줄이면 됨. 결국 속도차이가 생기는 이유는 Scanner를 통해서 객체를 만들어지는 시간때문에 시간이 엄청 걸리는것.

BufferedWriter를 통해서 Append를 통해서 Flush하는 방식으로 문제를 풀면 문제 해결할 수 있음. 

<img width="843" alt="스크린샷 2020-08-19 오전 11 38 00" src="https://user-images.githubusercontent.com/17822723/90585895-e3f41600-e210-11ea-8f50-f91881dc6125.png">