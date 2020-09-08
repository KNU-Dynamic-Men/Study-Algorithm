import copy

def rotate(arr):
    new_key = [[0]*len(arr) for _ in range(len(arr))]
    for i in range(len(arr)):
        for j in range(len(arr)):
            new_key[i][j] = arr[len(arr) - 1 - j][i]
    return new_key

def check(key, lock, a, b, start, end):
    new_lock = copy.deepcopy(lock)
    for i in range(len(key)):
        for j in range(len(key)):
            if key[i][j] and new_lock[i + a][j + b]:
                return False
            if key[i][j] and not new_lock[i + a][j + b]:
                new_lock[i + a][j + b] = 1
                continue
    for i in range(start, end):
        for j in range(start, end):
            if not new_lock[i][j]:
                return False
    return True

def solution(key, lock):
    new_len = ((len(key)-1) * 2) + len(lock)
    new_lock = [[0] * new_len for _ in range(new_len)]
    for i in range(len(lock)):
        for j in range(len(lock)):
            new_lock[i + (len(key) - 1)][j + (len(key) - 1)] = lock[i][j]
    start = len(key) - 1
    end = start + len(lock)
    for _ in range(4):
        for i in range(0, end):
            for j in range(0, end):
                if check(key, new_lock, i, j, start, end):
                    return True
        key = rotate(key)
    return False

if __name__ == "__main__":
    key = [[0, 0, 0], [1, 0, 0], [0, 1, 1]]
    lock = [[1, 1, 1], [1, 1, 0], [1, 0, 1]]
    print(solution(key, lock))