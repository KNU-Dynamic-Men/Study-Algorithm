# **2020 카카오 인턴십 1번 - 키패드 누르기**

## **1. 개요**

[https://programmers.co.kr/learn/courses/30/lessons/67256](https://programmers.co.kr/learn/courses/30/lessons/67256)

## **2. 코드**

Python3

```python
pos_map = {
    1:(0,0),
    2:(0,1),
    3:(0,2),
    4:(1,0),
    5:(1,1),
    6:(1,2),
    7:(2,0),
    8:(2,1),
    9:(2,2),
    0:(3,1),
}

def get_distance(x1, y1, x2, y2):
    return abs(x1-x2)+abs(y1-y2)

def solution(numbers, hand):
    answer = ''
    left_hand_pos, right_hand_pos = (3,0), (3,2)
    for num in numbers:
        if num in (1, 4, 7):
            left_hand_pos = pos_map[num]
            answer += 'L'
        elif num in (3, 6, 9):
            right_hand_pos = pos_map[num]
            answer += 'R'
        else:
            left_dist = get_distance(*left_hand_pos, *pos_map[num])
            right_dist = get_distance(*right_hand_pos, *pos_map[num])
            if left_dist > right_dist:
                right_hand_pos = pos_map[num]
                answer += 'R'
            elif left_dist < right_dist:
                left_hand_pos = pos_map[num]
                answer += 'L'
            else:
                if hand == 'left':
                    left_hand_pos = pos_map[num]
                    answer += 'L'
                else:
                    right_hand_pos = pos_map[num]
                    answer += 'R'
    return answer
```

## **3. 설명**

1. 키패드 숫자들을 2차 배열로 매핑한다. (`pos_map`)
2. 키패드를 누를 때마다 손의 위치를 저장한다. (x,y) 형식으로.
3. 2, 5, 8, 0 이 나오면 어느 손이 가까운지 판단하고 (`get_distancse()` 사용), 거리가 같을 때는 `hand`를 보고 어느 손을 쓸 지 결정한다.
