from sys import stdin
from collections import deque

n = int(stdin.readline())
maze = []
for _ in range(n):
    maze.append(list(map(int, stdin.readline().rstrip())))
stage = [[0]*n for _ in range(n)]
search = deque()
length = []
dx = [1, 0, -1, 0]
dy = [0, 1, 0, -1]
for i in range(n):
    for j in range(n):
        if maze[i][j] == 1 and stage[i][j] == 0:
            search.append([i, j])
            stage[i][j] = 1
            count = 1
            while(search):
                x, y = map(int, search.pop())
                for k in range(4):
                    nx = x + dx[k]
                    ny = y + dy[k]
                    if nx < 0 or nx >= n or ny < 0 or ny >= n: continue
                    if maze[nx][ny] == 0 or stage[nx][ny] == 1: continue
                    search.append([nx, ny])
                    stage[nx][ny] = 1
                    count += 1
            length.append(count)
print(len(length))
length.sort()
for i in length:
    print(i)