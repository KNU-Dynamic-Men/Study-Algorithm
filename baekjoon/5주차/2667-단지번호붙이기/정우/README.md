# **2667 - 단지번호붙이기**

## **1. 개요**

[https://www.acmicpc.net/problem/2667](https://www.acmicpc.net/problem/2667)

## **2. 코드**

C++
```c++
#include <cstdio>		// for scanf, printf
#include <algorithm>	// for sort

using namespace std;

int n, arr[27][27], res[27*27], resCnt;
int dirs[][2] = {
	{1, 0},
	{-1, 0},
	{0, 1},
	{0, -1}
};

int findHomes(int r, int c) {
	int sum = 1;
	for (int i = 0; i < 4; i++) {
		if (arr[r+dirs[i][0]][c+dirs[i][1]] == 1) {
			arr[r+dirs[i][0]][c+dirs[i][1]] = 0;
			sum += findHomes(r+dirs[i][0], c+dirs[i][1]);
		}
	}
	return sum;
}

int main() {

	scanf("%d", &n);
	for (int i = 1; i <= n; i++)
		for (int j = 1; j <= n; j++)
			scanf("%1d", &arr[i][j]);

	for (int i = 1; i <= n; i++) {
		for (int j = 1; j <= n; j++) {
			if (arr[i][j] == 1) {
				arr[i][j] = 0;
				res[resCnt++] = findHomes(i, j);
			}
		}
	}

	printf("%d\n", resCnt);
	sort(res, res+resCnt);
	for (int i = 0; i < resCnt; i++)
		printf("%d\n", res[i]);
		
	return 0;
}
```

## **3. 설명**

1. 주어진 배열을 전체 탐색
2. 탐색 중 1이 나오면 DFS로 이어진 1을 탐색(재귀)
3. 탐색 완료한 1은 0으로 변경
4. 연결된 집을 모두 탐색하면 집의 크기를 +1 해서 return

## **4. 여정**

1. 제출 언어를 잘 봅시다.
2. 성공

## **5. 결과**
![image](https://user-images.githubusercontent.com/41278416/88884033-ac78f600-d270-11ea-99e8-a1f5bc125d6f.png)