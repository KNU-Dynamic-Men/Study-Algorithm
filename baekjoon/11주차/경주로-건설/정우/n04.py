import sys
from collections import deque

dirs = (
    (0,1),  # right
    (1,0),  # down
    (0,-1), # left
    (-1,0), # up
)

glo_cost = sys.maxsize

def maze_print(arr):
    for a in arr:
        print(a)
    print()

def bfs(maze, x, y, direction, cost):
    global glo_cost
    q = deque([[[x,y,direction,cost]]])
    while q:
        popped = q.popleft()
        for p in popped:
            if p[0] == len(maze)-2 and p[1] == len(maze[1])-2:
                if p[3] < glo_cost:
                    glo_cost = p[3]
                continue
            nxt = []
            for i in range(4):
                t1 = p[0]+dirs[i][0]
                t2 = p[1]+dirs[i][1]
                if p[2] == i:
                    c = 100
                else:
                    c = 600
                if maze[t1][t2] >= p[3]+c or maze[t1][t2] == 0:
                    nxt.append([t1,t2,i,p[3]+c])
                    maze[t1][t2] = p[3]+c
            if nxt:
                q.append(nxt)

def solution(board):
    global glo_cost
    glo_cost = sys.maxsize
    board[0][0] = 1
    maze = [[1]*(len(board[0])+2)]
    for i in range(len(board)):
        maze.append([1]+board[i]+[1])
    maze.append([1]*(len(board[0])+2))

    bfs(maze, 1, 1, 0, 0)
    bfs(maze, 1, 1, 1, 0)
    return glo_cost


print(solution(
    [
        [0,0,0],
        [0,0,0],
        [0,0,0]
    ], # board
)) # result: 900

print(solution(
    [
        [0,0,0,0,0,0,0,1],
        [0,0,0,0,0,0,0,0],
        [0,0,0,0,0,1,0,0],
        [0,0,0,0,1,0,0,0],
        [0,0,0,1,0,0,0,1],
        [0,0,1,0,0,0,1,0],
        [0,1,0,0,0,1,0,0],
        [1,0,0,0,0,0,0,0]
    ], # board
)) # result: 3800

print(solution(
    [
        [0,0,1,0],
        [0,0,0,0],
        [0,1,0,1],
        [1,0,0,0]
    ], # board
)) # result: 2100

print(solution(
    [
        [0,0,0,0,0,0],
        [0,1,1,1,1,0],
        [0,0,1,0,0,0],
        [1,0,0,1,0,1],
        [0,1,0,0,0,1],
        [0,0,0,0,0,0]
    ], # board
)) # result: 3200