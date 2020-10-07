package Implementation;

import java.util.ArrayList;
import java.util.Arrays;


class Solution {
	public int solution(int n, int[] weak, int[] dist) {
		int answer = dist.length+1;
		// 길이를 2배로 늘려서 '원형'을 일자 형태로 변경
		ArrayList<Integer> weakList = new ArrayList<Integer>();
		for (int i = 0; i < weak.length; i++) {
			weakList.add(weak[i]);
		}
		for (int i = 0; i < weak.length; i++) {
			weakList.add(weak[i] + n);
		}
		Arrays.sort(dist);
		

		// 0부터 length - 1까지의 위치를 각각 시작점으로 설정
		for (int start = 0; start < weak.length; start++) {
			// 친구를 나열하는 모든 경우 각각에 대하여 확인
			int cnt = 1; // 투입할 친구의 수
			// 해당 친구가 점검할 수 있는 마지막 위치
			int position = weakList.get(start) + dist[dist.length-cnt];
			
			// 시작점부터 모든 취약한 지점을 확인
			for (int index = start; index < start + weak.length; index++) {
				// 점검할 수 있는 위치를 벗어나는 경우
				if (position < weakList.get(index)) {
					cnt += 1; // 새로운 친구를 투입
					if (cnt > dist.length) { // 더 투입이 불가능하다면 종료
						break;
					}
					position = weakList.get(index) + dist[dist.length-cnt];
				}
			}
			answer = Math.min(answer, cnt); // 최솟값 계산
		}
		if (answer > dist.length) {
            return -1;
        }
        return answer;
	}
}