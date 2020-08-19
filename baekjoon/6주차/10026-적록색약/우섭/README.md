# 10026 - 적록색약

## 1. 개요

https://www.acmicpc.net/problem/10026

## 2. 코드

```python
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
```

## 3. 설명

1. 코드 구현 구상

    - BFS를 이용하여 일반인의 경우 그림 전체를 탐색하여 R, G, B를 발견한 경우 해당 구역을 탐색하지 않았으면 탐색을 진행한다
    - 적록색약의 동일하게 그림 전체를 탐색하여 R, G, B를 발견한 경우 해당 구역을 탐색하지 않았으면 탐색을 진행하되, R과 G를 하나로 묶어서 동일한 색으로 판단하고 탐색을 진행한다


2. 구현

    - 기존 문제들과 비슷하게 BFS 구현
    - 탐색 시, 해당 이미지의 탐색 시작점의 RGB 값을 저장한 후, 이를 대조하여 BFS를 진행
    ```python
    # 일반인의 경우
    rgb = img[i][j]
    ...
    if check[nx][ny] == True or img[nx][ny] != rgb: continue
    # 적록색약의 경우
    rgb = check_RG(i, j)
    ...
    if check[nx][ny] == True or check_RG(nx, ny) != rgb: continue
    ```
    - 일반인과 적록색약 BFS의 차이점은 check_RG를 통해 R,G를 하나로 보는지 아닌지의 차이

## 4. 결과

![image](https://user-images.githubusercontent.com/29600820/89530443-70720200-d829-11ea-96a4-dd91b47f224d.png)