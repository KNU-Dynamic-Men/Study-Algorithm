# 9465 - 스티커

## 1. 개요

https://www.acmicpc.net/problem/9465

## 2. 코드

```python
from sys import stdin
t = int(stdin.readline())
for _ in range(t):
    n = int(stdin.readline())
    sticker = [list(map(int, stdin.readline().split(' '))) for _ in range(2)]
    dp = [[0] * n for _ in range(2)]
    # 초기값
    dp[0][0] = sticker[0][0]
    dp[1][0] = sticker[1][0]
    dp[0][1] = sticker[0][1] + dp[1][0]
    dp[1][1] = sticker[1][1] + dp[0][0]

    for i in range(2, n):
        dp[0][i] = sticker[0][i] + max(dp[0][i - 2], dp[1][i - 2], dp[1][i - 1])
        dp[1][i] = sticker[1][i] + max(dp[1][i - 2], dp[0][i - 2], dp[0][i - 1])

    print(max(dp[0][n-1], dp[1][n-1]))
```

## 3. 설명

1. 구현 방법 구상

    - 스티커를 떼는 경우 위, 아래, 양 옆의 스티커는 사용하지 못한다
    - 그렇다면, 계산 가능한 스티커는 어느 위치에 있는가?
    - 1행의 n번째 스티커의 경우를 생각해보자
    - 현재 스티커가 포함되는 최대값은...
    - **n-2번째의 1, 2행 스티커, n-1번째의 2행 스티커**가 포함되는 최대값들 중에서 최대값을 현재 스티커에 더해주면 된다

2. 구현

    ```python
    sticker = [list(map(int, stdin.readline().split(' '))) for _ in range(2)]
    dp = [[0] * n for _ in range(2)]
    ```
    - `sticker` : 스티커의 점수가 저장된 배열
    - `dp` : 지금까지의 점수의 최대값이 저장될 배열

    ```python
    # 초기값
    dp[0][0] = sticker[0][0]
    dp[1][0] = sticker[1][0]
    dp[0][1] = sticker[0][1] + dp[1][0]
    dp[1][1] = sticker[1][1] + dp[0][0]
    ```
    - `dp` 배열의 초기값을 설정한다
    - 가장 첫 번째는 1행과 2행 모두 `sticker`의 처음 값을 그대로 저장한다
    - 두 번째는 대각선 위치의 점수를 더해준 값을 저장한다
    ```python
    for i in range(2, n):
        dp[0][i] = sticker[0][i] + max(dp[0][i - 2], dp[1][i - 2], dp[1][i - 1])
        dp[1][i] = sticker[1][i] + max(dp[1][i - 2], dp[0][i - 2], dp[0][i - 1])
    ```
    - **다이나믹 프로그래밍!**
    - 기존에 계산된 최대 점수들을 이용하여 현재 점수 최대값을 계산한다

## 4. 결과

![image](https://user-images.githubusercontent.com/29600820/91163411-7d876000-e708-11ea-85bf-c71d037143bc.png)
