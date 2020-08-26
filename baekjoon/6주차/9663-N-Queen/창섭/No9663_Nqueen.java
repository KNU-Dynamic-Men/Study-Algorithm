/** 2019. 7. 20.
 * @author ventulus95
 */
package codeBaekJoon;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;

public class No9663_Nqueen {
	static int n;
	static int cnt =0;
	static boolean visited1[], visited2[], visited3[];

	static void dfs(int depth){
		if(depth==n){
			cnt++;
			return;
		}
		for(int i=0; i<n; i++){
			if(visited1[i]== true || visited2[i+depth]== true || visited3[depth-i+n-1]== true)
				continue;
			visited1[i] = true;
			visited2[i+depth] = true;
			visited3[depth-i+n-1] = true;
			dfs(depth+1);
			visited1[i] = false;
			visited2[i+depth] = false;
			visited3[depth-i+n-1] = false;
		}
				
	}

	public static void main(String[] args)throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		n = Integer.parseInt(br.readLine());
		visited1 = new boolean[n];
		visited2 = new boolean[2*n-1];
		visited3 = new boolean[2*n-1];
		dfs(0);
		bw.write(String.valueOf(cnt));
		bw.flush();
		bw.close();

	}

}
