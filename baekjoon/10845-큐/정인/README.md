# 10845 - 큐

## 01 개요
- 문제: [boj/10845 큐](https://www.acmicpc.net/problem/10845)
- 구현: Python3

## 02 코드
```python
import sys
input_length = int(sys.stdin.readline().rstrip())
queue_body = [None] * 10000
queue_backidx = queue_frontidx = 0

for _ in range(input_length):
    user_input = sys.stdin.readline().rstrip().split(" ")
    if user_input[0] == 'push':
        queue_body[queue_backidx] = int(user_input[1])
        queue_backidx += 1
    elif user_input[0] == 'pop':
        if queue_frontidx >= queue_backidx:
            print(-1)
            continue
        print(queue_body[queue_frontidx])
        queue_frontidx += 1
    elif user_input[0] == 'size':
        print(queue_backidx - queue_frontidx)
    elif user_input[0] == 'empty':
        print('1' if queue_frontidx >= queue_backidx else '0')
    elif user_input[0] == 'front':
        if queue_frontidx >= queue_backidx:
            print(-1)
            continue
        print(queue_body[queue_frontidx])
    elif user_input[0] == 'back':
        if queue_frontidx >= queue_backidx:
            print(-1)
            continue
        print(queue_body[queue_backidx - 1])
```

## 03 설명
- 배열로 구현한 큐 문제다. ~~코드는 정말 [스택](https://github.com/KNU-Dynamic-Men/Study-Algorithm/tree/master/baekjoon/10828-%EC%8A%A4%ED%83%9D/%EC%A0%95%EC%9D%B8/)과 다를께 없다!~~
- 모든 연산은 스택과 달리 FIFO(First-In First-Out)으로 이루어지며, 스택에 추가로 back index가 존재한다.
- 마지막에 `push`한 데이터는 **맨 마지막에** `pop`되어 나온다. 그러므로 여기서의 `push`와 `pop`의 의미는 스택의 그 것과 다르다.
- 처음 `push`한 데이터가 처음으로 `pop`했을 때 나와야 하므로, 맨 처음 데이터의 포인터인 `queue_frontidx`가 필요하다. 반대로 새로운 데이터를 넣는 `push`작업은 `queue_backidx`를 이용한다.
- `queue_backidx`는 Stack에서의 `top`처럼 항상 마지막에 넣은 데이터를 가리킨다. 그러므로 큐가 비어있는지를 판단하기 위해서, 혹은 크기를 결정하기 위해서는 맨 바닥 데이터 위치인 `queue_frontidx`를 참고하면 된다.

## 04 결과
![image](https://user-images.githubusercontent.com/5201073/87516659-b3bcd300-c6b8-11ea-90c0-fc593efa9133.png)