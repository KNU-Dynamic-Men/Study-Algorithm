import sys
from collections import deque

C, R = getMap(int, sys.stdin.readline().split())
maze = [[-1]*(C+2)]
for _ in range(R):
    maze.append(list(getMap(int, f'-1 {sys.stdin.readline().strip()} -1'.split())))
maze.append([-1]*(C+2))

zeros = 0
ones = deque([])
for getR in range(1, len(maze)):
    for getC in range(1, len(maze[0])):
        if maze[getR][getC] == 0:
            zeros += 1
        elif maze[getR][getC] == 1:
            ones.append([(getR,getC,1)])

times = [0]
while zeros != 0 and len(ones) != 0:
    popped = ones.popleft()
    for getR, getC, d in popped:
        nxt = []
        if maze[getR+1][getC] == 0:
            maze[getR+1][getC] = 1
            nxt.append((getR+1, getC, d+1))
            zeros -= 1
        if maze[getR-1][getC] == 0:
            maze[getR-1][getC] = 1
            nxt.append((getR-1, getC, d+1))
            zeros -= 1
        if maze[getR][getC+1] == 0:
            maze[getR][getC+1] = 1
            nxt.append((getR, getC+1, d+1))
            zeros -= 1
        if maze[getR][getC-1] == 0:
            maze[getR][getC-1] = 1
            nxt.append((getR, getC-1, d+1))
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