# 카카오 2020년 인턴십 - 키패드 누르기

## 1. 개요

https://programmers.co.kr/learn/courses/30/lessons/67256

## 2. 코드

```python
def change(number):
    if number == 1:
        return [0, 0]
    elif number == 2:
        return [0, 1]
    elif number == 3:
        return [0, 2]
    elif number == 4:
        return [1, 0]
    elif number == 5:
        return [1, 1]
    elif number == 6:
        return [1, 2]
    elif number == 7:
        return [2, 0]
    elif number == 8:
        return [2, 1]
    elif number == 9:
        return [2, 2]
    elif number == 0:
        return [3, 1]

def leng(now, next):
    return abs(now[0] - next[0]) + abs(now[1] - next[1])

def check(now_left, now_right, next, hand):
    if next in [1, 4, 7]:
        return "left"
    elif next in [3, 6, 9]:
        return "right"
    next_pad = change(next)
    left_len = leng(now_left, next_pad)
    right_len = leng(now_right, next_pad)
    if left_len < right_len:
        return "left"
    elif left_len > right_len:
        return "right"
    else:
        return hand

def solution(numbers, hand):
    left = [3, 0]
    right = [3, 2]
    answer = ''

    for i in numbers:
        a = check(left, right, i, hand)
        if a == "left":
            left = change(i)
            answer += 'L'
        else:
            right = change(i)
            answer += 'R'
    return answer
```

## 3. 설명

1. 구현 방법 구상

    - 키패드의 번호를 **좌표로 변환**
    - 좌표간의 거리 계산(`|(x2 - x1)| + |(y2 - y1)|`)을 통해 최소값인 손을 이용하여 입력한다

2. 구현
    ```python
    def change(number):
    ```
    - 먼저 숫자를 좌표로 바꾸어주는 함수를 따로 구현하였다
    - `if - else` 문을 돌면서 해당하는 좌표로 바꾸어준다
    - ~~탐색하는데 시간이 걸릴 수 있으므로 다음에는 dict를 사용해보자~~
    ```python
    def leng(now, next):
        return abs(now[0] - next[0]) + abs(now[1] - next[1])
    ```
    - 거리를 계산하는 함수도 따로 구현하였다
    - `x`좌표와 `y`좌표 각각의 차를 구하고 절대값을 더해준다
    ```python
    def check(now_left, now_right, next, hand):
        if next in [1, 4, 7]:
            return "left"
        elif next in [3, 6, 9]:
            return "right"
    ```
    - 해당 좌표를 누르는 손이 왼손인지 오른손인지 판단하는 함수도 구현한다
    - `now_left`: 현재 왼손 좌표
    - `now_right`: 현재 오른손 좌표
    - `next`: 입력하려는 버튼
    - `hand`: 주로 사용하는 손
    - 주어진 조건인 `왼쪽 열의 3개의 숫자 1, 4, 7을 입력할 때는 왼손 엄지손가락을 사용합니다.` 와 `오른쪽 열의 3개의 숫자 3, 6, 9를 입력할 때는 오른손 엄지손가락을 사용합니다.`를 먼저 설정한다
    ```python
    next_pad = change(next)
    left_len = leng(now_left, next_pad)
    right_len = leng(now_right, next_pad)
    if left_len < right_len:
        return "left"
    elif left_len > right_len:
        return "right"
    else:
        return hand
    ```
    - `2, 5, 8, 0`의 숫자인 경우 현재 왼손과 오른손의 위치를 기준으로 거리를 계산한다
    - 거리가 짧은 손을 반환한다
    - 만약 거리가 같다면 주 사용 손을 반환한다
    ```python
    def solution(numbers, hand):
        left = [3, 0]
        right = [3, 2]
        answer = ''

        for i in numbers:
            a = check(left, right, i, hand)
            if a == "left":
                left = change(i)
                answer += 'L'
            else:
                right = change(i)
                answer += 'R'
        return answer
    ```
    - 메인 함수를 구현한다
    - 초기 위치는 왼손은 `*`, 오른손은 `#`이므로 해당 좌표를 초기값으로 둔다
    - 입력받은 번호 배열을 하나씩 탐색한다
    - `check` 결과 왼손인 경우 `L`, 오른손인 경우 `R`을 `answer` 문자열에 추가해준다
    - 완성된 문자열을 반환한다

## 4. 결과

- 정확성  테스트
    ```
    테스트 1 〉	통과 (0.00ms, 10.3MB)
    테스트 2 〉	통과 (0.01ms, 10.3MB)
    테스트 3 〉	통과 (0.01ms, 10.3MB)
    테스트 4 〉	통과 (0.00ms, 10.2MB)
    테스트 5 〉	통과 (0.01ms, 10.3MB)
    테스트 6 〉	통과 (0.01ms, 10.2MB)
    테스트 7 〉	통과 (0.02ms, 10.4MB)
    테스트 8 〉	통과 (0.03ms, 10.3MB)
    테스트 9 〉	통과 (0.02ms, 10.2MB)
    테스트 10 〉	통과 (0.03ms, 10.2MB)
    테스트 11 〉	통과 (0.06ms, 10.3MB)
    테스트 12 〉	통과 (0.05ms, 10.3MB)
    테스트 13 〉	통과 (0.10ms, 10.3MB)
    테스트 14 〉	통과 (0.23ms, 10.2MB)
    테스트 15 〉	통과 (0.56ms, 10.2MB)
    테스트 16 〉	통과 (0.34ms, 10.3MB)
    테스트 17 〉	통과 (1.03ms, 10.3MB)
    테스트 18 〉	통과 (0.96ms, 10.3MB)
    테스트 19 〉	통과 (3.37ms, 10.3MB)
    테스트 20 〉	통과 (1.10ms, 10.3MB)
    ```
![image](https://user-images.githubusercontent.com/29600820/93980525-b115e280-fdb9-11ea-93a0-f9e4f86284d1.png)