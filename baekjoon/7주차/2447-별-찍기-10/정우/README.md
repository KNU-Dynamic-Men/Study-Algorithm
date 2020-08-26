# **2447 - 별 찍기 - 10**

## **1. 개요**

[https://www.acmicpc.net/problem/2447](https://www.acmicpc.net/problem/2447)

## **2. 코드**

Python3

```python
import sys

def loop(y, x, n):
    if n == 1:
        arr[y][x] = '*'
        return
    d = n//3
    for i in range(3):
        for j in range(3):
            if not i==j==1:
                loop(i*d+y, j*d+x, d)

N = int(sys.stdin.readline())
arr = [[' ' for _ in range(N)] for _ in range(N)]
loop(0, 0, N)
for i in range(N):
    print(''.join(arr[i]))
```

## **3. 설명**

- `arr = [['']*N]*N]` 와 같이 배열을 선언하면 깊은 복사가 아니라 얉은 복사로서, 원소 간에 값을 공유한다는 점을 유의하자.

1. 주어진 전체 크기 N에서 3으로 나눈다.
2. 나눈 각각의 위치에서 또 3씩 계속 나누고, 이런 방식을 최소 형태가 나올 때까지 반복한다.
    - 단, 가운데 위치에는 더 이상 진행하지 않는다.
3. 최소 형태(3x3)가 나오면, 이 때 가운데만 비우고 나머지 위치에 별을 채운다.

## **4. 여정**

1. 성공

## **5. 결과**
![image](https://user-images.githubusercontent.com/41278416/90492406-1ef83a00-e17c-11ea-8650-49dd3809ee5f.png)