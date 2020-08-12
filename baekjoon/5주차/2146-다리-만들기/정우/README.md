# **2146 - 다리 만들기**

## **1. 개요**

[https://www.acmicpc.net/problem/2146](https://www.acmicpc.net/problem/2146)

## **2. 코드**

Python3

```python
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
```

## **3. 설명**

1. 주어진 배열을 탐색하면서 섬의 가장자리를 구해 저장한다.
2. 각 섬 별로 주어진 가장자리끼리 길이를 구해서 최소값을 출력한다.

## **4. 여정**

1. 섬 사이의 길이를 모두 배열에 저장한 후에 최소값을 찾아 줄력하는 방법 사용 
    - 메모리 초과 -> 최소값만 저장하도록 변경
2. 섬의 안쪽 말고 벽이랑 맞닿아 있는 가장자리도 거리 계산에 사용
    - 시간 초과 -> 가장자리는 계산에서 빼도록 변경
3. 거리 계산시 문제에서 제시한 거리 계산법이 아닌 유클리드 거리 계산법 사용
    - 틀렸습니다 -> 거리 계산법 변경
4. 메모리 초과 
    - 왜?
5. 치트키 pypy3 사용 
    - 그래도 메모리 초과
6. 이것 저것 건드려보지만
    - 메모리 초과
7. combinations의 결과를 list로 감싸지 않고 바로 사용
    - 통과

## **5. 결과**
![image](https://user-images.githubusercontent.com/41278416/89100691-0b3a9d00-d434-11ea-8b4d-82193f8c9d78.png)