/** 2020. 9. 8. 오후 11:26:08
 * @author ventulus95
 */
package kakaoBilnd2020;

public class A_rewind {

	public static void main(String[] args) {
		String tc ="xababcdcdababcdcd";
		int min = tc.length()/2;
		int ans = tc.length();
		for(int i=1; i<=min; i++){
			StringBuilder sb = new StringBuilder();
			String curr = tc.substring(0,i);
			int cnt = 1;
			int checker = 0;
			for(int idx =i; idx<=tc.length(); idx=idx+i){
				checker = idx;
				if(idx+i<=tc.length()){
					String cut = tc.substring(idx, idx+i);
					if(!curr.equals(cut)){
						if(cnt==1)
							sb.append(curr);
						else{
							sb.append(cnt);
							sb.append(curr);
							cnt = 1;
						}
						curr = cut;
					}
					else{
						cnt++;
					}
				}
			}
			if(cnt==1)
				sb.append(curr);
			else{
				sb.append(cnt);
				sb.append(curr);
			}
			if(checker+i>tc.length()){
				sb.append(tc.substring(checker));
			}
			ans = Math.min(sb.toString().length(), ans); 
		}
		System.out.println(ans);
	}

}
