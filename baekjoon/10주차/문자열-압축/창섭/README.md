# **2020 카카오 블라인드 1차 1번 - 문자열 압축**

## **1. 개요**

[https://programmers.co.kr/learn/courses/30/lessons/60057](https://programmers.co.kr/learn/courses/30/lessons/60057)

## **2. 코드**

```java
/** 2020. 9. 8. 오후 11:26:08
 * @author ventulus95
 */
package kakaoBilnd2020;

public class A_rewind {

	public static void main(String[] args) {
		String tc ="xababcdcdababcdcd";
		int min = tc.length()/2;
		int ans = tc.length();
		for(int i=1; i<=min; i++){
			StringBuilder sb = new StringBuilder();
			String curr = tc.substring(0,i);
			int cnt = 1;
			int checker = 0;
			for(int idx =i; idx<=tc.length(); idx=idx+i){
				checker = idx;
				if(idx+i<=tc.length()){
					String cut = tc.substring(idx, idx+i);
					if(!curr.equals(cut)){
						if(cnt==1)
							sb.append(curr);
						else{
							sb.append(cnt);
							sb.append(curr);
							cnt = 1;
						}
						curr = cut;
					}
					else{
						cnt++;
					}
				}
			}
			if(cnt==1)
				sb.append(curr);
			else{
				sb.append(cnt);
				sb.append(curr);
			}
			if(checker+i>tc.length()){
				sb.append(tc.substring(checker));
			}
			ans = Math.min(sb.toString().length(), ans); 
		}
		System.out.println(ans);
	}

}

```

## **3. 설명**

1. 일단 자를때 가능한 경우는 전체 주어진 문자열의 길이의 절반 -> 왜냐면 절반 이상부터는 그 문자열이 같을 수 있는 경우 자체가 없기 때문
2.  자르는 경우 1부터 문자열 길이 절반까지의 가능한 경우 모두 채킹
3. 기준 문자열 자른 문자열보면서 같으면 Cnt 늘려주고 안같으면 기준 문자열 출력하고 자른 문자열을 기준문자열로 만듬.
    1. 이러면 간혹 자르는 경우가 문자열의 길이를 넘어서서 자르는 경우를 넘어서는 경우가 존재할 수 도 있으니까 초과분에 대해서는 checker로 체크해주고 나머지 부분을 붙혀주는 방식으로 처리.
4. 이러고난 뒤에 sb의 길이 비교해서 최소인 경우 체크

