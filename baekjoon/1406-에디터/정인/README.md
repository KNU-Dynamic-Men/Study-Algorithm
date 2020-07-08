# 1406 - 에디터

## 01 개요
- 문제: [boj/1406 에디터](https://www.acmicpc.net/problem/1406)
- 구현: Python3

## 02 코드
```python
import sys

body_left = list(sys.stdin.readline().strip())
body_right = list()
num_commands = int(sys.stdin.readline())

for _ in range(num_commands):
    command = sys.stdin.readline().strip()
    if command == 'L': # move left
        if len(body_left) > 0: body_right.append(body_left.pop())
    elif command == 'D': # move right
        if len(body_right) > 0: body_left.append(body_right.pop())
    elif command == 'B': # delete left
        if len(body_left) > 0: body_left.pop()
    elif command[0] == 'P': # append left
        body_left.append(command.split(" ")[1])
    else:
        raise RuntimeError("Unexpected input: " + command)

body_right.reverse()

body_left_concat = ''.join(body_left)
body_right_concat = ''.join(body_right)
print(body_left_concat, end='')
print(body_right_concat)
```

## 03 여정
- 에디터의 동작 4가지를 구현하는 문제이다 - 커서 이동 명령어 L, D와 삭제 명령어 B, 삽입 명령어 P 총 4가지를 구현한다.
- 첫번째 시도 - 전체 명령을 배열 연산으로 구현 -> 실패(배열의 중간 원소 삽입 시 시간이 너무 오래 걸리는 문제 + sys.stdin이 아닌 input()을 사용함에 의한 시간 초과)
- 두번째 시도 - 배열 두 개를 가지고 구현 -> 실패(복잡성이 늘어남에 따라 시간이 너무 오래 걸리는 문제 + sys.stdin이 아닌 input()을 사용함에 의한 시간 초과)
- 세번째 시도 - 정우군의 코드 [참조](https://github.com/KNU-Dynamic-Men/Study-Algorithm/blob/master/baekjoon/1406-%EC%97%90%EB%94%94%ED%84%B0/%EC%A0%95%EC%9A%B0/1406_stack_ver.py) -> 성공

## 04 결과
![image](https://user-images.githubusercontent.com/5201073/86909205-ac467880-c152-11ea-8595-56ec7edbc926.png)
