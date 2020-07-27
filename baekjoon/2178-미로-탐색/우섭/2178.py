from collections import deque
from sys import stdin
N, M = map(int, stdin.readline().split())
maze = []
dx = [1, 0, -1, 0]
dy = [0, 1, 0, -1]
stage = [[0]*M for _ in range(N)]
search = deque()
for _ in range(N):
    maze.append(list(map(int, stdin.readline().rstrip())))
stage[0][0] = 1
search.append([0, 0])
find = False
while(search and not find):
    now = search.popleft()
    for i in range(4):
        nx = now[0] + dx[i]
        ny = now[1] + dy[i]
        if nx == (N-1) and ny == (M-1):
            print(stage[now[0]][now[1]] + 1)
            find = True
            break
        if nx < 0 or nx >= N or ny < 0 or ny >= M:
            continue
        if stage[nx][ny] > 0 or maze[nx][ny] == 0:
            continue
        stage[nx][ny] = stage[now[0]][now[1]] + 1
        search.append([nx, ny])