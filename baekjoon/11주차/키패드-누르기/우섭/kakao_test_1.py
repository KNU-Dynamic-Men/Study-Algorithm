def change(number):
    if number == 1:
        return [0, 0]
    elif number == 2:
        return [0, 1]
    elif number == 3:
        return [0, 2]
    elif number == 4:
        return [1, 0]
    elif number == 5:
        return [1, 1]
    elif number == 6:
        return [1, 2]
    elif number == 7:
        return [2, 0]
    elif number == 8:
        return [2, 1]
    elif number == 9:
        return [2, 2]
    elif number == 0:
        return [3, 1]

def leng(now, next):
    return abs(now[0] - next[0]) + abs(now[1] - next[1])

def check(now_left, now_right, next, hand):
    if next in [1, 4, 7]:
        return "left"
    elif next in [3, 6, 9]:
        return "right"
    next_pad = change(next)
    left_len = leng(now_left, next_pad)
    right_len = leng(now_right, next_pad)
    if left_len < right_len:
        return "left"
    elif left_len > right_len:
        return "right"
    else:
        return hand

def solution(numbers, hand):
    left = [3, 0]
    right = [3, 2]
    answer = ''

    for i in numbers:
        a = check(left, right, i, hand)
        if a == "left":
            left = change(i)
            answer += 'L'
        else:
            right = change(i)
            answer += 'R'
    return answer

if __name__ == "__main__":
    print(solution([1, 3, 4, 5, 8, 2, 1, 4, 5, 9, 5], "right"))