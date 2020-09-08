def print_arr(arr):
    for a in arr:
        print(a)
    print()

def rotate_key(key):
    ret = [[0]*len(key) for _ in range(len(key))]
    for i in range(len(key)):
        for j in range(len(key)):
            ret[j][len(key)-i-1] = key[i][j]
    return ret

def try_to_match_key_into_lock(key, lock, zeros, x, y):
    correct_cnt = 0
    for i in range(x, x+len(key)):
        for j in range(y, y+len(key)):
            if lock[i][j]==1 and key[i-x][j-y]==1:
                return False
            if lock[i][j] == 0 and key[i-x][j-y] == 1:
                correct_cnt += 1
    if correct_cnt == zeros:
        return True
    else:
        return False

def solution(key, lock):
    zeros = 0
    big_lock = [[2]*len(lock)*3 for _ in range(len(lock)*3)]
    for i in range(len(lock), len(lock)*2):
        for j in range(len(lock), len(lock)*2):
            big_lock[i][j] = lock[i-(len(lock))][j-(len(lock))]
            if big_lock[i][j] == 0:
                zeros += 1

    for _ in range(4):
        for i in range(len(lock)-len(key)+1, len(lock)*2):
            for j in range(len(lock)-len(key)+1, len(lock)*2):
                if try_to_match_key_into_lock(key, big_lock, zeros, i, j):
                    return True
        key = rotate_key(key)
    return False
    
print(solution(
    [[0, 0, 0], [1, 0, 0], [0, 1, 1]],  # key
    [[1, 1, 1], [1, 1, 0], [1, 0, 1]]   # lock
))                                      # result: True