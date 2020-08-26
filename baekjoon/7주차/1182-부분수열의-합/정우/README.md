# **1182 - 부분수열의 합**

## **1. 개요**

[https://www.acmicpc.net/problem/1182](https://www.acmicpc.net/problem/1182)

## **2. 코드**

Python3

```python
import sys, itertools

N, S = map(int, sys.stdin.readline().split())
arr = list(map(int, sys.stdin.readline().split()))
cnt = 0
for i in range(1,N+1):
    for combi in itertools.combinations(arr,i):
        cnt = cnt+1 if sum(combi) == S else cnt
print(cnt)
```

## **3. 설명**

1. 부분수열의 길이가 1일 때, 2일 때, ... , N-1일 때, N일 때를 모두 구한다.
2. 구한 부분수열의 모든 원소의 합이 S가 되면 cnt 증가.

## **4. 여정**

1. 성공

## **5. 결과**
![image](https://user-images.githubusercontent.com/41278416/90486490-fb30f600-e173-11ea-9a19-f5aee9c8df46.png)