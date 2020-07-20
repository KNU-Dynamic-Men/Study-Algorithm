# **2493 - 탑**

## **1. 개요**

[https://www.acmicpc.net/problem/2493](https://www.acmicpc.net/problem/2493)

## **2. 코드**

```python
import sys

sys.stdin.readline()
arr = list(map(int, sys.stdin.readline().split()))
ret = [0 for _ in range(len(arr))]
stack = [(len(arr)-1, arr[-1]),] # [(idx, value)]
for i in range(len(arr)-2, -1, -1):
    idx, value = stack[-1]
    while value < arr[i]:
        ret[idx] = i+1
        stack.pop()
        if stack:
            idx, value = stack[-1]
        else:
            break
    stack.append((i, arr[i]))
ret = list(map(str, ret))
print(' '.join(ret))
```

## **3. 설명**

1. n번째의 탑의 인덱스와 높이를 `(인덱스, 높이)` 형식으로 스택에 저장한다.
2. n-1번째의 탑과 스택에 저장된 탑을 비교해, n-1번째의 탑이 더 크다면 정답 배열의 n번째 탑의 위치에 n-1번째 탑의 인덱스+1 하여 저장한다.

## **4. 여정**

1. 정답 배열에 탑의 인덱스를 저장할 때, 스택에 저장된 탑의 위치에 넣어야 하는데 순서대로 넣어버려서 틀림.
2. 성공

## **5. 결과**
![image](https://user-images.githubusercontent.com/41278416/87913031-602bfa00-caa9-11ea-9f8e-0db816efc61d.png)