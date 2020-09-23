# 카카오 2020 인턴십 - 경주로 건설

## 1. 개요

https://programmers.co.kr/learn/courses/30/lessons/67259

## 2. 코드

```python
from collections import deque

def check(N, y, x, board):
    return 0 <= x < N and 0 <= y < N and board[y][x] == 0

def solution(board):
    N = len(board)
    direction = [[1, 0, 'DOWN'], [0, 1, 'RIGHT'], [-1, 0, 'UP'], [0, -1, 'LEFT']]
    confirm = {(0, 0): 0}
    route = deque()

    route.append([0, 0, 'RIGHT', 0])
    confirm[(0, 1)] = 100
    route.append([0, 0, 'DOWN', 0])
    confirm[(1, 0)] = 100
    answer = N * N * 500

    while route:
        tmp = route.popleft()
        if tmp[0] == N - 1 and tmp[1] == N - 1:
            answer = min(tmp[3], answer)
            continue
        for i in direction:
            dy = i[0] + tmp[0]
            dx = i[1] + tmp[1]
            if check(N, dy, dx, board):
                now = tmp[3]
                if tmp[2] == i[2]:
                    now += 100
                else:
                    now += 600
                arr = (dy, dx)
                if arr in confirm and confirm[arr] < now:
                    continue
                route.append([dy, dx, i[2], now])
                confirm[arr] = now

    return answer
```

## 3. 설명

1. 구현 방법 구상
    
    - 경로 탐색은 `DFS` 아니면 `BFS`를 주로 사용한다
    - **최소 비용을 확인**하는 문제이므로 `BFS`를 사용한다
    - 직진 도로는 `100`, 꺾는 도로는 `500` -> 비용을 따로 저장하면서 풀이를 한다
    - 이미 지난 도로를 체크를 하여 최소값인 경우에만 해당 좌표를 추가로 탐색하도록 한다
    - 이미 지난 도로는 `dict`를 활용한 `동적 프로그래밍`을 사용하여 체크한다

2. 구현
    ```python
    N = len(board)
    direction = [[1, 0, 'DOWN'], [0, 1, 'RIGHT'], [-1, 0, 'UP'], [0, -1, 'LEFT']]
    confirm = {(0, 0): 0}
    route = deque()
    ```
    - `N`: `board`의 가로 및 세로 크기, 입력은 반드시 정사각형이므로 `len`으로 바로 구할 수 있다
    - `direction`: 좌표 계산값 및 방향을 저장한 배열
    - `confirm`: 해당 좌표에 도달하는데 걸린 최소 비용을 저장하는 `동적 프로그래밍` `dict`
    - `route`: `BFS`를 이용하기 위해 다음 탐색할 좌표를 저장하는 `deque`
    ```python
    route.append([0, 0, 'RIGHT', 0])
    confirm[(0, 1)] = 100
    route.append([0, 0, 'DOWN', 0])
    confirm[(1, 0)] = 100
    answer = N * N * 500
    ```
    - 초기 값을 저장해준다
    - 반드시 `0, 0` 좌표에서 시작하므로 해당 좌표와 함께, 왼쪽 위에서 출발할 수 있는 오른쪽 및 아래쪽 경로를 `route`에 저장하여 주고, `confirm`에 최소 비용을 저장하여 준다
    - 정답의 초기값 또한 최대한 큰 값을 저장하여 준다
    ```python
    while route:
    ```
    - `route`에 값이 존재하는 동안 계속 탐색한다
    ```python
    tmp = route.popleft()
    if tmp[0] == N - 1 and tmp[1] == N - 1:
        answer = min(tmp[3], answer)
        continue
    ```
    - route의 가장 마지막에 추가된 값을 확인한다
    - 만약 해당 위치가 도착지점인 경우 정답과 현재 비용의 최소값을 비교한 후 저장한다
    ```python
    for i in direction:
        dy = i[0] + tmp[0]
        dx = i[1] + tmp[1]
    ```
    - 4 방향을 탐색한다
    - dy, dx에 4 방향으로 이동했을 때의 좌표를 저장한다
    ```python
    if check(N, dy, dx, board):
        now = tmp[3]
        if tmp[2] == i[2]:
            now += 100
        else:
            now += 600
    ```
    - 해당 위치가 이동 가능한 좌표인 경우 비용을 계산하여 준다
    - 방향을 비교하여 직진인 경우 `100`의 비용을 추가한다
    - 커브인 경우 커브길 + 직진길으로서 총 `600`의 비용을 추가한다
    ```python
    arr = (dy, dx)
    if arr in confirm and confirm[arr] < now:
        continue
    route.append([dy, dx, i[2], now])
    confirm[arr] = now
    ```
    - 이동한 좌표를 임시로 `arr`에 묶어둔다
    - 해당 좌표가 `confirm`에 있으면서 현재 비용보다 `confirm`에 저장된 비용이 작은 경우 경로를 저장하지 않고 다음 탐색을 진행한다
    - 그 외의 경우 다음 경로를 탐색할 수 있도록 `route`에 추가하여 주고, `confirm`의 해당 좌표의 최소 비용을 갱신하여 준다


## 4. 결과

- 정확성 테스트
    ```
    테스트 1 〉	통과 (0.04ms, 10.2MB)
    테스트 2 〉	통과 (0.03ms, 10.3MB)
    테스트 3 〉	통과 (0.02ms, 10.3MB)
    테스트 4 〉	통과 (0.06ms, 10.3MB)
    테스트 5 〉	통과 (0.06ms, 10.3MB)
    테스트 6 〉	통과 (0.44ms, 10.3MB)
    테스트 7 〉	통과 (0.48ms, 10.2MB)
    테스트 8 〉	통과 (0.43ms, 10.3MB)
    테스트 9 〉	통과 (1.46ms, 10.3MB)
    테스트 10 〉	통과 (1.16ms, 10.3MB)
    테스트 11 〉	통과 (49.08ms, 10.4MB)
    테스트 12 〉	통과 (19.01ms, 10.3MB)
    테스트 13 〉	통과 (0.35ms, 10.3MB)
    테스트 14 〉	통과 (0.36ms, 10.3MB)
    테스트 15 〉	통과 (2.40ms, 10.2MB)
    테스트 16 〉	통과 (2.41ms, 10.2MB)
    테스트 17 〉	통과 (12.71ms, 10.3MB)
    테스트 18 〉	통과 (7.05ms, 10.3MB)
    테스트 19 〉	통과 (55.69ms, 10.3MB)
    테스트 20 〉	통과 (1.89ms, 10.3MB)
    테스트 21 〉	통과 (1.33ms, 10.2MB)
    테스트 22 〉	통과 (0.11ms, 10.3MB)
    테스트 23 〉	통과 (0.09ms, 10.3MB)
    ```
![image](https://user-images.githubusercontent.com/29600820/93983588-baa14980-fdbd-11ea-8b90-94491b0ba459.png)