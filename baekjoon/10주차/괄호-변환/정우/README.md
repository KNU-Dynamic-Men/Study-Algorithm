# **1927 - 최소 힙**

## **1. 개요**

[https://www.acmicpc.net/problem/1927](https://www.acmicpc.net/problem/1927)

## **2. 코드**

Python3

```python
import sys

def add(arr, cnt):
    i = cnt
    while i != 1 and arr[i//2] > arr[i]:
        arr[i//2], arr[i] = arr[i], arr[i//2]
        i = i//2

def minus(arr, cnt):
    ret, arr[1] = arr[1], arr[cnt+1]
    i = 1
    while i*2 < cnt+1:
        if i*2 < cnt:
            if arr[i*2] <= arr[i*2+1] and arr[i*2] < arr[i]:
                arr[i], arr[i*2] = arr[i*2], arr[i]
                i = i*2
            elif arr[i*2+1] < arr[i]:
                arr[i], arr[i*2+1] = arr[i*2+1], arr[i]
                i = i*2+1
            else:
                break
        else:
            if arr[i*2] < arr[i]:
                arr[i*2], arr[i] = arr[i], arr[i*2]
                i = i*2
            else:
                break
    return ret

n = int(sys.stdin.readline())
arr = [0] * (n+1)
res = []
cnt = 0
for _ in range(n):
    x = int(sys.stdin.readline())
    if x == 0:
        if cnt == 0:
            res.append(0)
        else:
            cnt -= 1
            res.append(minus(arr, cnt))
    else:
        cnt += 1
        arr[cnt] = x
        add(arr, cnt)
print('\n'.join(map(str, res)))
```

## **3. 설명**

1. `heapq`를 사용하지 않고 힙을 직접 구현했다.
2. 힙이란?
    1. 힙은 이진완전트리 구조를 띄며, 루트 노드가 최소 또는 최대 값을 가지며, 하위 레벨의 노드로 갈수록 반대의 값을 가진다.
    2. 값을 추가할 때 (최소힙)
        1. 마지막 노드에 추가하려는 값 x를 넣는다.
        2. x와 x의 부모 노드를 비교하여, x의 값이 작다면 서로 교체한다.
        3. x가 부모노드보다 작을동안 `2`를 반복한다.
    3. 값을 삭제할 때 (최소힙)
        1. 루트 노드를 제거한다.
        2. 마지막 노드 x를 루트 노드로 이동시킨다.
        3. x와 자식 노드를 비교하여, x의 값이 크다면 서로 교체한다.
        4. x가 자식 노드보다 클동안 `3`을 반복한다.

## **4. 여정**

1. 성공

## **5. 결과**
![image](https://user-images.githubusercontent.com/41278416/91873515-12173280-ecb4-11ea-8971-c43f507ee838.png)