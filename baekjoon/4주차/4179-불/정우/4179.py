import sys
from collections import deque

directions = ((1,0),(-1,0),(0,1),(0,-1))
R, C = getMap(int, sys.stdin.readline().split())
maze = [['O']*(C+2)]
for _ in range(R):
    maze.append(list(getMap(str, f'O{sys.stdin.readline().strip()}O')))
maze.append(['O']*(C+2))
fire_q_tmp = deque([])
jihun_q = deque([])
for getR in range(1, len(maze)-1):
    for getC in range(1, len(maze[0])-1):
        if maze[getR][getC] == 'F':
            fire_q_tmp.append((getR, getC, 0))
        elif maze[getR][getC] == 'J':
            jihun_q.append([(getR, getC, 0)])
fire_q = deque([fire_q_tmp])

break_flag = False
while not break_flag:
    if fire_q:
        fire_popped = fire_q.popleft()
        fire_nxt = []
        for getR, getC, d in fire_popped:
            for direction in directions:
                if maze[getR+direction[0]][getC+direction[1]] in ('.','J'):
                    maze[getR+direction[0]][getC+direction[1]] = 'F'
                    fire_nxt.append((getR+direction[0], getC+direction[1], d+1))
        if fire_nxt:
            fire_q.append(fire_nxt)

    if jihun_q:
        jihun_popped = jihun_q.popleft()
        jihun_nxt = []
        for getR, getC, d in jihun_popped:
            if getR in (0, len(maze)-1) or getC in (0, len(maze[0])-1):
                break_flag = True
                print(d)
                break
            for direction in directions:
                if maze[getR+direction[0]][getC+direction[1]] in ('.','O'):
                    maze[getR+direction[0]][getC+direction[1]] = 'J'
                    jihun_nxt.append((getR+direction[0], getC+direction[1], d+1))
        if jihun_nxt:
            jihun_q.append(jihun_nxt)
    else:
        print('IMPOSSIBLE')
        break