# 7576 - 토마토

## 1. 개요

https://www.acmicpc.net/problem/7576

## 2. 코드

```python
from collections import deque
from sys import stdin
M, N = getMap(int, stdin.readline().split())
box = []
getDx = [1, 0, -1, 0]
getDy = [0, 1, 0, -1]
tomato = [[-1]*M for _ in range(N)]
search = deque()
for _ in range(N):
    box.append(list(getMap(int, stdin.readline().rstrip().split(' '))))
for i in range(N):
    for j in range(M):
        if box[i][j] == 1:
            search.append([i, j])
            tomato[i][j] = 0
        if box[i][j] == -1:
            tomato[i][j] = 0
while(search):
    x, y = getMap(int, search.popleft())
    for i in range(4):
        nx = x + getDx[i]
        ny = y + getDy[i]
        if nx < 0 or nx >= N or ny < 0 or ny >= M:
            continue
        if tomato[nx][ny] >= 0:
            continue
        tomato[nx][ny] = tomato[x][y] + 1
        search.append([nx, ny])
find = True
maximum = 0
for i in range(N):
    for j in range(M):
        if tomato[i][j] == -1:
            print(-1)
            find = False
    if not find: break
    maximum = max(maximum, max(tomato[i]))
if find:
    print(maximum)
```

## 3. 설명

1. 구현 방법 구상

    - **BFS** 문제
    - 시작점이 여러 곳이다 → 처음에 시작점을 `Queue`에 **모두 넣고 시작**한다

2. 변수 지정

    ```python
    M, N = getMap(int, stdin.readline().split())
    box = []
    getDx = [1, 0, -1, 0]
    getDy = [0, 1, 0, -1]
    tomato = [[-1]*M for _ in range(N)]
    search = deque()
    ```
    `M, N` : 입력으로 주어진 토마토 상자의 가로, 세로 크기
    `box` : 입력으로 주어진 박스 내 토마토 배치
    `getDx, getDy` : `x, y`축 이동 시 좌표 계산용 배열
    `tomato` : 토마토가 익은 시간을 저장하는 배열, 초기값은 `-1`
    `search` : 탐색할 좌표를 저장한 `Queue`

3. 입력

    - 토마토의 정보를 저장한다
    ```python
    for _ in range(N):
        box.append(list(getMap(int, stdin.readline().rstrip().split(' '))))
    ```
    - 저장이 완료되면 해당 배열을 읽고 `tomato` 배열에 전처리를 진행한다
    - `1`이 저장된 경우 이미 익은 토마토가 위치하고 있는 것이므로 `Queue`에 좌표를 추가하고 0번째 시간에 익은 것이므로 `0`으로 값을 변경한다
    - `-1`이 저장된 경우 해당 구역은 토마토가 없으며, 결과 탐색 시 토마토가 익지 않은것으로 판단하지 않도록 `0`으로 값을 변경한다
    ```python
    for i in range(N):
        for j in range(M):
            if box[i][j] == 1:
                search.append([i, j])
                tomato[i][j] = 0
            if box[i][j] == -1:
                tomato[i][j] = 0
    ```

4. 탐색 진행

    - Queue에 값이 남아있지 않을 때 까지 진행한다
    ```python
    while(search):
        x, y = getMap(int, search.popleft())
    ```
    - 좌표 계산 후, **해당 좌표가 배열의 크기를 벗어났거나**, **해당 좌표의 토마토가 이미 익은 경우** 다음 좌표를 탐색한다
    ```python
    for i in range(4):
    nx = x + getDx[i]
    ny = y + getDy[i]
    if nx < 0 or nx >= N or ny < 0 or ny >= M:
        continue
    if tomato[nx][ny] >= 0:
        continue
    ```
    - 익지 않은 토마토의 좌표가 확인되면 Queue에 해당 좌표를 추가하고 익는데 걸린 시간을 기록한다
    ```python
    tomato[nx][ny] = tomato[x][y] + 1
    search.append([nx, ny])
    ```

5. 출력
    - tomato 배열 내에 -1이 하나라도 존재하는 경우 익지 않은 토마토가 존재한다는 의미이므로 -1을 출력하고 종료한다
    - 그렇지 않은 경우 최대값을 확인하고 출력한 후 종료한다
    ```python
    find = True
    maximum = 0
    for i in range(N):
        for j in range(M):
            if tomato[i][j] == -1:
                print(-1)
                find = False
        if not find: break
        maximum = max(maximum, max(tomato[i]))
    if find:
        print(maximum)
    ```
## 4. 결과

![image](https://user-images.githubusercontent.com/29600820/88614674-35562d00-d0cb-11ea-84b3-e3cb561b304e.png)