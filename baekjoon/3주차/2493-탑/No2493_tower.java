/** 2020. 7. 22. 오전 11:07:57
 * @author ventulus95
 */
package codeBaekJoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class No2493_tower {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		String towers = br.readLine();
		StringTokenizer st = new StringTokenizer(towers, " ");
		int arr[] = new int[T+1];
		int r[] = new int[T+1];
 		for(int i =1; i<=T; i++){
			arr[i] = Integer.parseInt(st.nextToken());
		}
		Stack<Integer> tower = new Stack<>();
		Stack<Integer> result = new Stack<>();
		for(int i =T; i>=0; i-- ){
			while( !tower.isEmpty() && tower.peek() < arr[i]){
				tower.pop();
				int aa =result.pop();
				r[aa] = i;
			}
			tower.add(arr[i]);
			result.add(i);
		}
		for(int i =1; i<=T; i++){
			System.out.print(r[i]+" ");
		}
	}

}
