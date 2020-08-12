# 15649 - N과 M (1)

## 1. 개요

- https://www.acmicpc.net/problem/15649
- N과 M 문제 중 가장 기초 문제

## 2. 코드
```python
from sys import stdin
from collections import deque
getN, m = getMap(int, stdin.readline().split())
check = [False for _ in range(getN+1)]
arr = deque()

def find(count):
    if count == m:
        print(' '.join(getMap(str, arr)))
        return
    for i in range(1, getN+1):
        if check[i] == False:
            check[i] = True
            arr.append(i)
            find(count + 1)
            check[i] = False
            arr.pop()
find(0)
```

## 3. 풀이

1. 구현 방법 구상

    - 백트래킹 문제
    - DFS, BFS와 유사하지만, 다른점은 탐색 완료 시 돌아오면서 해당 값을 탐색하지 않은 것으로 수정한 후 다음 경로를 탐색한다
    - 지정된 크기만큼 count되면 배열을 출력하고 return
    - 조건에 따라 배열에 추가하고, 출력 완료되고 return되면 해당 값을 탐색하지 않은 것으로 체크 후 계속 탐색

2. 구현

    ```python
    check = [False for _ in range(getN+1)]
    arr = deque()
    ```
    - check: 해당 값을 탐색하였는지 확인하는 배열
    - arr: 탐색한 숫자를 차례대로 넣는 deque
    ```python
    for i in range(1, getN+1):
        if check[i] == False:
            check[i] = True
            arr.append(i)
            find(count + 1)
            check[i] = False
            arr.pop()
    ```
    - 배열을 탐색하면서, 해당 값이 배열에 넣지 않은 값인 경우 `arr`에 추가하고, `check`를 `True`로 바꾸어 중복되지 않게 설정한다
    - 출력이 완료된 후에 돌아온 경우 `arr`에서 해당 값을 빼 주고, `check`를 `False`로 바꾸어 해당 값을 다시 탐색이 가능하도록 설정한다

## 4. 결과
![image](https://user-images.githubusercontent.com/29600820/89903237-62a1ef80-dc22-11ea-9769-1a21c7e48989.png)