# **7569 - 3차원 토마토**

## **1. 개요**

[https://www.acmicpc.net/problem/7569](https://www.acmicpc.net/problem/7569)

## **2. 코드**

Python3
```python
import sys
from collections import deque

directions = (
    (1,0,0),(-1,0,0),
    (0,1,0),(0,-1,0),
    (0,0,1),(0,0,-1),
)
C, R, H = map(int, sys.stdin.readline().split())
maze = [[[-1]*(C+2)]*(R+2)]
for _ in range(H):
    tmp_maze = [[-1]*(C+2)]
    for _ in range(R):
        tmp_maze.append(list(map(int, f'-1 {sys.stdin.readline().strip()} -1'.split())))
    tmp_maze.append([-1]*(C+2))
    maze.append(tmp_maze)
maze.append([[-1]*(C+2)]*(R+2))

zeros = 0
ones = deque([])
for h in range(1, len(maze)):
    for r in range(1, len(maze[0])):
        for c in range(1, len(maze[0][0])):
            if maze[h][r][c] == 0:
                zeros += 1
            elif maze[h][r][c] == 1:
                ones.append([(h,r,c,1)])

times = [0]
while zeros != 0 and len(ones) != 0:
    popped = ones.popleft()
    for h, r, c, d in popped:
        nxt = []
        for direction in directions:
            if maze[h+direction[0]][r+direction[1]][c+direction[2]] == 0:
                maze[h+direction[0]][r+direction[1]][c+direction[2]] = 1
                nxt.append((h+direction[0], r+direction[1], c+direction[2], d+1))
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

1. 안 익은 토마토의 개수를 모두 센다.
2. 익은 토마토로부터 BFS 탐색을 시작한다.
3. BFS 탐색 도중에 토마토를 익혔다면(가지 않은 경로로 갔다면) 안 익은 토마토의 개수를 하나 줄인다.
4. 탐색이 모두 종료된 후에 안 익은 토마토의 개수가 0이면 탐색에 사용된 시간 출력
5. 아니라면 -1 출력

## **4. 여정**

1. 성공

## **5. 결과**
![image](https://user-images.githubusercontent.com/41278416/88828500-4a83a680-d206-11ea-9247-595c71fb4eac.png)