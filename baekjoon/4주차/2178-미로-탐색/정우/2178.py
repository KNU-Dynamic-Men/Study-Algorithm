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
