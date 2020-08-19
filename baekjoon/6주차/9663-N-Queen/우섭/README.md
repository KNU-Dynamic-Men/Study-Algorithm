# 9663 - N-Queen

## 1. 개요

https://www.acmicpc.net/problem/9663

## 2. 코드

- **PyPy3**로 제출
```python
from sys import stdin
getN = int(stdin.readline())
ans = 0
hor = [False]*getN
croleft = [False]*(2*getN-1)
croright = [False]*(2*getN-1)

def find(t):
    if t == getN:
        global ans
        ans += 1
        return
    for i in range(getN):
        if hor[i] or croleft[i + t] or croright[t - i + getN - 1]: continue
        hor[i] = croleft[i + t] = croright[t - i + getN - 1] = True
        find(t+1)
        hor[i] = croleft[i + t] = croright[t - i + getN - 1] = False

find(0)
print(ans)
```

## 3. 설명

1. 구현 방법 구상

    - 퀸을 체스판에 둘 때 마다 해당 위치 기준 가로, 세로, 대각선에 퀸이 있는지 확인하면 된다
    - 다만 이를 어떻게 체크를 할 것인가...
    - 하나하나 탐색하면 안그래도 오래 걸리는 문제가 더더욱 오래 걸릴 것이다
    - [BaaaaaaaarkingDog - [실전 알고리즘] 0x0C강 - 백트래킹](https://baaaaaaaaaaaaaaaaaaaaaaarkingdog.tistory.com/945?category=773649) 강의글과 [PROJECT REBAS - BOJ 9663 · N-Queen](https://rebas.kr/761) 블로그 글을 보고 힌트를 얻어 구현하였다

2. 구현

    ```python
    hor = [False]*getN
    croleft = [False]*(2*getN-1)
    croright = [False]*(2*getN-1)
    ```
    - `hor`: 세로 기준 퀸이 있을 수 있는 위치
    - `croleft`: 왼쪽 아래 방향 대각선 기준
    - `croright`: 오른쪽 아래 방향 대각선 기준

    ```python
    for i in range(getN):
        if hor[i] or croleft[i + t] or croright[t - i + getN - 1]: continue
        hor[i] = croleft[i + t] = croright[t - i + getN - 1] = True
        find(t+1)
        hor[i] = croleft[i + t] = croright[t - i + getN - 1] = False
    ```
    - 가로줄 기준 하나의 퀸만 존재할 수 있으므로 `getN`개 좌표 기준으로 탐색한다
    - 세로, 대각선 위치에 퀸이 존재하는 경우 다음 좌표를 탐색한다
    - 퀸이 놓아지는 자리와 퀸이 이동 가능한 좌표의 값을 `True`로 바꾸어준다
    - 재귀를 이용하여 다음 좌표를 탐색한다
    - 탐색이 완료되면 `False`로 바꾸어 주어 다음 탐색을 진행한다

3. 제출

    - 해당 문제는 `Python3`로 제출 시 대부분의 케이스에 *시간 초과*가 발생한다
    - [참고글 - 파이썬으로 풀 경우 추가 시간이 필요한 게 아닌지 한번 봐주세요](https://www.acmicpc.net/board/view/34490)
    - 따라서 `PyPy3`로 제출하였다
    - `N`의 값이 큰 경우에는 미리 계산해둔 값을 출력하게 하는 편법도 있지만...시도하지는 않았다

## 4. 결과
![image](https://user-images.githubusercontent.com/29600820/89909694-502bb400-dc2a-11ea-834d-c3596da15d14.png)
