# **2020 카카오 블라인드 1차 3번 - 자물쇠와 열쇠**

## **1. 개요**

[https://programmers.co.kr/learn/courses/30/lessons/60059](https://programmers.co.kr/learn/courses/30/lessons/60059)

## **2. 코드**

```java
/** 2020. 9. 9. 오후 1:17:24
 * @author ventulus95
 */
package kakaoBilnd2020;

import java.util.Arrays;

public class C {

	public static int[][] rotate(int [][] key){
		int len = key.length;
		int[][] temp = new int[len][len];
		for(int i=0; i<len; i++) {
			for(int j=0; j<len; j++) {
				temp[i][j] = key[len-j-1][i];
			}
		}
		return temp;
	}

	public static void main(String[] args) {
		int[][] key = {{0, 0, 0}, {1, 0, 0}, {0, 1, 1}};
		int[][] lock = {{1, 1, 1}, {1, 1, 0}, {1, 0, 1}};
		int km = key.length;
		int ln = lock.length;	
		int [][] lockmap = new int[2*km+ln-2][2*km+ln-2];
		boolean answer = false;
		for(int i =0; i<4; i++){
			for(int sr=0; sr<km+ln-1; sr++){
				for(int sc=0; sc<km+ln-1; sc++){
					for(int aaa=0; aaa<2*km+ln-2; aaa++){
						Arrays.fill(lockmap[aaa], 0);
					}
					for(int aaa=km-1; aaa<=km+ln-2; aaa++){
						for(int bbb=km-1; bbb<=km+ln-2; bbb++){
							lockmap[aaa][bbb] = lock[aaa-km+1][bbb-km+1];
						}
					} // 매번 맵초기화.
					boolean isFlag = false; //무조건 안되는 경우
					for(int kr=sr; kr<sr+km; kr++){
						for(int kc=sc; kc<sc+km; kc++){
							if(key[kr-sr][kc-sc] ==1 && lockmap[kr][kc]==1 ){
								isFlag = true;
							}
							if(key[kr-sr][kc-sc] ==1 && lockmap[kr][kc]==0 ){
								lockmap[kr][kc] = 1;
							}
							if(isFlag == true){
								break;
							}
						}//그안에서순
						if(isFlag == true){
							break;
						}
					}
					boolean isCheck = false;
					if(isFlag == true){
						isCheck = true;
					}
					for(int a=km-1; a<=km+ln-2; a++){
						for(int b=km-1; b<=km+ln-2; b++){
							if(lockmap[a][b] == 0){
								isCheck = true;
							}
							if(isCheck == true){
								break;
							}
						}
						if(isCheck==true){
							break;
						}
					}// 요거 맵전체 순회하는 거 첫 위치 잡아줌.
					if(isCheck == false){
						answer = true;
					}
				}
			}
			key = rotate(key);
		} //4방향에 대해서 해야함.
		System.out.println(answer);
	}
}
```

## **3. 설명**

1. 배열을 크게 키운다.  [2*km+ln-2][2*km+ln-2]; 대충 이정도로 키우면 됨.
2. 크게 키운 배열을 일대일로 다 대응해보고 조건) Lock의 홈이 다 끼워지고, 홈 이외 지점에서 키의 1인 지점과 만나면 불가능 처리
3. 키를 돌린다.
4. 2-3을 반복한다.
