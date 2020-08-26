# 2493 - 탑

## 01 개요
- 문제: [boj/2493 탑](https://www.acmicpc.net/problem/2493)
- 구현: Python3

## 02 코드
```python
import sys

sys.stdin.readline()
tower_input = list(enumerate(map(int, sys.stdin.readline().split())))[::-1]

lazer_reach_table = [0] * len(tower_input)
tower_list = [tower_input.pop(0)]

for tower_item in tower_input:
    index, next_tower_element = tower_item
    while tower_list and tower_list[-1][1] < next_tower_element:
        lazer_reach_table[tower_list[-1][0]] = index + 1
        tower_list.pop()
    tower_list.append(tower_item)

print(' '.join(list(map(str, lazer_reach_table))))
```

## 03 여정
- Stack을 이용한 풀이법 활용
- **for문을 Forward로 진행하면 출력 초과라고 나옴** - Why?
- 탑 문제 풀이 참고: [마이구미](https://mygumi.tistory.com/101)

## 04 결과

![image](https://user-images.githubusercontent.com/5201073/88024743-2a9d1480-cb6e-11ea-98af-99d1b32fc7ff.png)
