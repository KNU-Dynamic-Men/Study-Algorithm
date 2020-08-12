import sys
from collections import deque

R, C = getMap(int, sys.stdin.readline().split())
maze = [[2]*(C+2)]
for _ in range(R):
    maze.append(list(getMap(int, f'2{sys.stdin.readline().strip()}2')))
maze.append([2]*(C+2))

q = deque([])
q.append([(1,1,1)])
maze[1][1] = 3
break_flag = False
while len(q) != 0 and break_flag == False:
    popped = q.popleft()
    for getR, getC, d in popped:
        if getR == R and getC == C:
            print(d)
            break_flag = True
            break
        nxt = []
        if maze[getR+1][getC] == 1:
            maze[getR+1][getC] = 3
            nxt.append((getR+1, getC, d+1))
        if maze[getR-1][getC] == 1:
            maze[getR-1][getC] = 3
            nxt.append((getR-1, getC, d+1))
        if maze[getR][getC+1] == 1:
            maze[getR][getC+1] = 3
            nxt.append((getR, getC+1, d+1))
        if maze[getR][getC-1] == 1:
            maze[getR][getC-1] = 3
            nxt.append((getR, getC-1, d+1))
        if nxt:
            q.append(nxt)
