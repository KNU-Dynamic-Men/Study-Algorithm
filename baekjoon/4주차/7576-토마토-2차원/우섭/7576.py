from collections import deque
from sys import stdin
M, N = getMap(int, stdin.readline().split())
box = []
getDx = [1, 0, -1, 0]
getDy = [0, 1, 0, -1]
tomato = [[-1]*M for _ in range(N)]
search = deque()
for _ in range(N):
    box.append(list(getMap(int, stdin.readline().rstrip().split(' '))))
for i in range(N):
    for j in range(M):
        if box[i][j] == 1:
            search.append([i, j])
            tomato[i][j] = 0
        if box[i][j] == -1:
            tomato[i][j] = 0
while(search):
    x, y = getMap(int, search.popleft())
    for i in range(4):
        nx = x + getDx[i]
        ny = y + getDy[i]
        if nx < 0 or nx >= N or ny < 0 or ny >= M:
            continue
        if tomato[nx][ny] >= 0:
            continue
        tomato[nx][ny] = tomato[x][y] + 1
        search.append([nx, ny])
find = True
maximum = 0
for i in range(N):
    for j in range(M):
        if tomato[i][j] == -1:
            print(-1)
            find = False
    if not find: break
    maximum = max(maximum, max(tomato[i]))
if find:
    print(maximum)