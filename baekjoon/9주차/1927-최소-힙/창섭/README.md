## BOJ 1927번 최소 힙

## 1. 개요

https://www.acmicpc.net/problem/1927

## 2. 코드

```java
/** 2020. 8. 29. 오전 12:55:22
 * @author ventulus95
 */
package codeBaekJoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;

public class No1927_minHeap {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		PriorityQueue<Integer> q = new PriorityQueue<Integer>();
		while(n>0){
			int temp = Integer.parseInt(br.readLine());
			if(temp == 0 ){
				if(!q.isEmpty()){
					System.out.println(q.poll());
				}
				else{
					System.out.println(0);
				}
				
			}
			else{
				q.add(temp);
			}
			n--;
		}

	}

}

```

## 3. 풀이 과정

- 우선순위 큐를 이용해 해결
