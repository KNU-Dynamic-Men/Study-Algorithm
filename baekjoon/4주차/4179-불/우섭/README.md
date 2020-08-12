# 4179 - 불!

## 1. 개요

https://www.acmicpc.net/problem/4179

## 2. 코드

```python
from collections import deque
from sys import stdin

R, C = getMap(int, stdin.readline().split())
stage = []
fi = [[0]*C for _ in range(R)]
pi = [[0]*C for _ in range(R)]
fi_q = deque()
pi_q = deque()
getDx = [1, 0, -1, 0]
getDy = [0, 1, 0, -1]

def read_stage():
    for _ in range(R):
        stage.append(list(stdin.readline().rstrip()))

    for i in range(R):
        for j in range(C):
            if stage[i][j] == 'F':
                fi_q.append([i, j])
                fi[i][j] = 1
            if stage[i][j] == 'J':
                pi_q.append([i, j])
                pi[i][j] = 1

def fire_dfs():
    while(fi_q):
        cur = fi_q.popleft()
        for i in range(4):
            nx = cur[0] + getDx[i]
            ny = cur[1] + getDy[i]
            if nx < 0 or nx >= R or ny < 0 or ny >= C:
                continue
            if fi[nx][ny] > 0 or stage[nx][ny] == '#':
                continue
            fi[nx][ny] = fi[cur[0]][cur[1]] + 1
            fi_q.append([nx, ny])

def people_dfs():
    while(pi_q):
        cur = pi_q.popleft()
        for i in range(4):
            nx = cur[0] + getDx[i]
            ny = cur[1] + getDy[i]
            if nx < 0 or nx >= R or ny < 0 or ny >= C:
                return pi[cur[0]][cur[1]]
            if pi[nx][ny] > 0 or stage[nx][ny] == '#':
                continue
            if fi[nx][ny] != 0 and fi[nx][ny] <= (pi[cur[0]][cur[1]] + 1):
                continue
            pi[nx][ny] = pi[cur[0]][cur[1]] + 1
            pi_q.append([nx, ny])
    return -1

read_stage()
fire_dfs()
ans = people_dfs()
print('IMPOSSIBLE' if ans == -1 else ans)
```

## 3. 설명

1. 구현 방법 구상

    - **BFS - Breadth-First Search**란?
    - 시작 정점으로부터 가까운 정점을 먼저 방문하고 멀리 떨어져 있는 정점을 나중에 방문하는 순회 방법
    - 즉 깊이 탐색하는 것이 아닌 **넓게 탐색하는 것**
    - 주로 미로 등의 최단거리를 탐색할 때 사용한다
    - [참고 링크 - Heee's Development Blog](https://gmlwjd9405.github.io/2018/08/15/algorithm-bfs.html)
    - [BaaaaaaaarkingDog 실전 알고리즘](https://blog.encrypted.gg/941?category=773649) 글을 참고하여 구현하였다.

2. 변수 지정

    ```python
    R, C = getMap(int, stdin.readline().split())
    stage = []
    fi = [[0]*C for _ in range(R)]
    pi = [[0]*C for _ in range(R)]
    fi_q = deque()
    pi_q = deque()
    getDx = [1, 0, -1, 0]
    getDy = [0, 1, 0, -1]
    ```
    - `R, C` : 입력으로 주어진 행, 열의 개수
    - `stage` : 미로가 저장되는 배열
    - `fi` : 해당 좌표에 불이 번지는 시간을 저장한 배열
    - `pi` : 지훈이가 해당 좌표로 이동할 수 있는 시간을 저장한 배열
    - `fi_q` : 불이 번진 좌표를 저장한 `Queue`
    - `pi_q` : 지훈이가 이동 가능한 좌표를 저장한 `Queue`
    - `getDx` : `x`축 이동 시 좌표 계산을 위한 배열
    - `getDy` : `y`축 이동 시 좌표 계산을 위한 배열

3. 입력 함수 구현

    - 각각의 기능들을 따로 분리하여 오류 발생 시 빠른 수정 및 파악이 가능하도록 함수를 구분하였다

    ```python
    # 입력 함수
    def read_stage():
        for _ in range(R):
            stage.append(list(stdin.readline().rstrip()))

        for i in range(R):
            for j in range(C):
                if stage[i][j] == 'F':
                    fi_q.append([i, j])
                    fi[i][j] = 1
                if stage[i][j] == 'J':
                    pi_q.append([i, j])
                    pi[i][j] = 1
    ```
    - `R`의 크기만큼 입력을 한 줄씩 받아 한 글자씩 분리 후 `stage` 배열에 저장한다.
    ```python
    for _ in range(R):
        stage.append(list(stdin.readline().rstrip()))
    ```
    - 전체 지도를 탐색하여 지훈이와 불의 시작 좌표를 확인한 후 각각의 Queue에 추가한다
    - 좌표를 저장하는 배열에도 해당 위치에 1을 입력하여 시작점을 추가한다

    ```python
    for i in range(R):
        for j in range(C):
            # F의 위치를 확인한 후 좌표를 저장한다.
            if stage[i][j] == 'F':
                fi_q.append([i, j])
                fi[i][j] = 1
            # J의 위치를 확인한 후 좌표를 저장한다.
            if stage[i][j] == 'J':
                pi_q.append([i, j])
                pi[i][j] = 1
    ```

4. 불이 번지는 시간 계산

    - 불이 미로의 각 좌표에 도달하는 시간을 먼저 계산한다
    - 지훈이가 미로의 탈출구를 탐색할 때 불이 이미 번져있는 경우 해당 좌표로 이동할 수 없다
    - 동시에 지훈이와 불을 움직이기보다는 **불이 번지는 시간을 먼저 계산**한 후 지훈이가 이동할 시 탐색 조건으로 추가한다
    ```python
    # 불이 번지는 시간 탐색
    def fire_dfs():
        while(fi_q):
            cur = fi_q.popleft()
            for i in range(4):
                nx = cur[0] + getDx[i]
                ny = cur[1] + getDy[i]
                if nx < 0 or nx >= R or ny < 0 or ny >= C:
                    continue
                if fi[nx][ny] > 0 or stage[nx][ny] == '#':
                    continue
                fi[nx][ny] = fi[cur[0]][cur[1]] + 1
                fi_q.append([nx, ny])
    ```
    - 불이 번지는 좌표를 저장한 `Queue`에 더이상 값이 없을 때 까지 진행한다
    - `fi_q`에 저장된 좌표값은 불이 번진 위치이며, 해당 `Queue`가 비어있다는 것은 **더이상 탐색 가능한 좌표가 없다**는 것을 의미한다
    - `cur`에 `popleft()`를 한 좌표값을 저장한다.
    ```python
    while(fi_q):
        cur = fi_q.popleft()
    ```

    - 이미 저장되어 있는 `getDx`, `getDy` 리스트를 이용하여 4방향의 좌표를 계산 후 탐색을 진행한다
    ```python
    for i in range(4):
        nx = cur[0] + getDx[i]
        ny = cur[1] + getDy[i]
    ```

    - *조건 1*. 해당 좌표값이 지도의 크기를 벗어난 경우 불이 번질 수 없으므로 다음 좌표를 탐색한다
    ```python
    if nx < 0 or nx >= R or ny < 0 or ny >= C:
        continue
    ```
    - *조건 2*. 해당 좌표값이 이미 불이 번진 상태이거나 벽인 경우 불이 번질 수 없으므로 다음 좌표를 탐색한다
    ```python
    if fi[nx][ny] > 0 or stage[nx][ny] == '#':
        continue
    ```
    - 모든 조건을 넘긴 경우 불이 번질 수 있는 좌표이므로 해당 좌표에 불이 번진 시간을 기록하고, Queue에 좌표를 추가하여 준다
    ```python
    fi[nx][ny] = fi[cur[0]][cur[1]] + 1
    fi_q.append([nx, ny])
    ```

5. 지훈이의 이동 시간 계산

    - 불이 번지는 시간이 계산 완료되면 해당 시간을 추가로 확인하여 지훈이가 탈출 가능한 경로를 탐색한다
    ```python
    def people_dfs():
        while(pi_q):
            cur = pi_q.popleft()
            for i in range(4):
                nx = cur[0] + getDx[i]
                ny = cur[1] + getDy[i]
                if nx < 0 or nx >= R or ny < 0 or ny >= C:
                    return pi[cur[0]][cur[1]]
                if pi[nx][ny] > 0 or stage[nx][ny] == '#':
                    continue
                if fi[nx][ny] != 0 and fi[nx][ny] <= (pi[cur[0]][cur[1]] + 1):
                    continue
                pi[nx][ny] = pi[cur[0]][cur[1]] + 1
                pi_q.append([nx, ny])
        return -1
    ```
    - 불이 번지는 시간과 동일하게 `Queue`에 더이상 값이 없을 때 까지 진행한다
    ```python
    while(pi_q):
        cur = pi_q.popleft()
    ```
    - 불이 번지는 시간과 동일하게 이미 저장되어 있는 `getDx`, `getDy` 리스트를 이용하여 4방향의 좌표를 계산 후 탐색을 진행한다
    ```python
    for i in range(4):
        nx = cur[0] + getDx[i]
        ny = cur[1] + getDy[i]
    ```
    - 지훈이의 좌표가 지도의 크기를 벗어난 경우 탈출에 성공한 것이므로 해당 이동 시간을 반환하고 함수를 종료한다
    ```python
    if nx < 0 or nx >= R or ny < 0 or ny >= C:
        return pi[cur[0]][cur[1]]
    ```
    - 탈출하지 못한 경우 계속해서 조건에 따라 탐색을 진행한다
    - *조건 1*. 해당 좌표값이 이미 거쳐 온 상태이거나 벽인 경우 이동할 수 없으므로 다음 좌표를 탐색한다
    ```python
    if pi[nx][ny] > 0 or stage[nx][ny] == '#':
        continue
    ```
    - *조건 2*. 해당 좌표에 불이 번져있는 경우 불이 번진 시간이 지훈이가 이동 가능한 시간보다 먼저인 경우 해당 좌표로 이동할 수 없으므로 다음 좌표를 탐색한다
    ```python
    if fi[nx][ny] != 0 and fi[nx][ny] <= (pi[cur[0]][cur[1]] + 1):
        continue
    ```
    - 모든 조건을 넘긴 경우 지훈이가 이동할 수 있는 좌표이므로 해당 좌표에 이동 시간을 기록한 후 `Queue`에 좌표를 추가한다
    ```python
    pi[nx][ny] = pi[cur[0]][cur[1]] + 1
    pi_q.append([nx, ny])
    ```
    - `Queue`에 값이 더이상 존재하지 않는 경우 미로를 탈출할 방법이 없으므로 `-1`을 반환한다 ~~지훈이의 명복도 빌어주자~~

6. 출력
    - 지훈이의 탐색까지 마친 후 반환값을 기준으로 출력한다
    - 반환값이 `-1`인 경우 `IMPOSSIBLE`을 출력하고, 아닌 경우 해당 걸린 시간을 출력한다
    ```python
    ans = people_dfs()
    print('IMPOSSIBLE' if ans == -1 else ans)
    ```

## 4. 결과
![image](https://user-images.githubusercontent.com/29600820/88551597-fe95fd80-d05d-11ea-98fe-4190ac4cc95a.png)
