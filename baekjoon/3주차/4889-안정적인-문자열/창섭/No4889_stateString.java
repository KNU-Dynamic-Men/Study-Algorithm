/** 2020. 7. 22. 오전 12:02:01
 * @author ventulus95
 */
package codeBaekJoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Stack;

public class No4889_stateString {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String tc = br.readLine();
		int num = 1;
		while(tc.charAt(0)!='-'){
			int cnt=0;
			int back =0;
			Stack<Character> stack = new  Stack<>();
			for(int i =0; i<tc.length(); i++){
				char temp = tc.charAt(i);
				if(temp == '{'){
					stack.add('{');
				}
				else{
					if(!stack.isEmpty()){
						stack.pop();
					}
					else{
						cnt++;
						stack.add('{');
					}
				}
			}
			cnt += stack.size()/2;
			
			System.out.println(num+". "+cnt);
			tc = br.readLine();
			num++;
		}

	}

}
