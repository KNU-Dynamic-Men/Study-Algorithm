# **9663 - N-Queen**

## **1. 개요**

[https://www.acmicpc.net/problem/9663](https://www.acmicpc.net/problem/9663)

## **2. 코드**

Pypy3

```python
import sys, time

def is_possible(idx, x):
    for i in range(1,idx+1):
        if arr[idx-i] == x-i or arr[idx-i] == x+i:
            return False
    return True

def loop(arr, idx, all_queens, queens):
    cnt = 0
    # print(queens, set(range(N)) - queens, arr)
    for i in all_queens - queens:
        if is_possible(idx, i):
            if idx+1 == N:
                cnt += 1
            else:
                arr[idx] = i
                cnt += loop(arr, idx+1, all_queens, queens|set([i]))
    return cnt

now = time.time()
N = int(sys.stdin.readline())
if N%2 == 0:
    n = int(N/2)
else:
    n = N
arr = [-1]*N
print(loop(arr, 0, set(range(N)), set()))
# print(time.time()-now)

```

## **3. 설명**

1. 1차 배열 사용

## **4. 여정**

1. 삽질의 연속
2. pypy3로 겨우 성공

## **5. 결과**
![image](https://user-images.githubusercontent.com/41278416/90012929-43b56300-dcdf-11ea-8b7c-06616c79532b.png)