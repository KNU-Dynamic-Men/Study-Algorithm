# 1799 - 비숍

## 1. 개요

https://www.acmicpc.net/problem/1799

## 2. 코드
```python
from sys import stdin
n = int(stdin.readline())
chess = []
for _ in range(n):
    chess.append(list(map(int, stdin.readline().rstrip().split(' '))))
croleft = [False]*(2*n-1)
croright = [False]*(2*n-1)
ans = [0, 0]

def find(x, y, k, color):
    ans[color] = max(k, ans[color])
    if y >= n:
        if y % 2 == 0:
            y = 1
        else:
            y = 0
        x += 1
    
    if x >= n:
        return

    if chess[x][y] == 1 and not croleft[x+y] and not croright[x-y+n-1]:
        croleft[x+y] = croright[x-y+n-1] = True
        find(x, y+2, k+1, color)
        croleft[x+y] = croright[x-y+n-1] = False
    
    find(x, y+2, k, color)

find(0, 0, 0, 0)
find(0, 1, 0, 1)
print(sum(ans))
```

## 3. 풀이

1. 구현 방법 구상

    - 이전 주차에 풀었던 [N-Queen](https://www.acmicpc.net/problem/9663) 문제와 유사하지만, **비숍의 특징을 이용**해서 조금 다르게 구현해야 한다
    - 퀸의 경우, 가로 세로 대각선을 모두 이동할 수 있으므로, 백트래킹을 이용하여 가로줄 마다 탐색을 돌리면 모든 경우의 수를 알 수 있었다
    - 비숍의 경우, **대각선으로만 움직일 수 있으며** 이로 인해 같은 열과 행에 두 개 이상의 비숍이 존재할 수 있다
    - 또한, 체스판의 경우 검은색과 흰색이 격자무늬로 반복되는 형태인데, 비숍의 특징으로 인해 비숍의 최초 위치를 기준으로 **동일한 색의 타일로만 움직일 수 있다**
    - 전체탐색을 진행하는 경우 10초 이내에 풀리지 않지만, 흰 색인 경우와 검은색인 경우를 나누어서 생각하게 되는 경우 시간복잡도를 줄일 수 있다

2. 구현
    ```python
    def find(x, y, k, color):
    ```
    - **재귀를 이용한 백트래킹** 탐색을 진행하기 위해 `find` 함수를 구현한다
    - `x`: 행의 좌표
    - `y`: 열의 좌표
    - `k`: 지금까지 좌표에 놓은 비숍의 수
    - `color`: 체스판 타일의 색

    ```python
    ans[color] = max(k, ans[color])
    ```
    - 재귀를 통해 불러올 때 마다 해당 체스판 타일 색의 비숍 최대 갯수를 갱신한다
    ```python
    if y >= n:
        if y % 2 == 0:
            y = 1
        else:
            y = 0
        x += 1
    if x >= n:
        return
    ```
    - `y` 좌표가 체스판의 크기인 `n`을 벗어나는 경우 다음 열의 `x, y`좌표로 바꾸어준다
    - `%` 연산을 이용하여 다음 행의 `y`좌표를 계산한다
    - x 좌표가 크기를 벗어나는 경우 return을 해준다

    ```python
    if chess[x][y] == 1 and not croleft[x+y] and not croright[x-y+n-1]:
        croleft[x+y] = croright[x-y+n-1] = True
        find(x, y+2, k+1, color)
        croleft[x+y] = croright[x-y+n-1] = False

    find(x, y+2, k, color)
    ```
    - 해당 체스판이 비숍을 놓을 수 있는 자리인지 판단한다
    - 비숍을 놓을 수 있는 경우 해당 좌표에서 움직일 수 있는 대각선 좌표를 `True`로 설정한다
    - 비숍의 최대값을 증가시킨 후 체스판 오른쪽의 다음 좌표를 탐색한다
    - 탐색이 완료되어 돌아온 경우 대각선 좌표를 다시 `False`로 설정한다
    - 체스판 오른쪽의 다음 좌표를 탐색한다
    
    ```python
    find(0, 0, 0, 0)
    find(0, 1, 0, 1)
    print(sum(ans))
    ```
    - 최초 탐색을 진행할 경우, 최초 좌표는 `0,0`과 `0,1`이며, `color` 값을 각각 다르게 `0`과 `1`로 지정한다
    - 탐색 완료된 `ans` 배열의 합을 계산하고 출력한다

## 4. 결과
![image](https://user-images.githubusercontent.com/29600820/90518633-01d66200-e1a2-11ea-9ba0-784029891aa1.png)
