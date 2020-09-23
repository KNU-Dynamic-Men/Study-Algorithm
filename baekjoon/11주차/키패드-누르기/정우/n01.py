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
    
print(solution(
    [1, 3, 4, 5, 8, 2, 1, 4, 5, 9, 5], # numbers
    "right" # hand
)) # result: "LRLLLRLLRRL"

print(solution(
    [7, 0, 8, 2, 8, 3, 1, 5, 7, 6, 2], # numbers
    "left" # hand
)) # result: "LRLLRRLLLRR"

print(solution(
    [1, 2, 3, 4, 5, 6, 7, 8, 9, 0], # numbers
    "right" # hand
)) # result: "LLRLLRLLRL"