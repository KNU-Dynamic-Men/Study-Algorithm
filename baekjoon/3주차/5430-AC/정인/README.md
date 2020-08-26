# 5430 - AC

## 01 개요
- 문제: [boj/5430 AC](https://www.acmicpc.net/problem/5430)
- 구현: Python3

## 02 코드
```python
import collections
import sys

for _ in range(int(sys.stdin.readline().rstrip())):
    function_list = list(sys.stdin.readline().rstrip())
    if int(sys.stdin.readline().rstrip()) > 0:
        array_body = collections.deque(map(int, sys.stdin.readline().rstrip()[1:-1].split(",")))
    else:
        sys.stdin.readline()
        array_body = []

    error = False
    location_positive = True
    for function_value in function_list:
        if function_value == 'D':
            if not array_body:
                error = True
                print("error")
                break
            
            if location_positive:
                array_body.popleft()
            else:
                array_body.pop()
        elif function_value == 'R':
            location_positive = not location_positive

    if not error:
        if location_positive:
            print(f'[{",".join(list(map(str, array_body)))}]')
        else:
            print(f'[{",".join(list(map(str, list(array_body)[::-1])))}]')
```

## 03 여정
- 처음 구현된 결과를 가지고 보니 **덱(Deque)이 아닌 리스트로** 구현이 되어 있었음
- Python 내 collections 라이브러리에서 사용할수 있는 deque 활용 ([밑단 C가 `O(1)`로 구현되어 있어서](https://stackoverflow.com/a/32543932) `popleft()`가 상당히 빠르다!)
- location_positive 플래그는 결과 값을 반대로 출력할지, 정방향으로 출력할지 결정한다.

## 04 결과
![image](https://user-images.githubusercontent.com/5201073/88026162-6cc75580-cb70-11ea-8efb-d364058ce2fa.png)

