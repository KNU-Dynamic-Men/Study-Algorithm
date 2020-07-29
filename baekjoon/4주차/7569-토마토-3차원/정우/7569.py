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