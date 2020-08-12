# 1074 - Z

## 1. 개요

https://www.acmicpc.net/problem/1074

## 2. 코드
```python
from sys import stdin
getN, getR, getC = getMap(int, stdin.readline().split(' '))

# k = 시작점 값
# t = 변의 길이
def findz(x, y, k, t):
    if t == 1:
        return k
    
    half = t//2
    if x < half and y < half:
        return findz(x, y, k, half)
    elif x < half and y >= half:
        return findz(x, y - half, k + (half)**2, half)
    elif x >= t/2 and y < half:
        return findz(x - half, y, k + ((half)**2)*2, half)
    else:
        return findz(x - half, y - half, k + ((half)**2)*3, half)

print(findz(getR, getC, 0, 2**getN))
```

## 3. 설명

1. 구현 방법 구상

    - [[실전 알고리즘] 0x0B강 - 재귀 - BaaaaaaaarkingDog](https://baaaaaaaaaaaaaaaaaaaaaaarkingdog.tistory.com/943?category=773649)에 이미 풀이가 나와있는 문제
    - 해당 블로그의 풀이를 통해 코드 작성 후 [통과한 풀이](https://github.com/rokuta1059/SolutionPractice/blob/master/work_py/1074.py)가 있지만, 해당 풀이를 생각하지 않고 직접 구상 후 새로 구현하였다

2. 함수 구현
    ```python
    def findz(x, y, k, t)
    ```
    - x: 계산하려는 X 좌표 (세로)
    - y: 계산하려는 Y 좌표 (가로)
    - 해당 문제의 좌표는 왼쪽 위가 (0,0)으로 설정되어 있다
    - (getR, getC)의 경우, 아래쪽으로 r만큼, 오른쪽으로 c만큼 이동 후 해당 좌표의 순서를 출력하게 된다
    - k: 현재 계산하려는 첫 번째 좌표의 방문순서
    - t: 현재 계산중인 변의 크기

    ```python
    if t == 1:
        return k
    ```
    - 계산하려는 변의 크기가 1인 경우 해당 방문 순서를 반환한다

    ```python
    half = t//2
    if x < half and y < half:
        return findz(x, y, k, half)
    elif x < half and y >= half:
        return findz(x, y - half, k + (half)**2, half)
    elif x >= t/2 and y < half:
        return findz(x - half, y, k + ((half)**2)*2, half)
    else:
        return findz(x - half, y - half, k + ((half)**2)*3, half)
    ```
    - 그렇지 않은 경우 변의 크기가 1이 될 때 까지 재귀 호출을 통해 계산한다
    - 몇 번째 사각형에 있는지에 따라 해당 좌표를 계산하여 준 후 재귀 호출을 통하여 해당 좌표의 순서를 계산한다
    - 변의 크기를 반씩 줄여서 재귀 함수를 호출하고 계산하므로 좌표도 해당 변의 크기를 빼주면서 계산한다

## 4. 결과

![image](https://user-images.githubusercontent.com/29600820/89502322-c5991e00-d7ff-11ea-9332-926fdfa5a364.png)
