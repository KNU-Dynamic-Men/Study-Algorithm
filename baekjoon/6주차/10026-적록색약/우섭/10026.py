from sys import stdin
from collections import deque
n = int(stdin.readline())
img = []
dx = [1, -1, 0, 0]
dy = [0, 0, 1, -1]

def BFS_RGB():
    check = [[False] * n for _ in range(n)]
    count = 0
    bfs_list = deque()
    for i in range(n):
        for j in range(n):
            if check[i][j] == False:
                rgb = img[i][j]
                bfs_list.append([i, j])
                check[i][j] == True
                count += 1
                while(bfs_list):
                    x, y = map(int, bfs_list.popleft())
                    for k in range(4):
                        nx = x + dx[k]
                        ny = y + dy[k]
                        if nx < 0 or nx >= n or ny < 0 or ny >= n: continue
                        if check[nx][ny] == True or img[nx][ny] != rgb: continue
                        bfs_list.append([nx, ny])
                        check[nx][ny] = True
    return count

def BFS_RGB_RG():
    check = [[False] * n for _ in range(n)]
    count = 0
    bfs_list = deque()
    for i in range(n):
        for j in range(n):
            if check[i][j] == False:
                rgb = check_RG(i, j)
                bfs_list.append([i, j])
                check[i][j] == True
                count += 1
                while(bfs_list):
                    x, y = map(int, bfs_list.popleft())
                    for k in range(4):
                        nx = x + dx[k]
                        ny = y + dy[k]
                        if nx < 0 or nx >= n or ny < 0 or ny >= n: continue
                        if check[nx][ny] == True or check_RG(nx, ny) != rgb: continue
                        bfs_list.append([nx, ny])
                        check[nx][ny] = True
    return count

def check_RG(x, y):
    if img[x][y] == 'B':
        return False
    else:
        return True

for _ in range(n):
    img.append(list(map(str, stdin.readline().rstrip())))
print(f'{BFS_RGB()} {BFS_RGB_RG()}')