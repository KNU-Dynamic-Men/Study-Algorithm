# 카카오 2020년 상반기 1차 - 문자열 압축

## 1. 개요

https://programmers.co.kr/learn/courses/30/lessons/60057

## 2. 코드

```python
def solution(s):
    if len(s) == 1:
        return 1
    ans = len(s)
    for i in range(1, len(s) // 2 + 1):
        tmp = s[:i]
        count = 1
        st = ''
        for j in range(i, len(s), i):
            if tmp == s[j:j+i]:
                count += 1
            else:
                if count == 1:
                    st += tmp
                else:
                    st += str(count) + tmp
                count = 1
                tmp = s[j:j+i]
        if len(s) % i != 0:
            st += tmp
        else:
            if count > 1:
                st += str(count) + tmp
            else:
                st += tmp
        ans = min(ans, len(st))

    return ans
```

## 3. 설명

1. 구현 방법 구상

    - 문자열을 자른다 -> 전체 문자열을 `n`의 크기만큼 순서대로 자른다
    - 자른 문자열이 연속으로 나온다 -> 앞에 갯수를 쓰면서 압축
    - 문자를 미리 잘라두거나, 인덱스를 이용하여 차례대로 확인하며 진행
    - 문자열의 최대 크기는 `1000`이므로 **브루트 포스**를 이용한다
    - **최소값**을 반환!

2. 초기값 지정

    ```python
    if len(s) == 1:
        return 1
    ans = len(s)
    ```
    - 문자열의 길이가 1이면 압축이 불가능하므로 길이 그대로 1을 반환한다
    - 정답의 초기값으로 원래 문자열의 길이 = 압축되지 않은 문자열의 길이를 저장

3. 탐색 범위

    ```python
    for i in range(1, len(s) // 2 + 1):
        tmp = s[:i]
        count = 1
        st = ''
    ```
    - 압축을 할 때 현재 단위 길이가 원래 문자 길이의 절반을 넘어가게 되는 경우 탐색의 의미가 없으므로 단위 범위는 **문자열 길이의 절반까지**
    - `tmp` : 현재 문자열
    - `count` : 현재 문자열이 연속으로 나온 수
    - `st` : 압축되어 만들어진 문자열

4. 탐색

    ```python
    for j in range(i, len(s), i):
        if tmp == s[j:j+i]:
            count += 1
        else:
            if count == 1:
                st += tmp
            else:
                st += str(count) + tmp
            count = 1
            tmp = s[j:j+i]
    ```
    - 단위가 정해지면 문자 전체를 탐색한다
    - `i` : 단위의 크기
    - 범위 : 문자열의 길이
    - `for` 문을 돌면서 `i`를 계속해서 더해준다
    - 현재 저장되어 있는 `tmp`의 문자열과 탐색중인 문자열이 같은 경우 `count`를 증가시킨다
    - 문자열이 다른 경우 `count`가 `1`이면 문자열에 `tmp`값을 그대로 추가한다
    - `count`가 `1`이 아니면 `count`를 `str` 타입으로 바꾼 값과 `tmp` 값을 추가한다
    - `count`를 `1`로 추가시키고 `tmp` 또한 현재 탐색중인 값으로 초기화한다

5. 탐색 완료 후

    ```python
    if len(s) % i != 0:
        st += tmp
    else:
        if count > 1:
            st += str(count) + tmp
        else:
            st += tmp
    ```
    - 만약 단위값으로 **나누어 떨어지지 않는 길이**인 경우 마지막 값은 무조건 압축이 불가능하므로 그대로 추가하여 준다
    - 나누어 떨어지는 경우 후처리를 해준다
    - `count`가 `1`보다 크면 `count` 값과 `tmp`를 문자열에 추가해주고, 아닌 경우 `tmp`만 문자열에 추가하여준다

6. 최소값 갱신

    ```python
    ans = min(ans, len(st))
    ```
    - 완성된 압축된 문자열을 이용하여 최소값을 구하고 기존 값과 비교하여 저장한다

## 4. 결과

![image](https://user-images.githubusercontent.com/29600820/92398725-0f10bc00-f164-11ea-85d1-1afdf481d2bd.png)
