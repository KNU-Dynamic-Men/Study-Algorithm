# 10828 - 스택

## 01 개요
- 문제: [boj/10828 스택](https://www.acmicpc.net/problem/10828)
- 구현: Python3

## 02 코드
```python
import sys
input_length = int(sys.stdin.readline().rstrip())
stack_body = [None] * 10000
stack_topidx = 0

for _ in range(input_length):
    user_input = sys.stdin.readline().rstrip().split(" ")
    if user_input[0] == 'push':
        stack_body[stack_topidx] = int(user_input[1])
        stack_topidx += 1
    elif user_input[0] == 'pop':
        if stack_topidx <= 0:
            print(-1)
            continue
        print(stack_body[stack_topidx - 1])
        stack_topidx -= 1
    elif user_input[0] == 'size':
        print(stack_topidx)
    elif user_input[0] == 'empty':
        print('1' if stack_topidx <= 0 else '0')
    elif user_input[0] == 'top':
        if stack_topidx <= 0:
            print(-1)
            continue
        print(stack_body[stack_topidx - 1])
```

## 03 설명
- 배열로 구현한 스택 문제다.
- 스택의 맨 위는 `top`이며 `push` 명령으로 새로운 데이터를 `top`에 쌓을 수 있다. 데이터는 `push`할수록 계속 쌓이지만 이 문제에서는 최대 명령어 갯수가 10000개이므로 그만큼 미리 할당해두고 사용하였다.
- `pop` 명령 시 맨 위에 있는 데이터를 빼온다(빼온 데이터는 더이상 스택에 없고, 그 뒤에 있는 데이터가 다음 `pop` 대상이다).
- `top` 명령어로 맨 위에 있는 데이터를 볼 수 있다 (보통 `peek`라는 이름으로 구현되어 있다)

## 04 결과
![image](https://user-images.githubusercontent.com/5201073/87516247-0c3fa080-c6b8-11ea-81e3-e4ce99e06c58.png)
