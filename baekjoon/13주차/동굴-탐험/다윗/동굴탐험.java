package 동굴탐험;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;

public class Solution {

	public boolean solution(int n, int[][] path, int[][] order) {
		int[] enter = new int[n];
		Arrays.fill(enter, -1);
		boolean[] visit = new boolean[n];
		for(int i=0; i<order.length;i++) {
			enter[order[i][1]] = order[i][0];
			if(order[i][1]==0) {
				return false;
			}
		}
		
		ArrayList<ArrayList<Integer>> graph = new ArrayList<ArrayList<Integer>>();
		// 그래프 초기화
		for (int i = 0; i < n; i++) {
			graph.add(new ArrayList<Integer>());
		}
		// 모든 간선 정보를 입력받기
		for (int i = 0; i < path.length; i++) {
			// 양방향
			graph.get(path[i][0]).add(path[i][1]);
			graph.get(path[i][1]).add(path[i][0]);
		}
		
		Queue<Integer> q = new LinkedList<Integer>();
        HashMap<Integer, Integer> hash = new HashMap<Integer, Integer>();

		visit[0] = true; 
		q.offer(0);
		while(!q.isEmpty()) {
			int x = q.poll();
			if(enter[x]!=-1) {
				if(!visit[enter[x]]) {
					hash.put(enter[x], x);
					continue;
				}else{
					enter[x] = -1;
				}
			}
			
			visit[x] = true;
			
		    for (int i : graph.get(x)) {
		        if (visit[i] == false) {
		        	q.offer(i);
		        }
		    }
		    if(hash.containsKey(x)) {
		    	q.offer(hash.get(x));
		    }
		}
		for(boolean tmp : visit) {
			if(!tmp) 
				return false;
		}
		    
		return true;
	}
}
