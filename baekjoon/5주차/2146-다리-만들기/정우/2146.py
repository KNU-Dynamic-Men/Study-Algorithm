import sys
from collections import deque
from itertools import combinations

dirs = (
    (1, 0),
    (-1, 0),
    (0, 1),
    (0, -1),
)

def is_edge(arr, i, j):
    for d_r, d_c in dirs:
        if arr[i+d_r][j+d_c] == 0:
            return True
    return False

def get_edges(arr, i, j):
    arr[i][j] = 2
    res = []
    q = deque([[(i, j)]])
    while len(q) != 0:
        popped = q.popleft()
        for getR, getC in popped:
            if is_edge(arr, getR, getC):
                res.append((getR, getC))
            nxt = []
            for d_r, d_c in dirs:
                if arr[getR+d_r][getC+d_c] == 1:
                    arr[getR+d_r][getC+d_c] = 2
                    nxt.append((getR+d_r, getC+d_c))
            if nxt:
                q.append(nxt)
    return res

N = int(sys.stdin.readline())
arr = [[3] * (N+2)]
for _ in range(N):
    arr.append(list(getMap(int, f'3 {sys.stdin.readline()} 3'.split())))
arr.append([3] * (N+2))
# print(arr)

edges = []
for i in range(1, len(arr)-1):
    for j in range(1, len(arr[0])-1):
        if arr[i][j] == 1:
            edges.append(get_edges(arr, i, j))
# print(edges)

combi = combinations(edges, 2)
diff_minimum = 10001
diff = ()
for edge_list1, edge_list2 in combi:
    for edge1 in edge_list1:
        for edge2 in edge_list2:
            d = abs(edge1[0]-edge2[0]) + abs(edge1[1]-edge2[1])
            if d < diff_minimum:
                diff_minimum = d
print(diff_minimum - 1)