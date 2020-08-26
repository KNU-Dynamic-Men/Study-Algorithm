# 16986 - 인싸들의 가위바위보

## 1. 개요

- https://www.acmicpc.net/problem/16986
- ~~저도 인싸가 되고 싶습니다~~~
- ~~요즘 인싸들은 **가위불바위총번개악마용물공기보스펀지늑대나무사람뱀**이라는 게임을 하는지 확인 부탁드립니다~~

## 2. 코드

```python
from sys import stdin
from collections import deque
n, k = map(int, stdin.readline().split())
arr = [list(map(int, stdin.readline().split())) for _ in range(n)]
a = deque()
b = list(map(int, stdin.readline().split()))
c = list(map(int, stdin.readline().split()))
check = [False for _ in range(n+1)]
find = False

def check_win(fir, las):
    return arr[fir-1][las-1]

def versus_ab(a_val, b_val):
    if check_win(a_val, b_val) == 2:
        return True
    else:
        return False

def versus_ac(a_val, c_val):
    if check_win(a_val, c_val) == 2:
        return True
    else:
        return False

def versus_bc(b_val, c_val):
    if check_win(b_val, c_val) == 2:
        return True
    else:
        return False

def play_game(idx_a, idx_b, idx_c, ver_a, ver_b, ver_c, win_count):
    if win_count[0] >= k:
        return True
    if win_count[1] >= k or win_count[2] >= k:
        return False
    if idx_a >= n:
        return False

    if ver_a and ver_b:
        if versus_ab(a[idx_a], b[idx_b]):
            win_count[0] += 1
            return play_game(idx_a + 1, idx_b + 1, idx_c, True, False, True, win_count)
        else:
            win_count[1] += 1
            return play_game(idx_a + 1, idx_b + 1, idx_c, False, True, True, win_count)

    elif ver_a and ver_c:
        if versus_ac(a[idx_a], c[idx_c]):
            win_count[0] += 1
            return play_game(idx_a + 1, idx_b, idx_c + 1, True, True, False, win_count)
        else:
            win_count[2] += 1
            return play_game(idx_a + 1, idx_b, idx_c + 1, False, True, True, win_count)

    elif ver_b and ver_c:
        if versus_bc(b[idx_b], c[idx_c]):
            win_count[1] += 1
            return play_game(idx_a, idx_b + 1, idx_c + 1, True, True, False, win_count)
        else:
            win_count[2] += 1
            return play_game(idx_a, idx_b + 1, idx_c + 1, True, False, True, win_count)

def find_a(cnt):
    global find
    if find:
        return
    if cnt == n:
        find = play_game(0, 0, 0, True, True, False, [0, 0, 0])
        if find:
            return
    for i in range(1, n+1):
        if find:
            return
        if not check[i]:
            check[i] = True
            a.append(i)
            find_a(cnt + 1)
            check[i] = False
            a.pop()

find_a(0)
if find:
    print(1)
else:
    print(0)
```

## 3. 설명

1. 구현 방법 구상

    - 시뮬레이션 문제
    - 문제에서 요구하는 사항을 하나하나 차근차근 읽어본 후 부분부분 나누어서 구상 후 구현한다

2. 어떻게 나눌 것인가...?

    - 먼저 지우가 어떤 순서로 인싸 가위바위보의 손동작을 낼 것인지 파악한다
    - 그다음 게임을 진행한다
    - 게임의 성공 여부에 딸라 다음 손동작을 찾을 것인지, 게임을 종료할 것인지 선택한다
    - 크게 두 파트로 나눈다: 손동작을 찾는 파트, 게임을 하는 파트

3. 손동작 찾는 파트
    ```python
    def find_a(cnt):
        global find
        if find:
            return
        if cnt == n:
            find = play_game(0, 0, 0, True, True, False, [0, 0, 0])
            if find:
                return
        for i in range(1, n+1):
            if find:
                return
            if not check[i]:
                check[i] = True
                a.append(i)
                find_a(cnt + 1)
                check[i] = False
                a.pop()
    ```
    - 백트래킹을 이용하여 구한다
    - 재귀를 이용하여 백트래킹을 구현한다
    - 만들어질 배열의 크기는 지우가 낼 수 있는 손동작의 최대 수
    - 배열이 지정된 크기로 만들어지면 play game!

4. 게임 파트
    ```python
    def play_game(idx_a, idx_b, idx_c, ver_a, ver_b, ver_c, win_count):
    ```
    - 함수를 구현하고 게임이 끝날 때 까지 재귀를 이용하여 탐색한다
    - `idx_a` : 지우가 해당 게임에서 낼 손동작 배열의 인덱스
    - `idx_b` : 경희가 해당 게임에서 낼 손동작 배열의 인덱스
    - `idx_c` : 민호가 해당 게임에서 낼 손동작 배열의 인덱스
    - `ver_a` : 지우가 이번에 게임을 하는지 여부
    - `ver_b` : 경희가 이번에 게임을 하는지 여부
    - `ver_c` : 민호가 이번에 게임을 하는지 여부
    - `win_count` : `[지우의 승리 수, 경희의 승리 수, 민호의 승리 수]`가 저장된 배열

    ```python
    if win_count[0] >= k:
        return True
    if win_count[1] >= k or win_count[2] >= k:
        return False
    if idx_a >= n:
        return False
    ```
    - 지우의 승리 수가 지정된 승리 수를 만족한 경우 모든 손동작을 다르게 내어 우승할 수 있다는 의미이므로 `True`를 반환
    - 경희나 민호가 지정된 승리 수를 만족한 경우 해당 게임은 우승할 수 없다는 의미이므로 `False`를 반환
    - 지우의 손동작 배열 인덱스가 손동작 수보다 커지는 경우 모든 손동작을 다르게 내어도 우승할 수 없다는 의미이므로 `False`를 반환

    ```python
    if ver_a and ver_b:
        if versus_ab(a[idx_a], b[idx_b]):
            win_count[0] += 1
            return play_game(idx_a + 1, idx_b + 1, idx_c, True, False, True, win_count)
        else:
            win_count[1] += 1
            return play_game(idx_a + 1, idx_b + 1, idx_c, False, True, True, win_count)

    elif ver_a and ver_c:
        if versus_ac(a[idx_a], c[idx_c]):
            win_count[0] += 1
            return play_game(idx_a + 1, idx_b, idx_c + 1, True, True, False, win_count)
        else:
            win_count[2] += 1
            return play_game(idx_a + 1, idx_b, idx_c + 1, False, True, True, win_count)

    elif ver_b and ver_c:
        if versus_bc(b[idx_b], c[idx_c]):
            win_count[1] += 1
            return play_game(idx_a, idx_b + 1, idx_c + 1, True, True, False, win_count)
        else:
            win_count[2] += 1
            return play_game(idx_a, idx_b + 1, idx_c + 1, True, False, True, win_count)
    ```
    - 지우 vs 경희, 지우 vs 민호, 경희 vs 민호를 순서대로 구현한다
    - 각각의 게임에서 누가 이겼는지에 따라 win_count 값을 증가시켜 주고, 다음 경기 상대를 인자로 넣고 함수를 호출한다

## 4. 결과

![image](https://user-images.githubusercontent.com/29600820/91166530-79aa0c80-e70d-11ea-9490-9d0d7571d9cd.png)

- 처음 제출하기 전 로컬 환경에서 실행 시 오래 걸리는 문제로 인해 처음은 `PyPy3`로 제출 후 `Python3`으로 제출하였다
- ...그럴 필요는 없었다