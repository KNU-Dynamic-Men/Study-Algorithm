/** 2020. 8. 11. 오후 10:07:40
 * @author ventulus95
 */
package codeBaekJoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class No15649_ntom {

	static int n,m;
	static int arr[];
	static boolean visited[];
	
	public static void dfs(int cnt){
		if(cnt == m){
			for(int i=0; i<m; i++){
				System.out.print(arr[i]+" ");
			}
			System.out.println();
			return;
		}
		for(int i =1; i<=n; i++){
			if(visited[i]==false){
				arr[cnt] = i;
				visited[i] = true;
				dfs(cnt+1);
				visited[i] = false;
			}
		}
		
		
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String t = br.readLine();
		n = Integer.parseInt(t.split(" ")[0]);
		m = Integer.parseInt(t.split(" ")[1]);
		arr = new int[n+1];
		visited = new boolean[n+1];
		dfs(0);

	}

}
