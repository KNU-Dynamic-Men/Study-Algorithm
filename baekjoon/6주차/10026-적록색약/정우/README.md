# **10026 - 적록색약 **

## **1. 개요**

[https://www.acmicpc.net/problem/15649](https://www.acmicpc.net/problem/15649)

## **2. 코드**

Python3

```python
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
```

## **3. 설명**

1. 정상인 기준으로 먼저 BFS 탐색을 한다.
2. 탐색을 하면서 지나온 자리는 'RG' 또는 'P'로 마킹한다.
    - 'RG': R 혹은 G 이었던 자리
    - 'P': B 이었던 자리
3. 탐색을 모두 마친 후, 적록색약 기준으로 탐색을 다시 한다.
4. 배열에는 'RG', 'P'만 존재하므로, 해당 두 구역의 개수를 구하면 된다.

## **4. 여정**

1. 통과

## **5. 결과**
![image](https://user-images.githubusercontent.com/41278416/89978322-3af56a80-dca8-11ea-96ba-7fb1d682dc2b.png)