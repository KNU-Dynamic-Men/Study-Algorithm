/** 2020. 8. 17. 오후 9:59:53
 * @author ventulus95
 */
package codeBaekJoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class No1182_subsequenceSum {
	
	static int max = 1, s, cnt=0, n;
	static int arr[];
	static boolean visited[];
	
	static void dfs(int idx, int depth){
		if(depth == max){
			int sum = 0;
			for(int i =0; i<n; i++){
				if(visited[i]==true){
					 sum += arr[i];
//					 System.out.print(arr[i]+" ");
				}
			}
//			System.out.println("sum: "+sum);
			if(sum==s){
				cnt++;
			}
			return;
		}
		for(int i=idx; i<n; i++){
			if(visited[i] == true)
				continue;
			visited[i] = true;
			dfs(i, depth+1);
			visited[i] = false;
		}
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String t = br.readLine();
		n = Integer.parseInt(t.split(" ")[0]);
		s = Integer.parseInt(t.split(" ")[1]);
		String sub = br.readLine();
		StringTokenizer st = new StringTokenizer(sub, " ");
		arr = new int [n];
		for(int i =0; i<n; i++){
			arr[i] = Integer.parseInt(st.nextToken());
		}
		visited = new boolean[n];
		for(int i=1; i<=n; i++){
			dfs(0,0);
			max++;
			Arrays.fill(visited, false);
		}
		System.out.println(cnt);

	}

}
