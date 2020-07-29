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