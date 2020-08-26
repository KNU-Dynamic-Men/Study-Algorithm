from sys import stdin
from collections import deque
dx = [1, -1, 0, 0]
dy = [0, 0, 1, -1]
n = int(stdin.readline())
land = []
for _ in range(n):
    land.append(list(map(int, stdin.readline().split(' '))))
check = [[False]*n for _ in range(n)]
route = deque()

k = 0
for i in range(n):
    for j in range(n):
        if land[i][j] == 1 and not check[i][j]:
            k += 1
            land[i][j] = k
            route.append([i, j])
            check[i][j] = True
            while(route):
                a, b = map(int, route.pop())
                for t in range(4):
                    nx = a + dx[t]
                    ny = b + dy[t]
                    if nx < 0 or nx >= n or ny < 0 or ny >= n: continue
                    if not check[nx][ny] and land[nx][ny] == 1:
                        land[nx][ny] = k
                        check[nx][ny] = True
                        route.append([nx, ny])

ans = 500
for l in range(1, k+1):
    bridge = [[-1]*n for _ in range(n)]
    route = deque()
    for i in range(n):
        for j in range(n):
            if land[i][j] == l:
                route.append([i, j])
                bridge[i][j] = 0
                
    find = False
    while(route and not find):
        a, b = map(int, route.popleft())
        for t in range(4):
            nx = a + dx[t]
            ny = b + dy[t]
            if nx < 0 or nx >= n or ny < 0 or ny >= n: continue
            if land[nx][ny] > 0 and land[nx][ny] != l:
                ans = min(ans, bridge[a][b])
                find = True
                break
            if land[nx][ny] == 0 and bridge[nx][ny] == -1:
                bridge[nx][ny] = bridge[a][b] + 1
                route.append([nx, ny])
print(ans)