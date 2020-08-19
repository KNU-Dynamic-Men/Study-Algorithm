# 1182 - 부분수열의 합

## 1. 개요 

https://www.acmicpc.net/problem/1182

## 2. 코드

```java
/** 2020. 8. 17. 오후 9:59:53
 * @author ventulus95
 */
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	
	static int max = 1, s, cnt=0, n;
	static int arr[];
	static boolean visited[];
	
	static void dfs(int idx, int depth){
		if(depth == max){
			int sum = 0;
			for(int i =0; i<n; i++){
				if(visited[i]==true){
					 sum += arr[i];
//					 System.out.print(arr[i]+" ");
				}
			}
//			System.out.println("sum: "+sum);
			if(sum==s){
				cnt++;
			}
			return;
		}
		for(int i=idx; i<n; i++){
			if(visited[i] == true)
				continue;
			visited[i] = true;
			dfs(i, depth+1);
			visited[i] = false;
		}
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String t = br.readLine();
		n = Integer.parseInt(t.split(" ")[0]);
		s = Integer.parseInt(t.split(" ")[1]);
		String sub = br.readLine();
		StringTokenizer st = new StringTokenizer(sub, " ");
		arr = new int [n];
		for(int i =0; i<n; i++){
			arr[i] = Integer.parseInt(st.nextToken());
		}
		visited = new boolean[n];
		for(int i=1; i<=n; i++){
			dfs(0,0);
			max++;
			Arrays.fill(visited, false);
		}
		System.out.println(cnt);
	}
}
```

## 3. 풀이

이 부분 수열을 모두 더해보는 경우는 -7+5 나 5+-7은 결과적으로 같은 값을 가지게 될테니 순열은 아니고 **조합**으로 문제를 풀어야함. 그러면 조합을 알고리즘화하면 된다. 

조합 알고리즘 자체는 쉽다. (차후에 조합과 순열에 대해서 정리할 심산...)

```java
static void dfs(int idx, int depth){
		if(depth == max){
			int sum = 0;
			for(int i =0; i<n; i++){
				if(visited[i]==true){
					 sum += arr[i];
//					 System.out.print(arr[i]+" ");
				}
			}
//			System.out.println("sum: "+sum);
			if(sum==s){
				cnt++;
			}
			return;
		}
		for(int i=idx; i<n; i++){
			if(visited[i] == true)
				continue;
			visited[i] = true;
			dfs(i, depth+1);
			visited[i] = false;
		}
	}
```

몇가지를 조합할지는 depth이 결정하고 그 깊이가 max값에 다 다르면(조합이 완성된 경우라면), 그 합이 얼마인지 확인한 후에  s와 같으면 cnt를 늘리는 방식.

이런식으로 max값을 늘려가면서 전체를 모두 뽑는 경우까지 해보게 하면 된다. 그러면서 나온 모든 경우를  다 더해보면서 수열의 합을 체킹하는 방식으로 문제 풀이

## 4. 결과

<img width="1141" alt="스크린샷 2020-08-19 오전 11 51 18" src="https://user-images.githubusercontent.com/17822723/90586519-5addde80-e212-11ea-9b27-92cb06cadc47.png">