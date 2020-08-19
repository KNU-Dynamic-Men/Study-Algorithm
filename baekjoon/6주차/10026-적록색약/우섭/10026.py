from sys import stdin
from collections import deque
getN = int(stdin.readline())
img = []
getDx = [1, -1, 0, 0]
getDy = [0, 0, 1, -1]

def BFS_RGB():
    check = [[False] * getN for _ in range(getN)]
    count = 0
    bfs_list = deque()
    for i in range(getN):
        for j in range(getN):
            if check[i][j] == False:
                rgb = img[i][j]
                bfs_list.append([i, j])
                check[i][j] == True
                count += 1
                while(bfs_list):
                    x, y = getMap(int, bfs_list.popleft())
                    for k in range(4):
                        nx = x + getDx[k]
                        ny = y + getDy[k]
                        if nx < 0 or nx >= getN or ny < 0 or ny >= getN: continue
                        if check[nx][ny] == True or img[nx][ny] != rgb: continue
                        bfs_list.append([nx, ny])
                        check[nx][ny] = True
    return count

def BFS_RGB_RG():
    check = [[False] * getN for _ in range(getN)]
    count = 0
    bfs_list = deque()
    for i in range(getN):
        for j in range(getN):
            if check[i][j] == False:
                rgb = check_RG(i, j)
                bfs_list.append([i, j])
                check[i][j] == True
                count += 1
                while(bfs_list):
                    x, y = getMap(int, bfs_list.popleft())
                    for k in range(4):
                        nx = x + getDx[k]
                        ny = y + getDy[k]
                        if nx < 0 or nx >= getN or ny < 0 or ny >= getN: continue
                        if check[nx][ny] == True or check_RG(nx, ny) != rgb: continue
                        bfs_list.append([nx, ny])
                        check[nx][ny] = True
    return count

def check_RG(x, y):
    if img[x][y] == 'B':
        return False
    else:
        return True

for _ in range(getN):
    img.append(list(getMap(str, stdin.readline().rstrip())))
print(f'{BFS_RGB()} {BFS_RGB_RG()}')