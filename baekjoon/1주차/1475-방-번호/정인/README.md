# 1475 - 방 번호

## 01 개요
- 문제: [boj/1475 방 번호](https://www.acmicpc.net/problem/1475)
- 구현: Python3

## 02 코드
```python
import sys
import math
room_no = sys.stdin.readline().strip().replace("9", "6")

number_dist_list = {}
for num in range(9):
    number_dist_list[str(num)] = 0

for num in room_no:
    number_dist_list[num] += 1
number_dist_list['6'] = math.ceil(number_dist_list['6'] / 2)

max_counts = 0
for num in range(9):
    max_counts = max(max_counts, number_dist_list[str(num)])

print(max_counts)
```

## 03 여정
- 최소로 필요한 번호 플라스틱(1~9)의 갯수를 구하는 문제이다.
- 6은 뒤집어서 9로 사용 가능하다는 말이 있다 -> 서로 나누어 쓰면 되므로 **둘이 합쳐서 2로 나누면 되겠다!**
- 첫번째 시도
  - [1406번 문제의 여정](https://github.com/KNU-Dynamic-Men/Study-Algorithm/blob/master/baekjoon/1406-%EC%97%90%EB%94%94%ED%84%B0/%EC%A0%95%EC%9A%B0/1406_stack_ver.py) 봤던 문제를 토대로 sys.stdin을 쓰기로 했다.
  - 읽는 과정에서 9를 6으로 바꾸고, 추후 코드에서는 9가 아닌 8까지만 사용하기로 한다 (`range`라던가, `number_dist_list`라던가.)
  - 6에 해당하는 `number_dist_list`를 2로 나누어 **올림**한다 (2.5묶음을 살수는 없으니까).

## 04 결과
![image](https://user-images.githubusercontent.com/5201073/86909777-938a9280-c153-11ea-954c-565d22484900.png)
