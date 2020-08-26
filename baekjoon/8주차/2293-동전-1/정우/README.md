# **2293 - 동전 1**

## **1. 개요**

[https://www.acmicpc.net/problem/2293](https://www.acmicpc.net/problem/2293)

## **2. 코드**

Python3

```python
import sys

n, k = map(int, sys.stdin.readline().split())
arr = []
for _ in range(n):
    arr.append(int(sys.stdin.readline()))
dp = [0]*(k+1)
dp[0] = 1
for i in arr:
    for j in range(i, k+1):
        dp[j] += dp[j - i]
print(dp[k])
```

## **3. 설명**

1. 동전 arr[0]를 사용해 K를 채울 수 있는 경우의 수를 구한다.
2. 동전 arr[1]도 사용해 K를 채울 수 있는 경우의 수를 구한다.
3. 동전 arr[2]도 사용해 K를 채울 수 있는 경우의 수를 구한다.
4. 동전 arr[n]도 사용해 K를 채울 수 있는 경우의 수를 구한다.
- 각 경우의 수를 구할 때:
    - K의 경우의 수를 구하기 위해서는 K-arr[i]번째의 경우의 수를 가져온다.

## **4. 여정**

1. 성공

## **5. 결과**
![image](https://user-images.githubusercontent.com/41278416/91266466-4e2a2f00-e7ac-11ea-908a-3f6a7749bfbe.png)