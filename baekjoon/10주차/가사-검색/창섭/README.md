# **2020 카카오 블라인드 1차 4번 - 가사 검색**

## **1. 개요**

[https://programmers.co.kr/learn/courses/30/lessons/60060](https://programmers.co.kr/learn/courses/30/lessons/60060)

## **2. 코드**

```java
/** 2020. 9. 9. 오전 11:06:44
 * @author ventulus95
 */
package kakaoBilnd2020;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class D {

	public static void main(String[] args) {
		String[] words={"frodo", "front", "frost", "frozen", "frame", "kakao"};
//		String[] queries={"fro??", "????o", "fr???", "fro???", "pro?"};
		String[] queries={"??????", "fro??", "fro??", "fro???", "pro?"};
		int[] answer = new int[queries.length];
		Set<String> mySet = new HashSet<>(Arrays.asList(queries));
		String[] k = new String[mySet.size()];
 		k = mySet.toArray(k);
		int[] querycnt = new int[k.length];
		Arrays.fill(querycnt, -1);
		for(int i =0; i<queries.length; i++){
			String query = queries[i];
			boolean isDup = false;
			int dupidx = -999;
			for(int t=0; t<k.length; t++){
				if(k[t].equals(query)){
					if(querycnt[t]>=0){
						answer[i] = querycnt[t];
						isDup = true;
					}
					else{
						dupidx = t;
					}
				}
			}
			if(isDup==false){
				boolean isPre = true;
				if(query.charAt(0) == '?')
					isPre = false;
				String fix = query.replace("?", "");
				if(isPre == true){
					for(int j =0; j<words.length; j++){
						if(words[j].startsWith(fix) && query.length()==words[j].length())
							answer[i]++;
					}
				}
				else{
					for(int j =0; j<words.length; j++){
						if(words[j].endsWith(fix)&& query.length()==words[j].length())
							answer[i]++;
					}
				}
				if(dupidx >0)
					querycnt[dupidx] = answer[i];
			}
		}
		Stream<Object> qqqq = Arrays.stream(mySet.toArray()); 
		
		IntStream s = Arrays.stream(answer);
		s.forEach(System.out::println);
	}

}
```

## **3. 부연설명**

1. 이렇게 풀면 효율성 한개 빼고 다 맞음 -> 효율성 다 맞출려면 trie 알고리즘 써야되는데 굳이... 그렇게까지 안해도 80점 먹고 가는거면 나쁘지 않다고 봄 ㅋㅅㅋ

2. 처음에는 이렇게도 풀었음

    ```java
    class Solution {
        public int[] solution(String[] words, String[] queries) {
            int[] answer = new int[queries.length];
    		for(int i =0; i<queries.length; i++){
    			String query = queries[i];
    			boolean isPre = true;
    			if(query.charAt(0) == '?')
    				isPre = false;
    			String fix = query.replace("?", "");
    			if(isPre == true){
    				for(int j =0; j<words.length; j++){
    					if(words[j].startsWith(fix) && query.length()==words[j].length())
    						answer[i]++;
    				}
    			}
    			else{
    				for(int j =0; j<words.length; j++){
    					if(words[j].endsWith(fix)&& query.length()==words[j].length())
    						answer[i]++;
                        if(fix.equals("")&& query.length()==words[j].length())
    						answer[i]++;
    				}
    			}
    		}
            return answer;
        }
    }
    ```

    이러면 query중복에 대해서는 처리가 안되서 효율성 123에 대해서는 무조건 시간 초과 발생

## **4. 코드설명**

1. 일단 쿼리 중복을 막기위해서는 Query들을 set으로 처리해서 중복제거후 String 배열로 뽑아냄
2. 그리고 매 쿼리의 경우마다 중복이 있는지 없는지를 체크해서 중복이 있으면 쿼리중복이 있는 값을 넣어버리고 끝
3. 쿼리 중복이 없는 경우나 중복이 있긴하되 그 쿼리가 처음 나온 경우에는 쿼리를 통한 단어 찾기 실행
   1. 쿼리에서 앞에 ?가 있는지 여부를 따져서 어두인지 어미인지를 판단.
   2. 그리고 쿼리의 ?를 제거해서 fix에 저장
   3. 그후 자바의 Startwith나 endwith를 통해서 앞 혹은 뒤에 있는지 없는지 체크와 함께 쿼리 전체의 길이랑 단어 길이랑도 비교해야함.
   4. 맞으면 answer값 늘리기
4. 그리고 중복될 경우가 있을 수도 있으니 중복 idx를 미리 저장한 후에 그 값이 -1이면 answer값이랑 치환해서 중복 값 미리 처리해버리기