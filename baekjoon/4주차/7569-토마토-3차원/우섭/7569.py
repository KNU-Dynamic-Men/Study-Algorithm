from collections import deque
from sys import stdin
M, N, H = getMap(int, stdin.readline().split())
box = []
getDx = [1, 0, -1, 0, 0, 0]
getDy = [0, 1, 0, -1, 0, 0]
dz = [0, 0, 0, 0, 1, -1]

tomato = [[[-1]*M for _ in range(N)] for _ in range(H)]
search = deque()

box = [[list(getMap(int, stdin.readline().rstrip().split())) for i in range(N)] for j in range(H)]

for k in range(H):
    for i in range(N):
        for j in range(M):
            if box[k][i][j] == 1:
                search.append([k, i, j])
                tomato[k][i][j] = 0
            if box[k][i][j] == -1:
                tomato[k][i][j] = 0
while(search):
    z, x, y = getMap(int, search.popleft())
    for i in range(6):
        nz = z + dz[i]
        nx = x + getDx[i]
        ny = y + getDy[i]
        if nx < 0 or nx >= N or ny < 0 or ny >= M or nz < 0 or nz >= H:
            continue
        if tomato[nz][nx][ny] >= 0:
            continue
        tomato[nz][nx][ny] = tomato[z][x][y] + 1
        search.append([nz, nx, ny])

find = True
maximum = 0
for k in range(H):
    for i in range(N):
        for j in range(M):
            if tomato[k][i][j] == -1:
                print(-1)
                find = False
        if not find: break
        maximum = max(maximum, max(tomato[k][i]))
    if not find: break
if find:
    print(maximum)