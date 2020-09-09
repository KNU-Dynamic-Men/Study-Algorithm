/** 2020. 9. 9. 오전 11:06:44
 * @author ventulus95
 */
package kakaoBilnd2020;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class D {

	public static void main(String[] args) {
		String[] words={"frodo", "front", "frost", "frozen", "frame", "kakao"};
//		String[] queries={"fro??", "????o", "fr???", "fro???", "pro?"};
		String[] queries={"??????", "fro??", "fro??", "fro???", "pro?"};
		int[] answer = new int[queries.length];
		Set<String> mySet = new HashSet<>(Arrays.asList(queries));
		String[] k = new String[mySet.size()];
 		k = mySet.toArray(k);
		int[] querycnt = new int[k.length];
		Arrays.fill(querycnt, -1);
		for(int i =0; i<queries.length; i++){
			String query = queries[i];
			boolean isDup = false;
			int dupidx = -999;
			for(int t=0; t<k.length; t++){
				if(k[t].equals(query)){
					if(querycnt[t]>=0){
						answer[i] = querycnt[t];
						isDup = true;
					}
					else{
						dupidx = t;
					}
				}
			}
			if(isDup==false){
				boolean isPre = true;
				if(query.charAt(0) == '?')
					isPre = false;
				String fix = query.replace("?", "");
				if(isPre == true){
					for(int j =0; j<words.length; j++){
						if(words[j].startsWith(fix) && query.length()==words[j].length())
							answer[i]++;
					}
				}
				else{
					for(int j =0; j<words.length; j++){
						if(words[j].endsWith(fix)&& query.length()==words[j].length())
							answer[i]++;
//						if(fix.equals("")&& query.length()==words[j].length())
//							answer[i]++;
					}
				}
				if(dupidx >0)
					querycnt[dupidx] = answer[i];
			}
		}
		Stream<Object> qqqq = Arrays.stream(mySet.toArray()); 
		
		IntStream s = Arrays.stream(answer);
		s.forEach(System.out::println);
	}

}
