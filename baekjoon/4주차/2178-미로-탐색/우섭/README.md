# 2178 - 미로 탐색

## 1. 개요

https://www.acmicpc.net/problem/2178

## 2. 코드
```python
from collections import deque
from sys import stdin
N, M = getMap(int, stdin.readline().split())
maze = []
getDx = [1, 0, -1, 0]
getDy = [0, 1, 0, -1]
stage = [[0]*M for _ in range(N)]
search = deque()
for _ in range(N):
    maze.append(list(getMap(int, stdin.readline().rstrip())))
stage[0][0] = 1
search.append([0, 0])
find = False
while(search and not find):
    now = search.popleft()
    for i in range(4):
        nx = now[0] + getDx[i]
        ny = now[1] + getDy[i]
        if nx == (N-1) and ny == (M-1):
            print(stage[now[0]][now[1]] + 1)
            find = True
            break
        if nx < 0 or nx >= N or ny < 0 or ny >= M:
            continue
        if stage[nx][ny] > 0 or maze[nx][ny] == 0:
            continue
        stage[nx][ny] = stage[now[0]][now[1]] + 1
        search.append([nx, ny])
```

## 3. 설명

1. 구현 방법 구상

    - **가장 대표적인 BFS 문제**
    - 미로의 최단 경로를 찾는 문제는 BFS로 풀도록 한다!

2. 변수 지정 및 입력

    ```python
    N, M = getMap(int, stdin.readline().split())
    maze = []
    getDx = [1, 0, -1, 0]
    getDy = [0, 1, 0, -1]
    stage = [[0]*M for _ in range(N)]
    search = deque()
    ```
    - `N, M` : 입력값으로 주어지는 미로의 행, 열의 수
    - `maze` : 미로가 저장되는 배열
    - `getDx, getDy` : `x, y`축 이동 시 좌표 계산용 배열
    - `stage` : 해당 좌표까지 이동하는데 걸린 최단 시간을 저장할 배열
    - `search` : 탐색할 좌표를 저장한 `Queue`
    - 변수를 지정한 다음 미로를 한 줄씩 입력받고, 하나씩 잘라서 `maze`에 저장한다
    ```python
    for _ in range(N):
        maze.append(list(getMap(int, stdin.readline().rstrip())))
    ```

    - 저장이 완료되면 `Queue`에 최초 좌표인 `(0, 0)`을 추가하고, `stage`에서 해당 위치에 `1`을 저장하여 탐색이 완료된 곳임을 표시한다
    - `find` 변수를 지정하여 미로 탐색이 완료되었는지 확인한다
    ```python
    stage[0][0] = 1
    search.append([0, 0])
    find = False
    ```

3. 탐색
    - Queue에 값이 남아있거나 최종 좌표까지 탐색이 완료될 때 까지 탐색을 진행한다
    ```python
    while(search and not find):
    now = search.popleft()
    ```

    - 현재 좌표의 상하좌우 좌표를 계산한다
    ```python
    for i in range(4):
        nx = now[0] + getDx[i]
        ny = now[1] + getDy[i]
    ```
    - 탐색하려는 좌표가 `(N-1, M-1)`인 경우 답을 출력하고 `find`를 `True`로 바꾸어주어 탐색을 종료한다
    ```python
    if nx == (N-1) and ny == (M-1):
        print(stage[now[0]][now[1]] + 1)
        find = True
        break
    ```
    - 탐색하려는 좌표가 미로의 크기를 벗어난 경우 다음 좌표를 탐색한다
    ```python
    if nx < 0 or nx >= N or ny < 0 or ny >= M:
        continue
    ```
    - 탐색하려는 좌표가 이미 탐색한 좌표이거나 이동할 수 없는 좌표인 경우 다음 좌표를 탐색한다
    ```python
    if stage[nx][ny] > 0 or maze[nx][ny] == 0:
        continue
    ```
    - 모든 조건을 통과한 경우 해당 좌표에 시간을 기록 후 `Queue`에 추가한다
    ```python
    stage[nx][ny] = stage[now[0]][now[1]] + 1
    search.append([nx, ny])
    ```

## 4. 결과
![image](https://user-images.githubusercontent.com/29600820/88556480-08bafa80-d064-11ea-8ee5-f2b16c9307c7.png)
