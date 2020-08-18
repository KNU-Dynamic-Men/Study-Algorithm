# 1182 - 부분수열의 합

## 1. 개요 

https://www.acmicpc.net/problem/1182

## 2. 코드

```python
from sys import stdin
from collections import deque
n, s = map(int, stdin.readline().split())
t = list(map(int, stdin.readline().split(' ')))
check = [False for _ in range(n)]
arr = deque()
ans = 0

def find(index):
    if sum(arr) == s and len(arr) != 0:
        global ans
        ans += 1
    for i in range(index, n):
        if check[i] == False:
            check[i] = True
            arr.append(t[i])
            find(i)
            check[i] = False
            arr.pop()
find(0)
print(ans)
```

## 3. 풀이

1. 구현 방법 구상

    - [N과 M (6)](https://www.acmicpc.net/problem/15655) 문제를 좀 더 확장시킨 느낌의 문제
    - **부분수열의 합**을 기준으로 정답 값을 변화시킨다
    - `N`의 크기가 **1 ≤ N ≤ 20**으로 작은 값이므로 완전탐색을 진행한다

2. 함수 구현

    ```python
    def find(index):
    ```
    - 함수의 인자는 현재 배열의 몇 번째를 탐색중인지 나타내는 `index`
    ```python
    if sum(arr) == s and len(arr) != 0:
        global ans
        ans += 1
    ```
    - 부분수열을 저장한 `arr` `deque`에 들어가 있는 부분수열의 합이 주어진 조건인 `s`이면서 해당 부분수열의 크기가 `0`보다 큰 경우 `ans` 값을 `1` 증가시켜 준다
    ```python
    for i in range(index, n):
        if check[i] == False:
            check[i] = True
            arr.append(t[i])
            find(i)
            check[i] = False
            arr.pop()
    ```
    - 현재 탐색중인 `index`에서 배열의 크기인 `n`까지의 범위를 조사한다
    - 만약 해당 `index`가 아직 부분수열에 없는 값이면 해당 값을 부분수열에 `append`하여 추가하여 준 후 find 함수를 재귀를 통해 불러온다
    - 해당 `index`의 탐색이 끝나 함수가 `return`되면 해당 `index`를 탐색하지 않은 것으로 변경하고 부분수열에서 해당 값을 제외시킨다

## 4. 결과

![image](https://user-images.githubusercontent.com/29600820/90479651-f8c99e80-e169-11ea-81ad-363373ede713.png)