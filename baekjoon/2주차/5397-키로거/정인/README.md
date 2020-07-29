# 5397 - 키 로거

## 01 개요
- 문제: [boj/5397 키 로거](https://www.acmicpc.net/problem/5397)
- 구현: Python3

## 02 코드
```python
import sys

for _ in range(int(sys.stdin.readline().rstrip())):
    list_input = list(sys.stdin.readline().rstrip())

    list_left = []
    list_right = []

    for item in list_input:
        if item == '-':
            if list_left: list_left.pop()
        elif item == '<':
            if list_left: list_right.append(list_left.pop())
        elif item == '>':
            if list_right: list_left.append(list_right.pop())
        else:
            list_left.append(item)

    list_right.reverse()
    print(f'{"".join(list_left)}{"".join(list_right)}')
```

## 03 여정
- [배열 문제](https://github.com/KNU-Dynamic-Men/Study-Algorithm/tree/master/baekjoon/1406-%EC%97%90%EB%94%94%ED%84%B0/%EC%A0%95%EC%9D%B8)와 동일하게 구현하였다

## 04 결과
![image](https://user-images.githubusercontent.com/5201073/87515980-a18e6500-c6b7-11ea-8459-9a625c88eabf.png)
