# 2293 - 동전 1

## 1. 개요

https://www.acmicpc.net/problem/2293

## 2. 코드

```python
from sys import stdin
n, k = map(int, stdin.readline().split())
coin = []
for _ in range(n):
    coin.append(int(stdin.readline()))
dp = [0 for _ in range(k+1)]
dp[0] = 1
for i in coin:
    for j in range(i, k+1):
        dp[j] += dp[j - i]
print(f"{dp[k]}")
```

## 3. 풀이

1. 구현 방법 구상

    - 1 종류의 동전만 사용하는 경우의 수를 구한다
    - 2 종류의 동전만 사용하는 경우의 수를 구한다
    - . . .
    - n 종류의 동전만 사용하는 경우의 수를 구한다
    - n 종류의 동전을 사용하는 경우의 수는 n-1 종류의 동전을 사용하는 경우의 수에 n 번째 동전을 추가해서 사용하는 경우의 수를 구하면 된다
    - 불필요한 반복적 계산을 없애기 위해 다이나믹 프로그래밍을 사용한다
    
2. 구현

    ```python
    for i in coin:
        for j in range(i, k+1):
            dp[j] += dp[j - i]
    ```
    - `coin`을 하나씩 불러와서 계산한다
    - 범위는 불러온 `coin`의 크기부터 구해야 하는 값인 `k`까지
    - 현재 위치에서 `coin`의 크기만큼 빼 준 위치의 값을 더해준다
    ```python
    print(f"{dp[k]}")
    ```
    - 계산이 완료되면 해당 값을 출력한다

## 4. 결과

![image](https://user-images.githubusercontent.com/29600820/90951193-89b6b780-e493-11ea-818a-a71da3683b62.png)
