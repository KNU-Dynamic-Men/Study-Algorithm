def check(building):
    for x, y, floor in building:
        if not floor:
            if y \
                    and [x, y, 1] not in building \
                    and [x - 1, y, 1] not in building \
                    and [x, y - 1, 0] not in building:
                return False
        else:
            if [x, y - 1, 0] not in building \
                    and [x + 1, y - 1, 0] not in building \
                    and not ([x - 1, y, 1] in building and [x + 1, y, 1] in building):
                return False
    return True


def solution(n, build_frame):
    from collections import deque
    answer = deque()
    for x, y, a, b in build_frame:
        value = [x, y, a]
        if b:
            answer.appendleft(value)
            if not check(answer):
                answer.popleft()
        elif value in answer:
            answer.remove(value)
            if not check(answer):
                answer.appendleft(value)
    answer = map(list, answer)
    return sorted(answer, key=lambda x: (x[0], x[1], x[2]))


if __name__ == "__main__":
    print(solution(5, [[0, 0, 0, 1], [2, 0, 0, 1], [4, 0, 0, 1], [0, 1, 1, 1], [1, 1, 1, 1], [2, 1, 1, 1], [3, 1, 1, 1],
                       [2, 0, 0, 0], [1, 1, 1, 0], [2, 2, 0, 1]]))
