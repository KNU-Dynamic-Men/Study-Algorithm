/** 2020. 7. 29. 오전 9:54:52
 * @author ventulus95
 */
package codeBaekJoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class No4179_Fire {

	static int[][] map, visited;
	static int[] dx = {-1,0,1,0};
	static int[] dy = {0,1,0,-1};

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String tc = br.readLine();
		StringTokenizer st = new StringTokenizer (tc , " ");
		int r = Integer.parseInt(st.nextToken());
		int c = Integer.parseInt(st.nextToken());
		map = new int[r][c];
		visited = new int[r][c];
		Queue<Node4179> jq = new LinkedList<>();
		Queue<Node4179> fq = new LinkedList<>();
		for(int i=0; i<r; i++){
			String t = br.readLine();
			for(int j=0; j<c; j++){
				char temp = t.charAt(j);
				if(temp=='#'){
					map[i][j] = -1;
				}
				else if (temp=='J'){
					map[i][j] = 1;
					jq.add(new Node4179(i,j));
				}
				else if(temp == 'F'){
					map[i][j] = -2;
					fq.add(new Node4179(i, j));
				}
				else{
					map[i][j] = 0;
				}
			}
		}
		int answer = 0;
		while(true){
			answer++;
			int fs  = fq.size();
			while(fs>0){
				fs--;
				Node4179 node = fq.poll();
				int y = node.y;
				int x = node.x;
				for(int i=0; i<4; i++){
					if (x+dx[i] >= 0 && x+dx[i] < c && y+dy[i]> 0 && y+dy[i] < r){
						if(map[y+dy[i]][x+dx[i]] >=0){
							fq.add(new Node4179(y+dy[i], x+dx[i]));
							map[y+dy[i]][x+dx[i]] = -2;
						}
					}
				}
			}
			int js = jq.size();
			while(js>0){
				js--;
				Node4179 node = jq.poll();
				int y = node.y;
				int x = node.x;
				for(int i=0; i<4; i++){
					if (x+dx[i] < 0 || x+dx[i] >= c || y+dy[i]< 0 || y+dy[i] >= r){
						System.out.println(answer);
						return;
					}
					if(map[y+dy[i]][x+dx[i]] ==0){
						jq.add(new Node4179(y+dy[i], x+dx[i]));
						map[y+dy[i]][x+dx[i]] = 1;
					}
				}
			}
			if(jq.isEmpty()){
				System.out.println("IMPOSSIBLE");
				return;
			}
		}
	}
}

class Node4179{
	int x,y;

	public Node4179(int y, int x ){
		this.x = x;
		this.y = y;
	}
}