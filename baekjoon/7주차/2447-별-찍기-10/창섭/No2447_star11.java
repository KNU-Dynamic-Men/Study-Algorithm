/** 2020. 8. 18. 오후 7:20:28
 * @author ventulus95
 */
package codeBaekJoon;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;

public class No2447_star11 {
	
	static char map[][];
	
	static void star(int y, int x, int size){
		if(size==1){
			map[y][x] = '*';
			return;
		}
		int cnt = 0;
		for(int i=0; i<3; i++){
			for(int j=0; j<3; j++){
				if(i==1 && j==1)
					continue;
				star(y+i*(size/3),x+(j*size/3),size/3);
			}
		}
	}

	public static void main(String[] args)throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		int n = Integer.parseInt(br.readLine());
		map = new char[n][n];
		for(int i =0; i<n; i++){
			Arrays.fill(map[i], ' ');
		}
		star(0,0,n);
		for(int i=0; i<n; i++){
			for(int j=0; j<n; j++){
				bw.append(map[i][j]);
			}
			bw.newLine();
		}
		bw.flush();
		bw.close();
	}
}
