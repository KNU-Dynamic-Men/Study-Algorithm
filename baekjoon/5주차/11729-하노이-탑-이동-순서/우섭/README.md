# 11729 - 하노이 탑 이동 순서

## 1. 개요

- https://www.acmicpc.net/problem/11729
- 참고 링크: [하노이의 탑 - 위키백과](https://ko.wikipedia.org/wiki/%ED%95%98%EB%85%B8%EC%9D%B4%EC%9D%98_%ED%83%91)

## 2. 코드
```python
from sys import stdin

tower = 6
def hanoi(a, b, k):
    if k == 1:
        print("{} {}".format(a, b))
        return
    
    hanoi(a, tower-a-b, k-1)
    print("{} {}".format(a, b))
    hanoi(tower-a-b, b, k-1)

n = int(stdin.readline())
print(2**n-1)
hanoi(1, 3, n)
```

## 3. 설명

1. 구현 방법 구상

    - 한 번에 하나의 원판만
    - 큰 원판이 작은 원판에 있어서는 안된다
    - n개의 원반을 한쪽 기둥에서 다른 쪽으로 옮기는 데 걸리는 최소 횟수는![image](https://user-images.githubusercontent.com/29600820/89172968-72845880-d5be-11ea-93ae-a49eed8c13c6.png)점화식에 의하여 ![image](https://user-images.githubusercontent.com/29600820/89172884-54b6f380-d5be-11ea-919c-3c0297e6807f.png)번이 된다
    - 첫 번째 횟수 출력은 그냥 계산하고 출력하면 된다
    - 이제 과정이 중요한데...

2. 재귀

    - [0x0B강 - 재귀 - BaaaaaaaarkingDog](https://baaaaaaaaaaaaaaaaaaaaaaarkingdog.tistory.com/943?category=773649)의 설명으로 대신하자면 **n-1개의 원판을 원하는 곳으로 옮길 수만 있다면 n개의 원판을 처리할 수 있다**
    - 1번 기둥에서 3번 기둥으로 t개의 원판을 옮긴다 라는 것은...
    - 1번 기둥에서 2번 기둥으로 t-1개의 원판을 옮긴 후...
    - 1번 기둥에서 3번 기둥으로 t번째 원판을 옮긴 후...
    - 2번 기둥에서 3번 기둥으로 t-1개의 원판을 옮긴다!
    - **재귀로 구현해보자!**

3. 함수 구현
    ```python
    def hanoi(a, b, k):
        if k == 1:
            print("{} {}".format(a, b))
            return
        
        hanoi(a, tower-a-b, k-1)
        print("{} {}".format(a, b))
        hanoi(tower-a-b, b, k-1)
    ```
    - `tower`: 모든 기둥의 번호를 합한 값 (1 + 2 + 3 = 6)
    - `k`: 원판 번호
    - `a`: `k`번 원판이 위치한 기둥
    - `b`: `k`번 원판을 이동시키려는 기둥
    - `k`가 `1`인 경우 가장 상위에 있는 원판이므로 바로 이동 가능 -> 출력 후 바로 `return`
    - `k-1`번째 원판을 `a`기둥에서 `tower-a-b`로 모두 옮긴 후 맨 마지막인 `k`번째 원판을 `a`에서 `b`로 옮기고 `tower-a-b`에 있는 모든 원판을 `b`에 옮긴다
    - 이를 반복해서 출력!

## 4. 결과
![image](https://user-images.githubusercontent.com/29600820/89180823-84b9c300-d5cd-11ea-9a92-b0f13e7be0e7.png)