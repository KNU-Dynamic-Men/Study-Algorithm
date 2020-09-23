from collections import deque

def check(N, y, x, board):
    return 0 <= x < N and 0 <= y < N and board[y][x] == 0

def solution(board):
    N = len(board)
    direction = [[1, 0, 'DOWN'], [0, 1, 'RIGHT'], [-1, 0, 'UP'], [0, -1, 'LEFT']]
    confirm = {(0, 0): 0}
    route = deque()

    route.append([0, 0, 'RIGHT', 0])
    confirm[(0, 1)] = 100
    route.append([0, 0, 'DOWN', 0])
    confirm[(1, 0)] = 100
    answer = N * N * 500

    while route:
        tmp = route.popleft()
        if tmp[0] == N - 1 and tmp[1] == N - 1:
            answer = min(tmp[3], answer)
            continue
        for i in direction:
            dy = i[0] + tmp[0]
            dx = i[1] + tmp[1]
            if check(N, dy, dx, board):
                now = tmp[3]
                if tmp[2] == i[2]:
                    now += 100
                else:
                    now += 600
                arr = (dy, dx)
                if arr in confirm and confirm[arr] < now:
                    continue
                route.append([dy, dx, i[2], now])
                confirm[arr] = now

    return answer


if __name__ == "__main__":
    print(solution([[0, 0, 0, 0, 0, 0, 0, 1],
                    [0, 0, 0, 0, 0, 0, 0, 0],
                    [0, 0, 0, 0, 0, 1, 0, 0],
                    [0, 0, 0, 0, 1, 0, 0, 0],
                    [0, 0, 0, 1, 0, 0, 0, 1],
                    [0, 0, 1, 0, 0, 0, 1, 0],
                    [0, 1, 0, 0, 0, 1, 0, 0],
                    [1, 0, 0, 0, 0, 0, 0, 0]]))
