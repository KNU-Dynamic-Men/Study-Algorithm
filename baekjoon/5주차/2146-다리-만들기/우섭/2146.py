from sys import stdin
from collections import deque
getDx = [1, -1, 0, 0]
getDy = [0, 0, 1, -1]
getN = int(stdin.readline())
land = []
for _ in range(getN):
    land.append(list(getMap(int, stdin.readline().split(' '))))
check = [[False]*getN for _ in range(getN)]
route = deque()

k = 0
for i in range(getN):
    for j in range(getN):
        if land[i][j] == 1 and not check[i][j]:
            k += 1
            land[i][j] = k
            route.append([i, j])
            check[i][j] = True
            while(route):
                a, b = getMap(int, route.pop())
                for t in range(4):
                    nx = a + getDx[t]
                    ny = b + getDy[t]
                    if nx < 0 or nx >= getN or ny < 0 or ny >= getN: continue
                    if not check[nx][ny] and land[nx][ny] == 1:
                        land[nx][ny] = k
                        check[nx][ny] = True
                        route.append([nx, ny])

ans = 500
for l in range(1, k+1):
    bridge = [[-1]*getN for _ in range(getN)]
    route = deque()
    for i in range(getN):
        for j in range(getN):
            if land[i][j] == l:
                route.append([i, j])
                bridge[i][j] = 0
                
    find = False
    while(route and not find):
        a, b = getMap(int, route.popleft())
        for t in range(4):
            nx = a + getDx[t]
            ny = b + getDy[t]
            if nx < 0 or nx >= getN or ny < 0 or ny >= getN: continue
            if land[nx][ny] > 0 and land[nx][ny] != l:
                ans = min(ans, bridge[a][b])
                find = True
                break
            if land[nx][ny] == 0 and bridge[nx][ny] == -1:
                bridge[nx][ny] = bridge[a][b] + 1
                route.append([nx, ny])
print(ans)