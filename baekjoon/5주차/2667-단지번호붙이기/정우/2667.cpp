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