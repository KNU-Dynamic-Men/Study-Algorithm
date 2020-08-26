# 2447 - 별 찍기 - 10

## 1. 개요

https://www.acmicpc.net/problem/2447

## 2. 코드

```python
n = int(input())
img = [[' ']*n for _ in range(n)]

def square(y, x, k):
    if k == 1:
        img[y][x] = '*'
        return

    tmp = k // 3
    square(y, x, tmp)
    square(y, x+tmp, tmp)
    square(y, x+tmp*2, tmp)
    square(y+tmp, x, tmp)
    square(y+tmp, x+tmp*2, tmp)
    square(y+tmp*2, x, tmp)
    square(y+tmp*2, x+tmp, tmp)
    square(y+tmp*2, x+tmp*2, tmp)

square(0, 0, n)
for i in img:
    print(''.join(i))
```

## 3. 풀이

1. 구현 방법 구상

    - [별 찍기 - 11](https://www.acmicpc.net/problem/2448)와 거의 동일하게 풀이
    - [이전에 풀었던 별 찍기 - 11의 코드](https://github.com/KNU-Dynamic-Men/Study-Algorithm/tree/master/baekjoon/6%EC%A3%BC%EC%B0%A8/2448-%EB%B3%84-%EC%B0%8D%EA%B8%B0-11/%EC%9A%B0%EC%84%AD)와 거의 동일하게 진행하였다
    - 배열 생성 > 재귀를 통해 임계점 도달 > 임계점에서 별 찍기

2. 함수 구현

    ```python
    def square(y, x, k):
    ```
    - 필요한 인자는 좌표 값을 나타내는 `y`, `x`, 변의 길이인 `k`
    ```python
    if k == 1:
        img[y][x] = '*'
        return
    ```
    - 임계점을 k == 1로 설정하였으며, 해당 조건을 만족하면 해당 좌표에 *을 그린 후 return한다
    ```python
    tmp = k // 3
    square(y, x, tmp)
    square(y, x+tmp, tmp)
    square(y, x+tmp*2, tmp)
    square(y+tmp, x, tmp)
    square(y+tmp, x+tmp*2, tmp)
    square(y+tmp*2, x, tmp)
    square(y+tmp*2, x+tmp, tmp)
    square(y+tmp*2, x+tmp*2, tmp)
    ```
    - 기준 좌표는 왼쪽 위이며, `*`을 그리는 좌표는 총 8곳이 계산된다
    - 상단 왼쪽, 상단 중간, 상단 오른쪽, 중단 왼쪽, 중단 오른쪽, 하단 왼쪽, 하단 중간, 하단 오른쪽의 좌표를 `tmp`를 이용하여 계산한다
    ```python
    for i in img:
        print(''.join(i))
    ```
    - 모든 계산이 끝나면 출력한다
## 4. 결과

![image](https://user-images.githubusercontent.com/29600820/90480461-3aa71480-e16b-11ea-84c5-7220cb9ba9ca.png)