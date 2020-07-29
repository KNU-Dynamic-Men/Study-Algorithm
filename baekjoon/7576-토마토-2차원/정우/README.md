# **7576 - 토마토-2차원**

## **1. 개요**

[https://www.acmicpc.net/problem/7576](https://www.acmicpc.net/problem/7576)

## **2. 코드**

```python
import sys
from collections import deque

C, R = map(int, sys.stdin.readline().split())
maze = [[-1]*(C+2)]
for _ in range(R):
    maze.append(list(map(int, f'-1 {sys.stdin.readline().strip()} -1'.split())))
maze.append([-1]*(C+2))

zeros = 0
ones = deque([])
for r in range(1, len(maze)):
    for c in range(1, len(maze[0])):
        if maze[r][c] == 0:
            zeros += 1
        elif maze[r][c] == 1:
            ones.append([(r,c,1)])

times = [0]
while zeros != 0 and len(ones) != 0:
    popped = ones.popleft()
    for r, c, d in popped:
        nxt = []
        if maze[r+1][c] == 0:
            maze[r+1][c] = 1
            nxt.append((r+1, c, d+1))
            zeros -= 1
        if maze[r-1][c] == 0:
            maze[r-1][c] = 1
            nxt.append((r-1, c, d+1))
            zeros -= 1
        if maze[r][c+1] == 0:
            maze[r][c+1] = 1
            nxt.append((r, c+1, d+1))
            zeros -= 1
        if maze[r][c-1] == 0:
            maze[r][c-1] = 1
            nxt.append((r, c-1, d+1))
            zeros -= 1
        if nxt:
            ones.append(nxt)
        else:
            times.append(d)
    times.append(d)
if zeros == 0:
    print(max(times))
else:
    print(-1)
```

## **3. 설명**

1. 익지 않은 토마토(`0`)의 개수를 세어 `zeros`라고 저장한다.
2. 익은 토마토 기준으로 BFS 탐색을 한다.
3. 익지 않은 토마토를 익혔을때 `zeros`를 하나씩 뺀다.
4. 탐색을 모두 마친 후 `zeros`가 0이 아니라면 모두 익히지 못한 것이고, 0이라면 BFS 탐색에 걸린 시간을 출력한다.

## **4. 여정**

1. 출력미스
2. 성공

## **5. 결과**
![image](https://user-images.githubusercontent.com/41278416/88771231-e63af580-d1b9-11ea-9449-23288d705cc5.png)