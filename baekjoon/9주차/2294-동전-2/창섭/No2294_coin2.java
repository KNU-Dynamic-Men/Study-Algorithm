package codeBaekJoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class No2294_coin2 {

	static int dp[];

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String p = br.readLine();
		StringTokenizer st = new StringTokenizer(p, " ");
		int n = Integer.parseInt(st.nextToken());
		int k = Integer.parseInt(st.nextToken());
		int coin[] = new int [n+1];
		dp = new int[100001];
		Arrays.fill(dp, 100001);
		dp[0] = 0;
		for(int i=1; i<=n; i++){
			coin[i] = Integer.parseInt(br.readLine());
			dp[coin[i]] = 1;
		}
		for(int i=1; i<=n; i++){
			for(int j=coin[i]; j<=k; j++){
				dp[j] = Math.min(dp[j], dp[j-coin[i]]+1);
			}
		}
		if(dp[k] == 100001){
			System.out.println(-1);
		}
		else{
			System.out.println(dp[k]);
		}	
	}

}
