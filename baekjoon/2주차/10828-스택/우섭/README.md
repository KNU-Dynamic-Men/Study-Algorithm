# 10828 - 스택

## 1. 개요
https://www.acmicpc.net/problem/10828
- **스택 - Stack**이란?
- 사전적인 단어의 의미는 **지붕의 굴뚝**
- 한쪽에서만 자료를 넣거나 뺄 수 있는 **선형 구조**(LIFO - Last in First Out)로 되어 있다.
- <img src="https://upload.wikimedia.org/wikipedia/commons/thumb/2/29/Data_stack.svg/1024px-Data_stack.svg.png" width="200">
- 주로 함수를 호출할 때 인수의 전달 등에 이용된다.
- 참고 링크: [위키피디아: 스택](https://ko.wikipedia.org/wiki/%EC%8A%A4%ED%83%9D)

## 2. 코드

```python
import sys

getN = int(sys.stdin.readline())
stack = []
for _ in range(getN):
    input = sys.stdin.readline()
    if 'push' in input: 
        stack.append(int(input.split()[1]))
    elif 'pop' in input:
        print(stack.pop() if len(stack) > 0 else -1)
    elif 'size' in input:
        print(len(stack))
    elif 'empty' in input:
        print(1 if len(stack) == 0 else 0)
    elif 'top' in input:
        print(stack[-1] if len(stack) != 0 else -1)
```

## 3. 설명

1. 구현 방법 구상

    - 이전 학교 자료구조 수업 때 JAVA를 이용하여 ```LinkedList```를 응용한 ```Stack```을 구현하고 과제를 수행한 적이 있다. ([참고 링크](https://github.com/rokuta1059/DataStructurePractice/tree/master/src/day_3))
    - 이때의 지식을 토대로 **Python**을 통하여 구현을 해 보았다.
    - Python의 경우, ```LinkedList```는 존재하지 않지만, ```List```에 필요한 메소드가 구현되어 있기 때문에, 이를 이용하여 비슷하게 구현이 가능하다. ([참고 링크](https://docs.python.org/ko/3/tutorial/datastructures.html))

2. 세부 사항 구현

    - 기본적인 요구사항만 충족하면 되는 데다가, 요구사항 또한 ```Stack```의 기본 원리나 다름없기 때문에 *어렵지 않다*.
    - <img src="https://ww.namu.la/s/2b38937bb1ead788420b12c0f2f31d441cfc8af59d3fba718c03085bd37cacece7eaae2bc0e47717d99177eb018bd48b66075acf90a58840228eeb6fa02eeca16d3f85d47fe06321cd38fff05593e528fc4790d7fa9d461d58e3d1356ff377f3" width="200">

## 4. 시행 착오

1. lambda

    - 초기에는 함수가 길어지는 것을 대비하여 ```lambda``` 구문을 사용할 생각을 해보았다.
    - ```lambda``` 함수를 출력할 경우 값이 아닌 함수 자체가 출력이 되어버린다...
    - 예시와 결과
    ```python
    print(lambda: stack[-1] if len(stack) != 0 else -1)
    ```
    ```shell
    <function <lambda> at 0x0000029E438B9820>
    ```
    - 따라서 **모든 lambda 함수를 제거**하고 다시 작성하였다.

2. 코드 정리

    - ```if-else``` 구문을 한 줄로 정리하여 가독성을 높이고 코드 길이를 3/4로 줄였다. (601B -> 450B)
    ```python
    elif 'pop' in input:
        if len(stack) != 0:
            print(stack.pop())
        else:
            print('-1')
    ```
    ```python
    elif 'pop' in input:
        print(stack.pop() if len(stack) > 0 else -1)
    ```
    - 다만, 실행 시간은 구조가 바뀌지 않았기 때문에 동일하였다.
    - 실행 시간 또한 최소로 하는 방법을 연구해보아야 겠다.

## 5. 결과

![image](https://user-images.githubusercontent.com/29600820/87262380-b2db4400-c4f4-11ea-89a9-d45aa4d1e564.png)
