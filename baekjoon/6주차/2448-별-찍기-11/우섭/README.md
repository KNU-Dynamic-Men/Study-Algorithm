# 2448 - 별 찍기 - 11

## 1. 개요

https://www.acmicpc.net/problem/2448

## 2. 코드

```python
from math import log2
getN = int(input())
k = int(log2(getN / 3))
stage = [[' '] * getN * 2 for _ in range(getN)]

def change_for_star(y, x):
    stage[y-2][x+2] = '*'
    stage[y-1][x+1] = stage[y-1][x+3] = '*'
    stage[y][x] = stage[y][x+1] = stage[y][x+2] = stage[y][x+3] = stage[y][x+4] = '*'


def tri(y, x, count):
    if count == 0:
        change_for_star(y, x)
        return

    tmp = 3 * 2 ** (count-1)
    tri(y - tmp, x + tmp, count-1)
    tri(y, x, count-1)
    tri(y, x + 2 * tmp, count-1)

tri(getN-1, 0, k)
for i in stage:
    print(''.join(i))
```

## 3. 풀이

1. 구현 방법 구상

    - 규칙조차 직접 발견을 해야 한다
    - 세로 크기는 N, 가로 크기는 2N
    - 즉 세로는 ![render](https://user-images.githubusercontent.com/29600820/89900496-c7f3e180-dc1e-11ea-8a3d-0b080f472c00.png), 가로는 ![render (1)](https://user-images.githubusercontent.com/29600820/89900587-e78b0a00-dc1e-11ea-9792-d8ede83d1c00.png)
    - ![image](https://user-images.githubusercontent.com/29600820/89900716-10ab9a80-dc1f-11ea-9af6-cbf055e96fcf.png)
    - 가장 작은 삼각형을 임계점으로 설정하고 풀이를 구상한다

2. 재귀 조건 찾기

    - 삼각형의 왼쪽 아래 `*`를 기준점으로 잡고 풀이 구상
    - 재귀의 카운트는 `k`로 지정하고, `k`가 0인 경우 가장 작은 삼각형을 그린다
    - `k`가 `0`이 아니면 다른 세 좌표를 계산한 후 계속 탐색한다

3. 구현

    ```python
    k = int(log2(getN / 3))
    ```
    - `k`를 구하기 위해 `math` 모듈을 불러온 후 `log2` 함수를 통하여 `k`값을 구한다

    ```python
    tri(getN-1, 0, k)
    ```
    - 가장 왼쪽 아래의 좌표인 `(getN-1, 0)`을 이용하여 탐색을 시작한다
    ```python
    tmp = 3 * 2 ** (count-1)
    tri(y - tmp, x + tmp, count-1)
    tri(y, x, count-1)
    tri(y, x + 2 * tmp, count-1)
    ```
    - tmp: 다음 좌표 계산을 위한 변수
    - 위쪽의 삼각형은 현재 좌표 기준 `x`축은 가로 길이의 `1/4`, `y`축은 세로 길이의 `1/2`만큼 이동하게 되며, 해당 좌표를 재귀를 통해 탐색한다
    - 아래 왼쪽의 삼각형은 현재 좌표와 동일하다
    - 아래 오른쪽의 삼각형은 현재 좌표에서 `x`축은 가로 길이의 `1/2`만큼 이동하게 되며, 해당 좌표를 재귀를 통해 탐색한다

    ```python
    if count == 0:
    change_for_star(y, x)
    return
    ```
    - `count`가 `0`이 되면 해당 좌표를 기준으로 삼각형을 그린다
    ```python
    def change_for_star(y, x):
        stage[y-2][x+2] = '*'
        stage[y-1][x+1] = stage[y-1][x+3] = '*'
        stage[y][x] = stage[y][x+1] = stage[y][x+2] = stage[y][x+3] = stage[y][x+4] = '*'
    ```
    - `stage` 배열에 왼쪽 아래 좌표 기준으로 별을 저장한다
## 4. 결과
![image](https://user-images.githubusercontent.com/29600820/89902049-ea86fa00-dc20-11ea-91ce-f44807461156.png)