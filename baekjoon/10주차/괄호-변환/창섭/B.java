/** 2020. 5. 13. 오후 10:40:39
 * @author ventulus95
 * 소요시간: 1:05:19 
 */
package kakaoBilnd2020;

import java.util.Stack;

public class B {
	
	public static String maker(String m){
		StringBuilder sb = new StringBuilder();
		if(m.length() == 0){
			return "";
		}
		Stack<Character> balance = new Stack<>();
		int bcount = 0;
		int ucount = 0;
		int check = 0;
		for(int i=0; i<m.length(); i++){
			if(m.charAt(i) == '('){
				bcount++;
			}
			if(m.charAt(i) == ')'){
				ucount++;
			}
			if(bcount==ucount){
				check = i;
				break;
			}
		}
		String u = m.substring(0, check+1);
		String v = m.substring(check+1);
		boolean ubalance = true;
		for(int i =0; i<u.length(); i++){
			if(u.charAt(i) == '('){
				balance.add('(');
			}
			else{
				if(!balance.isEmpty()){
					balance.pop();
				}
				else{
					ubalance = false;
					break;
				}
			}
		}
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
			String p = "))((";
			System.out.println(maker(p));
			
	}

}
