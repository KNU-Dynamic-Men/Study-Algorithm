# 4889 - 안정적인 문자열

## 01 개요
- 문제: [boj/4889 안정적인 문자열](https://www.acmicpc.net/problem/4889)
- 구현: Python3

## 02 코드
```python
import sys

line_number = 1
for line in sys.stdin:
    if line[0] == '-':
        break

    parenthesis_stack = []
    changed_parenthesises = 0

    for parenthesis in line.rstrip():
        if parenthesis == '{':
            parenthesis_stack.append(parenthesis)
        else:  # '}'
            if parenthesis_stack and parenthesis_stack[-1] == '{':
                parenthesis_stack.pop()
            else:
                changed_parenthesises += 1
                parenthesis_stack.append('{')

    # Remaining parenthesis
    changed_parenthesises += int(len(parenthesis_stack) / 2)

    print(f'{line_number}. {changed_parenthesises}')
    line_number += 1
```

## 03 여정
- 괄호 문자열(Parenthesis)을 스택에 넣고 빼고 하는 과정에서 짝을 맞추게 되면 즉시 pop()!
- 짝이 다른 문자열 } 가 나오는 경우, { 로 무조건 바꾼 뒤에 `변경수 += 1`
- 결과적으로 남은 문자열들의 경우  }}}} {{{{ 와 같은 형식으로 구성되어 있음 → `전체 문자열수 / 2` 를 변경수에 더함

## 04 결과
![image](https://user-images.githubusercontent.com/5201073/88025890-fa567580-cb6f-11ea-831f-6e2bb9636492.png)
