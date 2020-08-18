# 1629 - 곱셈

## 1. 개요

https://www.acmicpc.net/problem/1629

## 2. 코드

```python
from sys import stdin
# a ^ b % m
def pow(a, b, m):
    if b == 1:
        return a % m
    val = pow(a, b//2, m)
    val = val * val % m
    if (b % 2 == 0):
        return val
    return val * a % m

a, b, m = map(int, stdin.readline().split(' '))
print(pow(a, b, m))
```

## 3. 설명

1. 구현 방법 구상

    - `mod` 연산은 곱셈에 대하여 분배 법칙이 성립한다
    - `(a * b) % c = ((a % c) * (b % c)) % c`
    - 이를 이용하면 아래 식 또한 성립한다
    - `a^2b % c = ((a^b % c) * (a^b % c)) % c`
    - `b`의 값을 최소로 만든 다음 `mod` 연산을 진행한다

2. 함수 구현

    ```python
    def pow(a, b, m):
    ```
    - `a ^ b % m`

    ```python
    if b == 1:
        return a % m
    ```
    - `b`의 값이 `1`인 경우를 임계점으로 두고, 임계점에 다다른 경우 `a`에 `mod` 연산을 한 값을 `return`한다
    ```python
    val = pow(a, b//2, m)
    ```
    - 그렇지 않은 경우 재귀호출을 통하여 값을 받는다

    ```python
    val = val * val % m
    if (b % 2 == 0):
        return val
    return val * a % m
    ```
    - `a^2b % m = ((a^b % m) * (a^b % m)) % m`를 구현한다
    - 만약 `b`가 짝수인 경우는 그대로 `return`하지만, 홀수인 경우 `a`를 한번더 곱해준 후 `mod` 연산을 실행하여 `return`한다

## 4. 결과
![image](https://user-images.githubusercontent.com/29600820/90531528-2128bb80-e1b1-11ea-8720-2aeba99ad293.png)