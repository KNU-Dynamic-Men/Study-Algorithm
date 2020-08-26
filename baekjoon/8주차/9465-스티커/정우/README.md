# **9465 - 스티커**

## **1. 개요**

[https://www.acmicpc.net/problem/9465](https://www.acmicpc.net/problem/9465)

## **2. 코드**

Python3

```python
import sys

for _ in range(int(sys.stdin.readline())):
    n = int(sys.stdin.readline())
    a1 = list(map(int, sys.stdin.readline().split()))
    a2 = list(map(int, sys.stdin.readline().split()))
    dp = [[0]*n for _ in range(3)]
    dp[0][0] = a1[0]
    dp[1][0] = a2[0]
    for i in range(1, n):
        dp[0][i] = max(dp[1][i-1], dp[2][i-1])+a1[i]
        dp[1][i] = max(dp[0][i-1], dp[2][i-1])+a2[i]
        dp[2][i] = max(dp[0][i-1], dp[1][i-1])
    print(max([dp[0][n-1], dp[1][n-1], dp[2][n-1]]))
```

## **3. 설명**

1. i번째 열의 스티커를 구하기 위해서는 i-1번째의 스티커를 무엇을 선택했는지 판단해야 한다.
    1. i번째 열의 1번째 행의 값을 구하기 위해서는, max(i-1번째 열의 2번째 행, i-1번째 열의 3번째 행)를 선택한다.
    2. i번째 열의 2번째 행의 값을 구하기 위해서는, max(i-1번째 열의 1번째 행, i-1번째 열의 3번째 행)를 선택한다.
    3. i번째 열의 3번째 행은 i-1번째 열에서 아무것도 선택하지 않은 경우이다. 즉, i-1번째 열에서 최대값을 선택한다.
2. 1-1 ~ 1-3을 모든 열에 대해서 반복한다.

## **4. 여정**

1. 성공

## **5. 결과**
![image](https://user-images.githubusercontent.com/41278416/91301985-bbf04e00-e7e0-11ea-861b-7fdad47f9073.png)