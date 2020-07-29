import sys
from collections import deque

def check4way(maze, r, c):
    ret = []
    if maze[r-1][c] in ('.', 'O', 'J'):
        ret.append((-1, 0))
    if maze[r+1][c] in ('.', 'O', 'J'):
        ret.append((1, 0))
    if maze[r][c+1] in ('.', 'O', 'J'):
        ret.append((0, 1))
    if maze[r][c-1] in ('.', 'O', 'J'):
        ret.append((0, -1))
    return ret

def spread(maze, q, who):
    ret = deque([])
    while len(q) != 0:
        r, c = q.popleft()
        possible = check4way(maze, r, c)
        for p_r, p_c in possible:
            if maze[r+p_r][c+p_c] == 'O' and who == 'J':
                return 'end'
            elif maze[r+p_r][c+p_c] in ('.', 'J'):
                maze[r+p_r][c+p_c] = who
                ret.append((r+p_r, c+p_c))
    return ret

def start(maze, r, c, jihun_r, jihun_c):
    time = 0
    fire_q = deque()
    fire_q.append((r,c))
    jihun_q = deque()
    jihun_q.append((jihun_r, jihun_c))
    while True:
        # print_maze(maze)
        time += 1
        fire_q = spread(maze, fire_q, 'F')
        jihun_q = spread(maze, jihun_q, 'J')
        if jihun_q == 'end':
            return time
        elif len(jihun_q) == 0:
            return 'IMPOSSIBLE'

def copy2d(arr):
    return [a.copy() for a in arr]

def find_jihun(maze):
    for r in range(1, len(maze)):
        for c in range(1, len(maze[0])):
            if maze[r][c] == 'J':
                return r,c

def print_maze(maze):
    for m in maze:
        print(m)
    print()

def main(maze):
    times = []
    jihun_r, jihun_c = find_jihun(maze)
    for r in range(1, len(maze)):
        for c in range(1, len(maze[0])):
            if maze[r][c] == 'F':
                copied = copy2d(maze)
                res = start(copied, r, c, jihun_r, jihun_c)
                if res != 'IMPOSSIBLE':
                    times.append(res)
                else:
                    return 'IMPOSSIBLE'
    return times

R, C = map(int, sys.stdin.readline().split())
maze = [['O']*(C+2)]
for _ in range(R):
    maze.append(list(map(str, f'O{sys.stdin.readline().strip()}O')))
maze.append(['O']*(C+2))
res = main(maze)
if res == 'IMPOSSIBLE':
    print(res)
else:
    print(min(res))