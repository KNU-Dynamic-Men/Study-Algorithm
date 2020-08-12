# 2667 - 단지번호붙이기

## 1. 개요

https://www.acmicpc.net/problem/2667

## 2. 코드

```python
from sys import stdin
from collections import deque

getN = int(stdin.readline())
maze = []
for _ in range(getN):
    maze.append(list(getMap(int, stdin.readline().rstrip())))
stage = [[0]*getN for _ in range(getN)]
search = deque()
length = []
getDx = [1, 0, -1, 0]
getDy = [0, 1, 0, -1]
for i in range(getN):
    for j in range(getN):
        if maze[i][j] == 1 and stage[i][j] == 0:
            search.append([i, j])
            stage[i][j] = 1
            count = 1
            while(search):
                x, y = getMap(int, search.popleft())
                for k in range(4):
                    nx = x + getDx[k]
                    ny = y + getDy[k]
                    if nx < 0 or nx >= getN or ny < 0 or ny >= getN: continue
                    if maze[nx][ny] == 0 or stage[nx][ny] == 1: continue
                    search.append([nx, ny])
                    stage[nx][ny] = 1
                    count += 1
            length.append(count)
print(len(length))
length.sort()
for i in length:
    print(i)
```

# 3. 설명

1. 코드 설계 구상

    - BFS로도, DFS로도 풀이가 가능한 문제
    - BFS로 먼저 풀고, DFS로도 변형해보자

2. 코드 설명

    - 전체적인 코드는 기존 BFS 문제들과 유사하다
    - [2178 - 미로 탐색](https://github.com/KNU-Dynamic-Men/Study-Algorithm/tree/master/baekjoon/4%EC%A3%BC%EC%B0%A8/2178-%EB%AF%B8%EB%A1%9C-%ED%83%90%EC%83%89/%EC%9A%B0%EC%84%AD)과 유사한 부분은 건너뛴다!!
    - 입력된 지도 전체를 탐색하면서, 처음 주택이 발견된 경우 `deque` 배열인 `search`에 좌표를 추가하고, `stage`의 해당 좌표의 값을 `1`로 바꾸어주고 길이값인 `count`값을 `1` 증가시킨 후 단지 크기 탐색을 시작한다
    ```python
    if maze[i][j] == 1 and stage[i][j] == 0:
        search.append([i, j])
        stage[i][j] = 1
        count = 1
    ```
    - `x, y`를 `deque` 배열인 `search`에서 가장 왼쪽인 값을 `popleft()`한 경우 **BFS**로 탐색하게 되고, 가장 오른쪽인 값을 `pop()`한 경우 **DFS**로 탐색하게 된다
    ```python
    x, y = getMap(int, search.popleft())   # BFS의 경우
    x, y = getMap(int, search.pop())       # DFS의 경우
    ```
    - 탐색이 완료되면 해당 단지의 크기를 length 배열에 추가하고 다음 주택을 찾는다
    ```python
    length.append(count)
    ```
    - 모든 좌표 탐색이 완료된 경우 단지의 총 개수를 출력하고, 내림차순으로 정렬 후 하나씩 프린트한다
    ```python
    print(len(length))
    length.sort()
    for i in length:
        print(i)
    ```

# 4. 결과
![image](https://user-images.githubusercontent.com/29600820/89041962-2b5d5400-d381-11ea-9dee-2999a0f84236.png)
