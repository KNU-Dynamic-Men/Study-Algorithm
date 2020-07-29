import sys
from collections import deque

def copy2d(arr):
    return [a.copy() for a in arr]

def print_maze(maze):
    for m in maze:
        print(m)
    print()

R, C = map(int, sys.stdin.readline().split())
maze = [['O']*(C+2)]
for _ in range(R):
    maze.append(list(map(str, f'O{sys.stdin.readline().strip()}O')))
maze.append(['O']*(C+2))
fire_maze = copy2d(maze)
time = 0

# Queue initialize
fire_q = deque([])
jihun_q = deque([])
for r in range(1, len(maze)):
    for c in range(1, len(maze[0])):
        if maze[r][c] == 'F':
            fire_q.append([(r, c, 1),])
        elif maze[r][c] == 'J':
            jihun_q.append([(r, c, [(r, c)]),])

# Initialize the fire_maze of times for the fire to spread
while len(fire_q) != 0:
    popped = fire_q.popleft()
    nxt = []
    for r, c, d in popped:
        if fire_maze[r+1][c] in ('.', 'J'):
            fire_maze[r+1][c] = d
            nxt.append((r+1, c, d+1))
        if fire_maze[r-1][c] in ('.', 'J'):
            fire_maze[r-1][c] = d
            nxt.append((r-1, c, d+1))
        if fire_maze[r][c+1] in ('.', 'J'):
            fire_maze[r][c+1] = d
            nxt.append((r, c+1, d+1))
        if fire_maze[r][c-1] in ('.', 'J'):
            fire_maze[r][c-1] = d
            nxt.append((r, c-1, d+1))
    if nxt:
        fire_q.append(nxt)
# print_maze(fire_maze)

# Initialize the maze of times of the jihun to runaway
break_flag = False
while len(jihun_q) != 0 and not break_flag:
    popped = jihun_q.popleft()
    nxt = []
    for r, c, path in popped:
        if r in (0, len(maze)-1) or c in (0, len(maze[0])-1):
            time = d
            break_flag = True
            break
        if maze[r+1][c] in ('.', 'O'):
            maze[r+1][c] = 'J'
            nxt.append((r+1, c, path+[(r+1,c)]))
        if maze[r-1][c] in ('.', 'O'):
            maze[r-1][c] = 'J'
            nxt.append((r-1, c, path+[(r-1,c)]))
        if maze[r][c+1] in ('.', 'O'):
            maze[r][c+1] = 'J'
            nxt.append((r, c+1, path+[(r,c+1)]))
        if maze[r][c-1] in ('.', 'O'):
            maze[r][c-1] = 'J'
            nxt.append((r, c-1, path+[(r,c-1)]))
    if nxt:
        jihun_q.append(nxt)
# print(path)
# print_maze(maze)

# Validate whether he success
prt = ''
for i, e in enumerate(path):
    if fire_maze[e[0]][e[1]] == 'O':
        prt = i
        break
    elif fire_maze[e[0]][e[1]] <= i:
        prt = 'IMPOSSIBLE'
        break
print(prt)