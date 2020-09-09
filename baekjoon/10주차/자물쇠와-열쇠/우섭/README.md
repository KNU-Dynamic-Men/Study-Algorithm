# 카카오 2020년 상반기 1차 - 자물쇠와 열쇠
## 1. 개요

https://programmers.co.kr/learn/courses/30/lessons/60059

## 2. 코드

```python
import copy

def rotate(arr):
    new_key = [[0]*len(arr) for _ in range(len(arr))]
    for i in range(len(arr)):
        for j in range(len(arr)):
            new_key[i][j] = arr[len(arr) - 1 - j][i]
    return new_key

def check(key, lock, a, b, start, end):
    new_lock = copy.deepcopy(lock)
    for i in range(len(key)):
        for j in range(len(key)):
            if key[i][j] and new_lock[i + a][j + b]:
                return False
            if key[i][j] and not new_lock[i + a][j + b]:
                new_lock[i + a][j + b] = 1
                continue
    for i in range(start, end):
        for j in range(start, end):
            if not new_lock[i][j]:
                return False
    return True

def solution(key, lock):
    new_len = ((len(key)-1) * 2) + len(lock)
    new_lock = [[0] * new_len for _ in range(new_len)]
    for i in range(len(lock)):
        for j in range(len(lock)):
            new_lock[i + (len(key) - 1)][j + (len(key) - 1)] = lock[i][j]
    start = len(key) - 1
    end = start + len(lock)
    for _ in range(4):
        for i in range(0, end):
            for j in range(0, end):
                if check(key, new_lock, i, j, start, end):
                    return True
        key = rotate(key)
    return False
```

## 3. 설명

1. 구현 방법 구상

    - 열쇠를 돌려가면서 자물쇠에 맞는지 확인! 단, 뒤집지는 않는다
    - 자물쇠를 벗어난 곳에 열쇠의 돌기가 있는건 상관없지만, 자물쇠의 돌기에 열쇠의 돌기가 있어서는 안된다
    - `M, N`의 범위가 크지 않으므로 전체탐색

2. 열쇠 돌리기 구현

    ```python
    def rotate(arr):
        new_key = [[0]*len(arr) for _ in range(len(arr))]
        for i in range(len(arr)):
            for j in range(len(arr)):
                new_key[i][j] = arr[len(arr) - 1 - j][i]
        return new_key
    ```
    - `rotate` 함수로 따로 구현
    - `arr`: 90도 회전하려는 배열
    - `new_key`: 90도 회전된 배열
    - `(i, j)`의 90도 회전된 좌표는 `((배열의 길이) -1 - j, i)`

3. 열쇠로 자물쇠 열어보기
    ```python
    def check(key, lock, a, b, start, end):
    ```
    - 함수를 따로 구현하여 체크
    - `key, lock`: 열쇠와 자물쇠 배열
    - `a, b`: 자물쇠에서 열쇠가 들어가는 왼쪽 위 좌표
    - `start, end`: 원래 자물쇠 배열이 들어가있는 좌표의 인덱스
    ```python
    new_lock = copy.deepcopy(lock)
    ```
    - 기존 배열이 덮어씌워지지 않도록 배열을 복사한다
    ```python
    for i in range(len(key)):
        for j in range(len(key)):
            if key[i][j] and new_lock[i + a][j + b]:
                return False
            if key[i][j] and not new_lock[i + a][j + b]:
                new_lock[i + a][j + b] = 1
                continue
    ```
    - 자물쇠에 열쇠를 맞추어본다
    - 열쇠의 돌기와 자물쇠의 돌기가 같은 좌표인 경우 `False`를 바로 반환한다
    - 열쇠의 돌기와 자물쇠의 홈이 맞거나 자물쇠를 벗어난 곳에 들어가는 경우 해당 좌표를 `1`로 바꾸어준다
    ```python
    for i in range(start, end):
        for j in range(start, end):
            if not new_lock[i][j]:
                return False
    return True
    ```
    - 열쇠 맞추기가 끝나면 자물쇠를 확인한다
    - 자물쇠 배열에 하나라도 `0`이 있으면 열쇠가 맞지 않았다는 뜻이므로 `False`를 반환한다
    - 그 외의 경우 자물쇠 배열이 모두 `1`이면 `True`를 반환한다
4. 메인 함수 구현
    ```python
    def solution(key, lock):
    ```
    - 함수는 문제에서 제시된 이름과 매개변수로 구현한다
    ```python
    new_len = ((len(key)-1) * 2) + len(lock)
    new_lock = [[0] * new_len for _ in range(new_len)]
    for i in range(len(lock)):
        for j in range(len(lock)):
            new_lock[i + (len(key) - 1)][j + (len(key) - 1)] = lock[i][j]
    start = len(key) - 1
    end = start + len(lock)
    ```
    - `new_len`: 자물쇠에 상하좌우 빈 공간을 추가한 크기
    - `new_lock`: 자물쇠에 상하좌우 빈 공간을 추가한 배열
    - 상하좌우의 빈 공간은 `(열쇠 크기 - 1)`한 크기만큼 추가한다
    - `(열쇠 크기 - 1)`인 이유는 배열의 가운데에 자물쇠가 있는 경우 열쇠는 **무조건 가운데 자물쇠 부분을 포함**하게 된다
    - `start`: 자물쇠의 시작점 인덱스
    - `end`: 자물쇠의 끝 인덱스
    ```python
    for _ in range(4):
        for i in range(0, end):
            for j in range(0, end):
                if check(key, new_lock, i, j, start, end):
                    return True
        key = rotate(key)
    return False
    ```
    - 탐색을 시작한다
    - 열쇠가 자물쇠에 맞는지 확인한다
    - 범위가 `end`까지인 이유는 `check` 함수에서 열쇠의 인덱스 계산 시 배열을 초과할 수 있으므로
    - 탐색이 완료되고 찾지 못한 경우 `key`를 90도 돌린 후 탐색을 다시 시작한다
    - 탐색 결과에 따라 `True` 또는 `False`를 반환한다

## 4. 결과
![image](https://user-images.githubusercontent.com/29600820/92490955-97f32a80-f22c-11ea-9417-783a9930be2f.png)
