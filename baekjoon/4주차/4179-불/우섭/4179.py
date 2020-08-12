from collections import deque
from sys import stdin

R, C = getMap(int, stdin.readline().split())
stage = []
fi = [[0]*C for _ in range(R)]
pi = [[0]*C for _ in range(R)]
fi_q = deque()
pi_q = deque()
getDx = [1, 0, -1, 0]
getDy = [0, 1, 0, -1]

def read_stage():
    for _ in range(R):
        stage.append(list(stdin.readline().rstrip()))

    for i in range(R):
        for j in range(C):
            if stage[i][j] == 'F':
                fi_q.append([i, j])
                fi[i][j] = 1
            if stage[i][j] == 'J':
                pi_q.append([i, j])
                pi[i][j] = 1

def fire_dfs():
    while(fi_q):
        cur = fi_q.popleft()
        for i in range(4):
            nx = cur[0] + getDx[i]
            ny = cur[1] + getDy[i]
            if nx < 0 or nx >= R or ny < 0 or ny >= C:
                continue
            if fi[nx][ny] > 0 or stage[nx][ny] == '#':
                continue
            fi[nx][ny] = fi[cur[0]][cur[1]] + 1
            fi_q.append([nx, ny])

def people_dfs():
    while(pi_q):
        cur = pi_q.popleft()
        for i in range(4):
            nx = cur[0] + getDx[i]
            ny = cur[1] + getDy[i]
            if nx < 0 or nx >= R or ny < 0 or ny >= C:
                return pi[cur[0]][cur[1]]
            if pi[nx][ny] > 0 or stage[nx][ny] == '#':
                continue
            if fi[nx][ny] != 0 and fi[nx][ny] <= (pi[cur[0]][cur[1]] + 1):
                continue
            pi[nx][ny] = pi[cur[0]][cur[1]] + 1
            pi_q.append([nx, ny])
    return -1

read_stage()
fire_dfs()
ans = people_dfs()
print('IMPOSSIBLE' if ans == -1 else ans)