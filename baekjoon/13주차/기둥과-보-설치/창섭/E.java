/** 2020. 10. 7. 오후 5:30:30
 * @author ventulus95
 */
package kakaoBilnd2020;

import java.util.Arrays;

public class E {
	static int n;
	static boolean column[][];
	static boolean row[][];
	

	public static void main(String[] args) {
		n = 5;  
		int[][] build_frame = {{1,0,0,1},{1,1,1,1},{2,1,0,1},{2,2,1,1},{5,0,0,1},{5,1,0,1},{4,2,1,1},{3,2,1,1}};
		column = new boolean[n+2][n+2];
		row = new boolean[n+2][n+2];
		int count =0;
		for(int i=0; i<build_frame.length; i++){
			int x = build_frame[i][0];
			int y = build_frame[i][1];
			int a = build_frame[i][2];
			int b = build_frame[i][3];
			if(a==0){ //기둥인 경우.
				if(b==0){ //삭제하는 경우.
					if(hasRemove(y+1,x+1, 0)){
						column[y+1][x+1] = false;
						count++;
					}
				}
				else{
					if(isColumn(y+1,x+1)){
						column[y+1][x+1] = true;
						count++;
					}
				}
			}
			else{ //보인 경우.
				if(b==0){
					if(hasRemove(y+1,x+1, 1)){
						row[y+1][x+1] = false;
						count--;
					}
					
				}
				else{
					if(isRow(y+1,x+1)){
						row[y+1][x+1] = true;
						count++;
					}
					
				}
			}
		}
		int[][] answer = new int[count][3];
		int index = 0; 
		for (int i = 1; i <= n + 1; i++) { // 남아 있는 기둥과 보 배열에 저장
			for (int j = 1; j <= n + 1; j++) {
				if (column[i][j]) answer[index++] = new int[]{j - 1, i - 1, 0}; 
				if (row[i][j]) answer[index++] = new int[]{j - 1, i - 1, 1}; 
			} 
		} 
		System.out.println(Arrays.deepToString(answer));
//		return answer; 

	}


	private static boolean hasRemove(int y, int x, int type) {
		boolean result = true; 
		if (type == 0) column[y][x] = false; // 임시로 삭제
		else row[y][x] = false; 
		loop: 
			for (int i = 1; i <= n+ 1; i++) {
				for (int j = 1; j <= n + 1; j++) {
					if (column[i][j] && !isColumn(i, j)) {
						result = false; 
						break loop; 
					} if (row[i][j] && !isRow(i, j)) {
						result = false; break loop; 
						} 
					} 
				} 
		if (type == 1) column[y][x] = true; // 삭제했던 구조물 원상복구
		else row[y][x] = true; 
		return result; 
	}


	private static boolean isRow(int y, int x) {
		return column[y-1][x] || column[y-1][x+1] || (row[y][x-1] && row[y][x+1]);
	}


	private static boolean isColumn(int y, int x) {
		return y==1 || column[y-1][x] || row[y][x] || row[y][x-1]; 
	}

}
