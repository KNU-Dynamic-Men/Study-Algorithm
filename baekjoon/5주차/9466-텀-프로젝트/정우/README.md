# **9466 - 텀 프로젝트**

## **1. 개요**

[https://www.acmicpc.net/problem/9466](https://www.acmicpc.net/problem/9466)

## **2. 코드**

Python3

```python
import sys

T = int(sys.stdin.readline())
for _ in range(T):
    getN = int(sys.stdin.readline())
    arr = list(getMap(int, sys.stdin.readline().split()))

    dic = dict()
    for i in range(1, getN+1):
        dic[i] = arr[i-1]

    looser_cnt = 0
    for i in range(1, getN+1):
        x = i
        can_loosers = []
        is_looser = True
        while x in dic.keys():
            tmp = dic.pop(x)
            if tmp == x:
                break
            elif tmp == i:
                is_looser = False
                break
            can_loosers.append(x)
            x = tmp
        can_looser_cnt = 0
        for candidate in can_loosers:
            if candidate == x:
                break
            can_looser_cnt += 1
        if is_looser:
            looser_cnt += can_looser_cnt
    print(looser_cnt)
```

## **3. 설명**

1. 각 학생이 선택하는 학생을 쭉 따라가다가 다음의 `조건` 조건에 해당하면 종료한다.
    - `조건`
        1. 어느 학생을 선택했을 때, 대상 학생이 선택하는 학생이 본인일 때
            - 이 경우 첫 선택부터 대상 학생을 선택한 학생까지 전부가 어느 팀에도 속하지 않게 된다.
        2. 어느 학생을 선택했을 때, 대상 학생이 이미 다른 학생을 선택했을때
            - 이 경우 선택 과정 중간부터 팀이 구성된다. 팀으로 생각했던 학생 중, 마지막으로 선택을 받은 학생 이전까지가 어느 팀에도 속하지 않게 된다.
        3. 어느 학생을 선택했을 때, 대상 학생이 첫 선택 학생일 때
            - 첫 학생부터 마지막 학생까지가 모두 팀원이다.
2. 종료 후 팀으로 구성할 수 없다고 판단되면, 팀에 속하지 않는 학생의 수를 증가시킨다.

## **4. 여정**

1. 팀이 중간에 짤리는 경우를 고려하지 못해 실패
2. Python의 Set이 순서를 보장한다고 가정하여 실패
3. 성공

## **5. 결과**
![image](https://user-images.githubusercontent.com/41278416/89256449-19203600-d65f-11ea-8e06-85e45eb1d2d7.png)