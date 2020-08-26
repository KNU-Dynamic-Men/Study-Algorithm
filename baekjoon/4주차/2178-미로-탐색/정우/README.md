# **2178 - 미로 탐색**

## **1. 개요**

[https://www.acmicpc.net/problem/2178](https://www.acmicpc.net/problem/2178)

## **2. 코드**

```python
import sys
from collections import deque

R, C = map(int, sys.stdin.readline().split())
maze = [[2]*(C+2)]
for _ in range(R):
    maze.append(list(map(int, f'2{sys.stdin.readline().strip()}2')))
maze.append([2]*(C+2))

q = deque([])
q.append([(1,1,1)])
maze[1][1] = 3
break_flag = False
while len(q) != 0 and break_flag == False:
    popped = q.popleft()
    for r, c, d in popped:
        if r == R and c == C:
            print(d)
            break_flag = True
            break
        nxt = []
        if maze[r+1][c] == 1:
            maze[r+1][c] = 3
            nxt.append((r+1, c, d+1))
        if maze[r-1][c] == 1:
            maze[r-1][c] = 3
            nxt.append((r-1, c, d+1))
        if maze[r][c+1] == 1:
            maze[r][c+1] = 3
            nxt.append((r, c+1, d+1))
        if maze[r][c-1] == 1:
            maze[r][c-1] = 3
            nxt.append((r, c-1, d+1))
        if nxt:
            q.append(nxt)
```

## **3. 설명**

1. 주어진 미로를 벽(`2`)으로 감싼다.
    - 미로의 가장자리인지 검사하지 않으려고
2. 시작점에서 BFS 탐색을 시작한다.
    1. 큐에서 이동할 지점의 집합 `A`를 꺼낸다.
    2. `A`집합 내에 있는 지점들로부터 갈 수 있는 지점(최대 3개)을 배열 `B`에 저장한다.
        - 저장되는 지점의 형식은 다음과 같다. `(행 위치, 열 위치, 해당 지점까지의 거리)`
    3. `B`배열을 큐에 넣는다.
    4. 반복
3. 지나온 길은 `3`으로 표시한다.

## **4. 여정**

1. 기분 좋은 원큐

## **5. 결과**
![image](https://user-images.githubusercontent.com/41278416/88766513-1af77e80-d1b3-11ea-80b4-ecea07f10632.png)