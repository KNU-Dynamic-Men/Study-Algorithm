# **4179 - 옥상 정원 꾸미기**

## **1. 개요**

[https://www.acmicpc.net/problem/6198](https://www.acmicpc.net/problem/6198)

## **2. 코드**

Python3
```python
import sys

N = int(sys.stdin.readline())
buildings = []
for _ in range(N):
    buildings.append(int(sys.stdin.readline()))
stack = [(buildings[-1], 0)]
cnt = 0
for i in range(N-2, -1, -1):
    t = s = 0
    while stack and stack[-1][0] < buildings[i]:
        s += stack.pop()[1]
        t += 1
    stack.append((buildings[i], t+s))
    cnt += t+s
print(cnt)
```

## **3. 설명**

1. 스택에 빌딩의 크기를 넣어 놓는다.
2. 가장 위에 있는 빌딩 크기보다 낮은 빌딩이 있는지 비교한다.
3. 낮은 빌딩이 있으면 개수를 세어서 스택에 넣는다.
    - 이 때 빌딩의 개수와 해당 빌딩들이 몇 개의 빌딩을 볼 수 있는지를 더해서 넣는다.

## **4. 여정**

1. 가장 큰 빌딩이 두 번째로 작은 빌딩 너머에 있는 빌딩도 볼 수 있다는 사실을 간과.
2. 성공

## **5. 결과**
![image](https://user-images.githubusercontent.com/41278416/88823872-8d428000-d200-11ea-99de-8ea7e713b410.png)