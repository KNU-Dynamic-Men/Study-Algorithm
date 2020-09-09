# **2020 카카오 블라인드 1차 2번 - 괄호 변환**

## **1. 개요**

[https://programmers.co.kr/learn/courses/30/lessons/60058](https://programmers.co.kr/learn/courses/30/lessons/60058)

## **2. 코드**

```java
/** 2020. 9. 9. 오전 12:06:16
 * @author ventulus95
 */
package kakaoBilnd2020;

import java.util.Stack;

public class B_rewind {
	
	 public static String maker(String m){
			StringBuilder sb = new StringBuilder();
			if(m.length() == 0){
				return "";
			}
			Stack<Character> balance = new Stack<>();
			int bcount = 0;
			int ucount = 0;
			int check = 0;
			boolean ubalance = true;
			for(int i=0; i<m.length(); i++){
				if(m.charAt(i) == '('){
					bcount++;
					balance.add('(');
				}
				if(m.charAt(i) == ')'){
					ucount++;
					if(!balance.isEmpty()){
						balance.pop();
					}
					else{
						ubalance = false;
					}
				}
				if(bcount==ucount){
					check = i;
					break;
				}
			}
			String u = m.substring(0, check+1);
			String v = m.substring(check+1);
			if(ubalance == true){ 
				sb.append(u);
				sb.append(maker(v));
				return sb.toString();
			} // 올바른 경우이면,
			else{
				sb.append("(");
				sb.append(maker(v));
				sb.append(")");
				u = u.substring(1, u.length()-1);
				for(int i=0; i<u.length(); i++){
					if(u.charAt(i)=='('){
						sb.append(")");
					}
					else{
						sb.append("(");
					}
				}
				return sb.toString();
			}//올바른 경우가 아니면,
		}

	public static void main(String[] args) {
		String p ="()))((()";
		System.out.println(maker(p));
	}

}

```

## **3. 설명**

1. 문제에 나와 있는 안내대로 알고리즘을 작성하면 된다.
2. 예전보다 코드량을 좀더 줄였는데,  올바른 괄호와 균형잡힌 괄호를 동시에 처리할 수 있겠다는 생각이 들어서 처리함
   1. 일단 "(" 과 ")"  갯수 일치를 종료조건으로 두고 
   2. 올바른지는 stack을 통해서 처리
