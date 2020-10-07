# **2020 카카오 - 기둥과 보 설치

## **1. 개요**

https://programmers.co.kr/learn/courses/30/lessons/60061

## **2. 코드**

```java
import java.util.ArrayList;
import java.util.Collections;

class Builder implements Comparable<Builder> {

	private int x, y, type;

	public Builder(int x, int y, int type) {
		this.x = x;
		this.y = y;
		this.type = type;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	@Override
	public int compareTo(Builder b) {
		if (this.getX() - b.getX() != 0) {
			return this.getX() - b.getX();
		}
		if (this.getY() - b.getY() != 0) {
			return this.getY() - b.getY();
		}
		return this.getType() - b.getType();

	}
}
class Solution {
    public int[][] solution(int n, int[][] build_frame) {
        ArrayList<Builder> map = new ArrayList<Builder>();
		int x, y;
		for (int step = 0; step < build_frame.length; step++) {
			x = build_frame[step][0];
			y = build_frame[step][1];
			Builder tmp =new Builder(x, y, build_frame[step][2]);
			if (build_frame[step][3] == 1) {// 설치
				map.add(tmp);
				if (!isPossible(map))
					map.remove(tmp);
			} else {// 삭제
				map.remove(indexBuilderByFild(map,x, y, build_frame[step][2]));
				if (!isPossible(map))
					map.add(tmp);
			}
		} // build_frame순회
		// map sort
		Collections.sort(map);
		// map 2'array로 변환
		int[][] answer = new int[map.size()][3];
		for(int i=0; i<map.size(); i++) {
			Builder tmp = map.get(i);
			answer[i][0] = tmp.getX();
			answer[i][1] = tmp.getY();
			answer[i][2] = tmp.getType();
		}
		return answer;
	}

	private static boolean isPossible(ArrayList<Builder> b) {
		for (Builder bb : b) {
			if (bb.getType() == 0) {// 기둥
				// '바닥 위' 혹은 '보의 한쪽 끝 부분 위' 혹은 '다른 기둥 위'라면 정상
				if (bb.getY() == 0 || b.contains(new Builder(bb.getX(), bb.getY(), 1))
						|| queryBuilderByFild(b,bb.getX()-1, bb.getY() , 1)
						|| queryBuilderByFild(b,bb.getX(), bb.getY() , 1)
						|| queryBuilderByFild(b,bb.getX(), bb.getY() - 1, 0))
					continue;
				return false;
			} else {// 보
					// '한쪽 끝부분이 기둥 위' 혹은 '양쪽 끝부분이 다른 보와 동시에 연결'이라면 정상
				if (queryBuilderByFild(b,bb.getX()+1, bb.getY()-1, 0)
						||queryBuilderByFild(b,bb.getX(), bb.getY()-1, 0)
						|| (queryBuilderByFild(b,bb.getX()-1, bb.getY() , 1)
								&& queryBuilderByFild(b,bb.getX()+1, bb.getY(), 1)))
					continue;
				return false;
			}
		}
		return true;
	}
	private static boolean queryBuilderByFild(ArrayList<Builder> b, int x, int y, int t) {
		for (Builder bb : b) {
			if (bb.getX() == x && bb.getY() == y && bb.getType()==t) {
				return true;
			}
		}
		return false;
	}
    private static int indexBuilderByFild(ArrayList<Builder> b, int x, int y, int t) {
		int cnt=0;
		for (Builder bb : b) {
			if (bb.getX() == x && bb.getY() == y && bb.getType()==t) {
				return cnt;
			}
            cnt++;
		}
		return -1;
	}
}
```

## **3. 설명**

1. 설계 명령을 따라 만들과 가능성을 확인한다.
2. 문제점, arraylist속 객체 확인법을 몰라 따로 함수 구현.
