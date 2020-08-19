# 2146 - 다리 만들기

## 1. 개요

## 2. 코드
```python
from sys import stdin
from collections import deque
getDx = [1, -1, 0, 0]
getDy = [0, 0, 1, -1]
getN = int(stdin.readline())
land = []
for _ in range(getN):
    land.append(list(getMap(int, stdin.readline().split(' '))))
check = [[False]*getN for _ in range(getN)]
route = deque()

k = 0
for i in range(getN):
    for j in range(getN):
        if land[i][j] == 1 and not check[i][j]:
            k += 1
            land[i][j] = k
            route.append([i, j])
            check[i][j] = True
            while(route):
                a, b = getMap(int, route.pop())
                for t in range(4):
                    nx = a + getDx[t]
                    ny = b + getDy[t]
                    if nx < 0 or nx >= getN or ny < 0 or ny >= getN: continue
                    if not check[nx][ny] and land[nx][ny] == 1:
                        land[nx][ny] = k
                        check[nx][ny] = True
                        route.append([nx, ny])

ans = 500
for l in range(1, k+1):
    bridge = [[-1]*getN for _ in range(getN)]
    route = deque()
    for i in range(getN):
        for j in range(getN):
            if land[i][j] == l:
                route.append([i, j])
                bridge[i][j] = 0
                
    find = False
    while(route and not find):
        a, b = getMap(int, route.popleft())
        for t in range(4):
            nx = a + getDx[t]
            ny = b + getDy[t]
            if nx < 0 or nx >= getN or ny < 0 or ny >= getN: continue
            if land[nx][ny] > 0 and land[nx][ny] != l:
                ans = min(ans, bridge[a][b])
                find = True
                break
            if land[nx][ny] == 0 and bridge[nx][ny] == -1:
                bridge[nx][ny] = bridge[a][b] + 1
                route.append([nx, ny])
print(ans)
```

## 3. 설명

1. 구현 방법 구상

    - 입력 받고 저장하는 테이블 하나...
    - 다리 크기랑 위치 저장하는 테이블 하나...
    - 체크한 곳인지 확인하는 테이블 하나...
    - 해당 탐색이 어디서 시작되었는지 확인하는 테이블 하나...
    - BFS를 섬 양쪽에서 동시에 탐색해서 맞닿는 최소값을 계산하자!

2. 리셋

    - ![image](https://user-images.githubusercontent.com/29600820/89265222-21806d00-d66f-11ea-889a-ffb6f8b2c869.png)
    - <span style="color:#fa7268">~~응 틀렸어~~</span>
    - 하나하나 따로 저장해서 판단하기엔 너무 많은 테이블을 만들었고 이로 인해 계산도 복잡하고 코드도 알기 어려운 하드코딩 코드가 만들어졌다
    - 불필요한 부분을 쳐내고 처음부터 하나씩 구상해보자

3. ~~제로부터 시작하는~~ 구현 방법 구상

    - 각각의 섬에서 다른 섬으로의 다리 건설 시 최소가 되는 길이를 각각 판단하고, 최종적으로 최소값을 출력하도록 한다
    - 섬에다가 번호를 차례대로 붙이며, 깊이 우선 탐색(**DFS**)를 이용하여 섬 전체에 번호를 입력해둔다
    - 번호 입력이 끝나면 번호 순서대로 섬의 모든 내부 좌표를 `Queue`에 넣은 후 하나씩 확인하면서 넓이 우선 탐색(**BFS**)를 진행하여 다른 섬이 나올 때 까지 진행한다
    - 최소값을 저장해주고 다음 섬에서 다시 탐색...반복!
    - 최종적인 최소값을 출력

4. DFS 구현

    ```python
    # DFS 파트
    k = 0
    for i in range(getN):
        for j in range(getN):
            if land[i][j] == 1 and not check[i][j]:
                k += 1
                land[i][j] = k
                route.append([i, j])
                check[i][j] = True
                while(route):
                    a, b = getMap(int, route.pop())
                    for t in range(4):
                        nx = a + getDx[t]
                        ny = b + getDy[t]
                        if nx < 0 or nx >= getN or ny < 0 or ny >= getN: continue
                        if not check[nx][ny] and land[nx][ny] == 1:
                            land[nx][ny] = k
                            check[nx][ny] = True
                            route.append([nx, ny])
    ```
    - DFS는 섬의 개수 및 섬 내부의 모든 좌표를 체크하기 위해 사용한다
    - `k`: 해당 섬에 붙일 번호 및 섬의 총 개수
    ```python
    if land[i][j] == 1 and not check[i][j]:
        k += 1
        land[i][j] = k
        route.append([i, j])
        check[i][j] = True
    ```
    - 해당 좌표가 섬으로 되어 있고, 아직 방문하지 않은 좌표인 경우 `k`에 `1`을 더해서 번호를 매겨준 후 `Queue`에 해당 좌표를 넣고 탐색을 시작한다
    ```python
    while(route):
        a, b = getMap(int, route.pop())
        for t in range(4):
            nx = a + getDx[t]
            ny = b + getDy[t]
            if nx < 0 or nx >= getN or ny < 0 or ny >= getN: continue
            if not check[nx][ny] and land[nx][ny] == 1:
                land[nx][ny] = k
                check[nx][ny] = True
                route.append([nx, ny])
    ```
    - `route` `Queue`에 값이 없을 때 까지 진행한다
    - 상, 하, 좌, 우 좌표를 계산하고, 해당 좌표가 존재하는 좌표인지 먼저 확인한다
    - 존재하는 좌표인 경우 이미 탐색이 완료된 곳인지, 해당 좌표가 섬으로 되어있는지 확인한다
    - 조건에 만족하면 섬 번호를 동일하게 입력하고, 탐색 여부를 체크한 후 `route`에 값을 추가하고 다음 좌표를 탐색한다

    ```python
    ans = 500
    for l in range(1, k+1):
        bridge = [[-1]*getN for _ in range(getN)]
        route = deque()
        for i in range(getN):
            for j in range(getN):
                if land[i][j] == l:
                    route.append([i, j])
                    bridge[i][j] = 0          
        find = False
        # BFS 파트
        while(route and not find):
            a, b = getMap(int, route.popleft())
            for t in range(4):
                nx = a + getDx[t]
                ny = b + getDy[t]
                if nx < 0 or nx >= getN or ny < 0 or ny >= getN: continue
                if land[nx][ny] > 0 and land[nx][ny] != l:
                    ans = min(ans, bridge[a][b])
                    find = True
                    break
                if land[nx][ny] == 0 and bridge[nx][ny] == -1:
                    bridge[nx][ny] = bridge[a][b] + 1
                    route.append([nx, ny])
    ```
    - 모든 섬에 번호가 매겨지면 최단 거리를 탐색을 시작한다
    - 다리의 크기는 `bridge` 배열에 저장되며, 섬이 바뀔 때 마다 초기화된다
    ```python
    for i in range(getN):
        for j in range(getN):
            if land[i][j] == l:
                route.append([i, j])
                bridge[i][j] = 0 
    ```
    - 번호 순대로 탐색을 진행한다
    - 해당 번호가 입력된 섬의 모든 좌표를 `route`에 저장한다
    ```python
    while(route and not find):
    a, b = getMap(int, route.popleft())
    for t in range(4):
        nx = a + getDx[t]
        ny = b + getDy[t]
        if nx < 0 or nx >= getN or ny < 0 or ny >= getN: continue
        if land[nx][ny] > 0 and land[nx][ny] != l:
            ans = min(ans, bridge[a][b])
            find = True
            break
        if land[nx][ny] == 0 and bridge[nx][ny] == -1:
            bridge[nx][ny] = bridge[a][b] + 1
            route.append([nx, ny])
    ```
    - 최단거리 최초 발견시 까지만 탐색을 진행한다
    - 탐색한 좌표가 섬이면서 현재와 동일한 섬이 아닌 경우 `bridge`에 저장된 값을 이전까지 탐색한 값과 비교한 후 최소값을 저장한다
    - 해당 좌표가 섬이 아닌 경우 `bridge` 배열에 길이를 추가하여 저장하고 좌표를 `route`에 추가한다
    - 최종적으로 최소값을 출력한다
## 4. 결과
![image](https://user-images.githubusercontent.com/29600820/89270367-34e30680-d676-11ea-83a2-e06edf0abfc4.png)
- 상단은 `PyPy3`로 제출
- 하단은 `Python3`로 제출