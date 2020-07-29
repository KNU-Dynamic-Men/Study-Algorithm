# 4889 - 안정적인 문자열

## 1. 개요

https://www.acmicpc.net/problem/4889

## 2. 코드

```python
from sys import stdin
import math

num = 0
while (True):
    num += 1
    com = list(stdin.readline().rstrip())
    if '-' in com[0]:
        break
    count = 0
    st = []
    for i in com:
        if i == '{':
            st.append('{')
        else:
            if st:
                st.pop()
            else:
                st.append('{')
                count += 1
    count += math.ceil(len(st) / 2)
    print('{0}. {1}'.format(num, count))
```

## 3. 설명

1. 구현 방법 구상

    - 안정적인 문자열 → 괄호로 열리고 닫힌 문자열 또는 비어있는 문자열
    - 모든 괄호가 닫혀있으면 안정적인 문자열이다
    - 평범하게 괄호가 닫혀있는지 여부를 확인하려면 ```Stack```으로만 풀어도 된다
    - 하지만 이 문제는 괄호를 모두 닫기 위해 필요한 추가 괄호 수 뿐만 아니라 **기존 괄호 중 바꾸어야 하는 괄호의 수**도 알아야 한다
    - 전체 길이가 짝수인 경우는 바꾸는 것으로 충분하지만, 홀수인 경우 1개를 추가해야 한다.

2. 구현

    - 입력에 따라 리스트 ```st```에 ```append()```를 해주거나, ```pop()```을 해주어 괄호가 안정적인지 계산한다.
    - ```{```가 입력된 경우 ```st```를 ```append()```해준다
    ```python
    if i == '{':
            st.append('{')
    ```
    - ```}```가 입력된 경우 st에 값이 하나라도 존재하는 경우 ```st```를 ```pop()```해주고, 비어있는 경우 ```{```로 바꾸어주고 ```count```를 증가시켜준다.
    ```python
    else:
        if st:
            st.pop()
        else:
            st.append('{')
            count += 1
    ```
3. 최종 출력 값

    - ```count```에 저장되어 있는 값은 지금까지 ```{```로 변환된 ```}```의 갯수
    - 현재 ```st``` 배열에 남아있는 ```{```의 갯수를 반을 나누어준 후 반올림하여 추가로 필요한 연산의 수를 증가시켜 준다
    ```python
    count += math.ceil(len(st) / 2)
    ```
    - ```round()``` 함수를 쓰지 않은 이유는 **사사오입 방식**으로 작동하므로 정상적인 값이 나오지 않을 수 있음
    - 반올림하는 이유는 남은 ```{```의 수가 홀수인 경우 ```}```를 하나 추가해야 하므로 +1을 하기 위해서

4. 결과

![image](https://user-images.githubusercontent.com/29600820/87763343-9c641e00-c84f-11ea-8662-4960d69f758c.png)