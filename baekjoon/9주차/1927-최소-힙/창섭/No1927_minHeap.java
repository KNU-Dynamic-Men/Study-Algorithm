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
