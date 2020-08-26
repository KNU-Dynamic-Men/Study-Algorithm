from sys import stdin
from collections import deque
n, k = map(int, stdin.readline().split())
arr = [list(map(int, stdin.readline().split())) for _ in range(n)]
a = deque()
b = list(map(int, stdin.readline().split()))
c = list(map(int, stdin.readline().split()))
check = [False for _ in range(n+1)]
find = False

def check_win(fir, las):
    return arr[fir-1][las-1]

def versus_ab(a_val, b_val):
    if check_win(a_val, b_val) == 2:
        return True
    else:
        return False

def versus_ac(a_val, c_val):
    if check_win(a_val, c_val) == 2:
        return True
    else:
        return False

def versus_bc(b_val, c_val):
    if check_win(b_val, c_val) == 2:
        return True
    else:
        return False

def play_game(idx_a, idx_b, idx_c, ver_a, ver_b, ver_c, win_count):
    if win_count[0] >= k:
        return True
    if win_count[1] >= k or win_count[2] >= k:
        return False
    if idx_a >= n:
        return False

    if ver_a and ver_b:
        if versus_ab(a[idx_a], b[idx_b]):
            win_count[0] += 1
            return play_game(idx_a + 1, idx_b + 1, idx_c, True, False, True, win_count)
        else:
            win_count[1] += 1
            return play_game(idx_a + 1, idx_b + 1, idx_c, False, True, True, win_count)

    elif ver_a and ver_c:
        if versus_ac(a[idx_a], c[idx_c]):
            win_count[0] += 1
            return play_game(idx_a + 1, idx_b, idx_c + 1, True, True, False, win_count)
        else:
            win_count[2] += 1
            return play_game(idx_a + 1, idx_b, idx_c + 1, False, True, True, win_count)

    elif ver_b and ver_c:
        if versus_bc(b[idx_b], c[idx_c]):
            win_count[1] += 1
            return play_game(idx_a, idx_b + 1, idx_c + 1, True, True, False, win_count)
        else:
            win_count[2] += 1
            return play_game(idx_a, idx_b + 1, idx_c + 1, True, False, True, win_count)

def find_a(cnt):
    global find
    if find:
        return
    if cnt == n:
        find = play_game(0, 0, 0, True, True, False, [0, 0, 0])
        if find:
            return
    for i in range(1, n+1):
        if find:
            return
        if not check[i]:
            check[i] = True
            a.append(i)
            find_a(cnt + 1)
            check[i] = False
            a.pop()

find_a(0)
if find:
    print(1)
else:
    print(0)