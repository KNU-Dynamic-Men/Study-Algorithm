# 카카오 2020년 상반기 1차 - 괄호 변환

## 1. 개요

https://programmers.co.kr/learn/courses/30/lessons/60058

## 2. 코드

```python
from collections import deque

def balance(s):
    t = 0
    for idx, i in enumerate(s):
        if i == '(':
            t += 1
        if i == ')':
            t -= 1
        if t == 0:
            return idx

def is_perfect(s):
    t = deque()
    for i in list(s):
        if i == '(':
            t.append('(')
        else:
            if not t:
                return False
            else:
                t.pop()
    if t:
        return False
    return True
        
def reverse(s):
    tmp = ''
    for i in list(s):
        tmp += ')' if i == '(' else '('
    return tmp

def solution(s):
    if len(s) == 0 or is_perfect(s):
        return s
    idx = balance(s)
    u, v = s[:idx+1], s[idx+1:]
    if is_perfect(u):
        return u + solution(v)
    else:
        return '(' + solution(v) + ')' + reverse(u[1:len(u)-1])
```

## 3. 설명

1. 구현 방법 구상

    - 문제에서 제시한 시나리오 대로 차근차근 구현한다

2. 구현

    1. 입력이 빈 문자열인 경우, 빈 문자열을 반환합니다. 
    ```python
    if len(s) == 0 or is_perfect(s):
        return s
    ```
    2. 문자열 w를 두 "균형잡힌 괄호 문자열" u, v로 분리합니다. 단, u는 "균형잡힌 괄호 문자열"로 더 이상 분리할 수 없어야 하며, v는 빈 문자열이 될 수 있습니다. 
    - 먼저 균형잡힌 문자열을 찾는 함수를 구현한다
    ```python
    def balance(s):
        t = 0
        for idx, i in enumerate(s):
            if i == '(':
                t += 1
            if i == ')':
                t -= 1
            if t == 0:
                return idx
    ```
    - `(`이 나오면 `+1`, `)`이 나오면 `-1`을 하고 결과값이 `0`이 되는 순간의 `index`를 반환한다
    - `index`는 `enumerate`를 이용하여 확인한다
    ```python
    idx = balance(s)
    u, v = s[:idx+1], s[idx+1:]
    ```
    - 반환된 index를 기준으로 문자열을 두 개로 나눈다
    3. 문자열 u가 "올바른 괄호 문자열" 이라면 문자열 v에 대해 1단계부터 다시 수행합니다.     
    3-1. 수행한 결과 문자열을 u에 이어 붙인 후 반환합니다. 
    - 올바른 괄호 문자열인지 확인하는 함수를 먼저 구현한다
    ```python
    def is_perfect(s):
        t = deque()
        for i in list(s):
            if i == '(':
                t.append('(')
            else:
                if not t:
                    return False
                else:
                    t.pop()
        if t:
            return False
        return True
    ```
    - `stack` 알고리즘을 이용해 올바른 괄호 문자열인지 확인한다
    - `(`가 나오면 `push`(`append`), `)`가 나오면 `pop`을 한다
    - `pop`을 할 때 `stack`이 비어 있거나, 모든 작업이 완료된 이후에 `stack`에 값이 남아있는 경우 올바른 괄호 문자열이 아니므로 `False`를 반환한다
    - `stack`에 값이 남아있지 않은 경우 `True`를 반환한다
    ```python
    if is_perfect(u):
        return u + solution(v)
    ```
    - 해당 결과에 따라 작업을 진행하고 반환한다
    4. 문자열 u가 "올바른 괄호 문자열"이 아니라면 아래 과정을 수행합니다.     
    4-1. 빈 문자열에 첫 번째 문자로 '('를 붙입니다.     
    4-2. 문자열 v에 대해 1단계부터 재귀적으로 수행한 결과 문자열을 이어 붙입니다.     
    4-3. ')'를 다시 붙입니다.     
    4-4. u의 첫 번째와 마지막 문자를 제거하고, 나머지 문자열의 괄호 방향을 뒤집어서 뒤에 붙입니다.     
    4-5. 생성된 문자열을 반환합니다.    
    - 먼저 괄호의 방향을 뒤집는 함수를 구현한다
    ```python
    def reverse(s):
        tmp = ''
        for i in list(s):
            tmp += ')' if i == '(' else '('
        return tmp
    ```
    - 문자를 배열로 만든 후 하나씩 탐색하면서 뒤집어준다
    - 뒤집기가 완료된 문자열을 반환한다
    ```python
    else:
        return '(' + solution(v) + ')' + reverse(u[1:len(u)-1])
    ```
    - 해당 결과에 따라 작업을 진행하고 반환한다

## 4. 결과

![image](https://user-images.githubusercontent.com/29600820/92588197-9892cc00-f2d3-11ea-9233-c3e171831856.png)