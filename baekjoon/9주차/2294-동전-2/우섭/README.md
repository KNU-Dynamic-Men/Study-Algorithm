# 2294 - 동전 2

## 1. 개요

https://www.acmicpc.net/problem/2294

## 2. 코드

```python
from sys import stdin
n, k = map(int, stdin.readline().split())
dp = [20000 for _ in range(k+1)]
coin = []
dp[0] = 0
for _ in range(n):
    coin.append(int(stdin.readline()))
for i in range(1, k+1):
    for j in coin:
        if i - j >= 0:
            dp[i] = min(dp[i], dp[i - j] + 1)
print(dp[k] if dp[k] != 20000 else -1)
```

## 3. 풀이

1. 구현 방법 구상

    - **동적 프로그래밍** 문제
    - 지난 시간에 풀었던 [동전 1](https://www.acmicpc.net/problem/2293) 문제와 비슷하다
    - 따라서 풀이도 비슷하게

2. 동적 배열

    ```python
    dp = [20000 for _ in range(k+1)]
    ```
    - `dp` 배열의 의미는 해당 `index`의 금액을 만들 때 **최소로 필요한 코인의 수**
    - 최소값을 구하는 문제이므로 초기화 시 최대값을 먼저 저장해준다
    - `20000`인 이유는 `k`의 최대값이 `10000`이므로 코인의 최대 개수가 `10000`을 넘을 수 없다

3. 탐색

    ```python
    for i in range(1, k+1):
        for j in coin:
            if i - j >= 0:
                dp[i] = min(dp[i], dp[i - j] + 1)
    ```
    - dp 배열을 1에서부터 k+1까지 탐색한다
    - coin을 하나씩 꺼내서 확인한다
    - 현재 금액을 만들 때, 지금 저장된 값과 현재 꺼낸 coin을 뺀 금액에서 1개 추가한 값을 비교하여 최소값을 저장한다

## 4. 결과
![image](https://user-images.githubusercontent.com/29600820/91955229-d628af80-ed3d-11ea-992d-65d3dad339e8.png)