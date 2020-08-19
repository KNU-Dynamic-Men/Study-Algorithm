/** 2020. 8. 18. 오후 11:36:01
 * @author ventulus95
 */
package codeBaekJoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class No1629_multiple {
	
	static long pow(int a, int b, int c){
		if(b==1){
			return a%c;
		}
		long val = pow(a, b/2, c);
		val = val*val%c;
		if(b%2==0){
			return val;
		}
		return val*a%c;
	}

	public static void main(String[] args)throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String t = br.readLine();
		StringTokenizer st = new StringTokenizer(t, " ");
		int a = Integer.parseInt(st.nextToken());
		int b = Integer.parseInt(st.nextToken());
		int c = Integer.parseInt(st.nextToken());
		System.out.println(pow(a,b,c));
	}

}
 