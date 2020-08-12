# 7569 - 토마토

## 1. 개요

https://www.acmicpc.net/problem/7569

## 2. 코드
**PyPy3로 제출한 코드**
```python
from collections import deque
from sys import stdin
M, N, H = getMap(int, stdin.readline().split())
box = []
getDx = [1, 0, -1, 0, 0, 0]
getDy = [0, 1, 0, -1, 0, 0]
dz = [0, 0, 0, 0, 1, -1]

tomato = [[[-1]*M for _ in range(N)] for _ in range(H)]
search = deque()

box = [[list(getMap(int, stdin.readline().rstrip().split())) for i in range(N)] for j in range(H)]

for k in range(H):
    for i in range(N):
        for j in range(M):
            if box[k][i][j] == 1:
                search.append([k, i, j])
                tomato[k][i][j] = 0
            if box[k][i][j] == -1:
                tomato[k][i][j] = 0
while(search):
    z, x, y = getMap(int, search.popleft())
    for i in range(6):
        nz = z + dz[i]
        nx = x + getDx[i]
        ny = y + getDy[i]
        if nx < 0 or nx >= N or ny < 0 or ny >= M or nz < 0 or nz >= H:
            continue
        if tomato[nz][nx][ny] >= 0:
            continue
        tomato[nz][nx][ny] = tomato[z][x][y] + 1
        search.append([nz, nx, ny])

find = True
maximum = 0
for k in range(H):
    for i in range(N):
        for j in range(M):
            if tomato[k][i][j] == -1:
                print(-1)
                find = False
        if not find: break
        maximum = max(maximum, max(tomato[k][i]))
    if not find: break
if find:
    print(maximum)
```

## 3. 설명

1. 구현 방법 구상

    - [7576 - 토마토](https://www.acmicpc.net/problem/7576)의 연장선상 문제
    - 7576 문제가 2차원이었다면, 이 문제는 **3차원**이다
    - [기존 풀이](https://github.com/KNU-Dynamic-Men/Study-Algorithm/tree/master/baekjoon/7576-토마토-2차원/우섭)를 약간 변경하여 풀이

2. 변경점

    - 높이 값인 `H`도 입력 받도록 수정
    - 좌표 계산을 위한 `dz` 배열 추가
    ```python
    dz = [0, 0, 0, 0, 1, -1]
    ```
    - 입력받을 때 높이도 고려하여 입력받고, 토마토가 익는 시간을 저장하는 배열 또한 높이를 추가하여 배열 생성
    - 탐색 시 높이 값인 `z`축도 고려하여 탐색

## 4. 결과

![image](https://user-images.githubusercontent.com/29600820/88615228-78fd6680-d0cc-11ea-8a1b-3183fdf8150b.png)