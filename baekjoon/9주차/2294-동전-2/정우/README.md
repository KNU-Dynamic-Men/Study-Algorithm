# **2294 - 동전 2**

## **1. 개요**

[https://www.acmicpc.net/problem/2294](https://www.acmicpc.net/problem/2294)

## **2. 코드**

Python3

```python
import sys

n, k = map(int, sys.stdin.readline().split())
arr = []
dp = [10001] * (k+1)
for _ in range(n):
    i = int(sys.stdin.readline())
    if i <= k:
        dp[i] = 1
    arr.append(i)
arr = list(set(arr))
res = []

for i in range(1, k+1):
    for a in arr:
        if i-a >= 0:
            dp[i] = min(dp[i-a]+1, dp[i])
if dp[-1] == 10001:
    print(-1)
else:
    print(dp[-1])
```

## **3. 설명**

1. 동전이 x의 가치를 가진다면 동전의 합 x를 만들기 위해 동전이 하나만 필요하다.
2. 동전의 합 x를 만들기 위해서는 해당 x를 만들기 위해서 이전의 값이 필요하다. 
3. 가장 최소의 값으로 가져올 수 있는 방법은 다음과 같다.
    1. x에 각 동전 크기를 뺐을 때 이를 y라고 하자. 
    2. (이 y를 만들기 위해 필요한 동전의 개수) + (방금 전에 뺀 동전 1개)가 최소 동전의 개수가 된다.
    3. 단, 처음에 합을 동전 하나 만으로도 이룰 수 있는 경우가 있다고 했으므로, 2번의 값과 현재 x의 동전 개수를 비교하여 더 적은 값을 저장한다.
4. 모든 합과 모든 동전을 비교하면 O(nk)의 시간복잡도를 가진다.

## **4. 여정**

1. Array Boundary Exception Error
2. Array Boundary Exception Error
3. 성공

## **5. 결과**
![image](https://user-images.githubusercontent.com/41278416/91875638-b7cba100-ecb6-11ea-9dca-f974f941c8a1.png)