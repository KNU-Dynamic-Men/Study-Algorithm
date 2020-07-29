# 10845 - 큐

## 1. 개요
https://www.acmicpc.net/problem/10845
- **큐 - Queue**란?
- 사전적인 단어의 의미는 **대기 행렬**
- 먼저 집어 넣은 데이터가 먼저 나오는 **FIFO**(First In First Out)구조로 되어있다.
- <img src="https://media.vlpt.us/post-images/pa324/392c0570-9fa4-11e9-b079-63bbcd31f7a4/image.png" width="200">
- 즉, 먼저 집어넣은 데이터가 나중에 나오는 Stack과는 반대되는 개념이다.
- 참고 링크: [위키피디아: 큐](https://ko.wikipedia.org/wiki/%ED%81%90_(%EC%9E%90%EB%A3%8C_%EA%B5%AC%EC%A1%B0))

## 2. 코드
```python
import sys

n = int(sys.stdin.readline())
stack = []
for _ in range(n):
    input = sys.stdin.readline()
    if 'push' in input:
        stack.append(int(input.split()[1]))
    elif 'pop' in input:
        print(stack.pop(0) if len(stack) > 0 else -1)
    elif 'size' in input:
        print(len(stack))
    elif 'empty' in input:
        print(1 if len(stack) == 0 else 0)
    elif 'front' in input:
        print(stack[0] if len(stack) != 0 else -1)
    elif 'back' in input:
        print(stack[-1] if len(stack) != 0 else -1)
```

## 3. 설명

1. 구현 방법 구상

    - 이전 문제인 ```Stack```과 비슷한 코드이다. ([참고 링크](https://github.com/KNU-Dynamic-Men/Study-Algorithm/tree/master/baekjoon/10828-%EC%8A%A4%ED%83%9D/%EC%9A%B0%EC%84%AD))
    - 차이점은 ```pop``` 명령어를 받을 시 배열의 맨 뒤가 아닌 맨 앞의 값이 pop 되어야 한다는 점과, 명령어 ```top```이 사라지고 ```front```와 ```back```이 생겼다는 점이다.

2. 세부 사항 구현

    - 발견한 차이점들에 맞게 세부 코드를 수정한다.
    - ```pop``` 명령어의 경우 인자를 주어 **배열의 맨 앞이 반환되도록** 수정한다.
    ```python
    print(stack.pop(0) if len(stack) > 0 else -1)
    ```
    - ```front```는 배열의 **가장 첫번째 값**을 출력하도록 하고, ```back```은 배열의 **가장 마지막 값**을 출력하도록 한다.
    ```python
    elif 'front' in input:
        print(stack[0] if len(stack) != 0 else -1)
    elif 'back' in input:
        print(stack[-1] if len(stack) != 0 else -1)
    ```

## 4. 결과

![image](https://user-images.githubusercontent.com/29600820/87263251-912f8c00-c4f7-11ea-8c00-26c6bb6aa586.png)