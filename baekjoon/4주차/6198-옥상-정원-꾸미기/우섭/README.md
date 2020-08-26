# 6198 - 옥상 정원 꾸미기

## 1. 개요

https://www.acmicpc.net/problem/6198

## 2. 코드

```python
from sys import stdin

n = int(stdin.readline())
a = 0
tower = []
stack = []
for _ in range(n):
    tower.append(int(stdin.readline()))
for i in range(n):
    while(stack):
        if stack[-1] <= tower[i]:
            stack.pop()
        else:
            break
    a += len(stack)
    stack.append(tower[i])
print(a)
```

## 3. 설명

1. 구현 방법 구상

    - [2493 - 탑](https://github.com/KNU-Dynamic-Men/Study-Algorithm/tree/master/baekjoon/2493-%ED%83%91/%EC%9A%B0%EC%84%AD)과 상당히 유사한 문제
    - 이 빌딩은 몇개의 옥상을 볼수있는가? 가 아닌 **이 빌딩은 몇개의 빌딩에게 옥상이 보여지는가?** 로 접근 ~~능동태가 아닌 수동태~~

2. 구현

    - 탑을 왼쪽에서부터 하나씩 탐색을 진행한다
    - `stack` 배열에 값이 남아있지 않을때 까지 진행한다
    - `stack` 배열의 마지막 값이 현재 빌딩의 높이보다 낮은 경우 pop을 한다
    - 이를 반복하면 `stack`에는 **현재 빌딩보다 높이가 높은 빌딩**만 남게된다
    - 즉 `stack`의 크기는 **해당 빌딩의 옥상을 볼 수 있는 빌딩의 수**가 된다
    - 탐색 완료 시 `stack`에 현재 빌딩의 높이를 저장하고 다음 탐색을 진행한다
    ```python
    for i in range(n):
    while(stack):
        if stack[-1] <= tower[i]:
            stack.pop()
        else:
            break
    a += len(stack)
    stack.append(tower[i])
    ```
    - 출력 시 지금까지의 stack 길이를 더한 값을 출력한다

## 4. 결과
![image](https://user-images.githubusercontent.com/29600820/88616105-33da3400-d0ce-11ea-9f43-abbcfce19c63.png)
