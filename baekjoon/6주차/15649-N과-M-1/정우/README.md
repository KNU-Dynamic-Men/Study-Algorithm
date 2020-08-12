# **15649 - N과 M (1)**

## **1. 개요**

[https://www.acmicpc.net/problem/15649](https://www.acmicpc.net/problem/15649)

## **2. 코드**

Python3

```python
import sys

def loop(arr, res, cnt, m):
    if cnt == m:
        print(res)
        return
    toggles = []
    for i in range(len(arr)):
        if arr[i] == 0:
            toggles.append(i)
    for i in toggles:
        arr[i] = 1
        loop(arr, f"{res} {i+1}", cnt+1, m)
        arr[i] = 0
        

n, m = map(int, sys.stdin.readline().split())
arr = [0] * n

for i in range(n):
    arr[i] = 1
    loop(arr, i+1, 1, m)
    arr[i] = 0
```

## **3. 설명**

1. `itertools`를 쓰면 너무 쉬울 것 같아 사용하지 않았다.
2. 번호를 뽑아올 배열을 하나 둔다.
3. 배열의 앞에서부터 번호를 뽑는다.(뽑으면 1로 변경)
4. 그 다음 자리 수를 뽑으러 간다.(재귀 한 단계 더 들어가기)
5. 다음 자리 수를 뽑고 나오면 현재 위치에서 뽑은 번호를 돌려준다.(0으로 변경)
6. 만약 배열에서 번호를 다 뽑은 경우라면 출력만 하고 나온다.

## **4. 여정**

1. 통과

## **5. 결과**
![image](https://user-images.githubusercontent.com/41278416/89863390-f6ed6180-dbe4-11ea-8529-67799d5407ed.png)