/** 2020. 7. 21. 오전 9:51:20
 * @author ventulus95
 */
package codeBaekJoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;

public class No5430_AC {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		for(int test=0; test<T; test++){
			String p = br.readLine();
			int n = Integer.parseInt(br.readLine());
			String arr = br.readLine();
			arr = arr.substring(1, arr.length()-1);
			String[] temp = arr.split(",");
			Deque<String> deq = new ArrayDeque<>();
			for(int i=0; i<n; i++){
				deq.add(temp[i]);
			}
			int curr = 1; // 1 :정 -1: 반 
			boolean error = false;
			for(int i =0; i<p.length(); i++){
				char command = p.charAt(i);
				if(command=='R'){
					curr *= -1;
				}
				else{
					if(curr == -1){
						if(!deq.isEmpty()&& n!=0){
							deq.removeLast();
							n--;
						}
						else{
							System.out.println("error");
							error = true;
							break;
						}
					}
					else{
						if(!deq.isEmpty() && n!=0){
							deq.pop();
							n--;
						}
						else{
							System.out.println("error");
							error = true;
							break;
						}
					}
				}
			}
			if(error==false && n!=0){
				System.out.print("[");
				if(curr==1){
					for(int i=0; i<n-1; i++){
						System.out.print(deq.pop());
						System.out.print(",");
					}
					System.out.print(deq.pop());
				}
				else{
					for(int i=0; i<n-1; i++){
						System.out.print(deq.removeLast());
						System.out.print(",");
					}
					System.out.print(deq.removeLast());

				}
				System.out.println("]");
			}
			if(error==false && n==0){
				System.out.print("[");
				System.out.println("]");
			}
		}
	}

}
