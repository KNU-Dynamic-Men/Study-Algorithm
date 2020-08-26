import sys
from collections import deque

def find(arr, rg, b):
    curr = arr[x][y]
    if arr[x][y] in ('R','G'):
        arr[x][y] = rg
        check = rg
    else:
        arr[x][y] = b
        check = b
    q = deque([[(x, y),]])
    while len(q) != 0:
        popped = q.popleft()
        nxt = []
        for r, c in popped:
            for i in range(4):
                if arr[r+row[i]][c+col[i]] == curr:
                    arr[r+row[i]][c+col[i]] = check
                    nxt.append((r+row[i], c+col[i]))
        if nxt:
            q.append(nxt)

row = [-1, 0, 0, 1]
col = [0, -1, 1, 0]

n = int(sys.stdin.readline())
arr = [list(f"X{sys.stdin.readline().rstrip()}X") for _ in range(n)]
arr.insert(0, ['X']*(n+2))
arr.append(['X']*(n+2))

cnt = 0
for x in range(1, len(arr)-1):
    for y in range(1, len(arr[0])):
        if arr[x][y] in ('R','G','B'):
            cnt += 1
            find(arr, 'RG', 'P')

patient_cnt = 0
for x in range(1, len(arr)-1):
    for y in range(1, len(arr[0])):
        if arr[x][y] in ('RG', 'P'):
            patient_cnt += 1
            find(arr, '', 'X')

print(cnt, patient_cnt)