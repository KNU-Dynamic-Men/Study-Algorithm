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
                x, y = getMap(int, search.pop())
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