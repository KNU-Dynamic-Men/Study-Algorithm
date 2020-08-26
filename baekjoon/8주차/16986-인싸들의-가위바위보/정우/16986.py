import sys
n, k = map(int, sys.stdin.readline().split())
arr = [list(map(int, sys.stdin.readline().split())) for _ in range(n)]
a = []
b = list(map(int, sys.stdin.readline().split()))
c = list(map(int, sys.stdin.readline().split()))
check = [False for _ in range(n+1)]

def versus(x, y):
    return True if arr[x-1][y-1] == 2 else False

def play_game(idx_a, idx_b, idx_c, ver_a, ver_b, ver_c, win_count):
    if win_count[0] >= k:
        return True
    if win_count[1] >= k or win_count[2] >= k:
        return False
    if idx_a >= n:
        return False

    if ver_a and ver_b:
        if versus(a[idx_a], b[idx_b]):
            win_count[0] += 1
            return play_game(idx_a + 1, idx_b + 1, idx_c, True, False, True, win_count)
        else:
            win_count[1] += 1
            return play_game(idx_a + 1, idx_b + 1, idx_c, False, True, True, win_count)

    elif ver_a and ver_c:
        if versus(a[idx_a], c[idx_c]):
            win_count[0] += 1
            return play_game(idx_a + 1, idx_b, idx_c + 1, True, True, False, win_count)
        else:
            win_count[2] += 1
            return play_game(idx_a + 1, idx_b, idx_c + 1, False, True, True, win_count)

    elif ver_b and ver_c:
        if versus(b[idx_b], c[idx_c]):
            win_count[1] += 1
            return play_game(idx_a, idx_b + 1, idx_c + 1, True, True, False, win_count)
        else:
            win_count[2] += 1
            return play_game(idx_a, idx_b + 1, idx_c + 1, True, False, True, win_count)

def find_a(cnt):
    find = False
    if cnt == n:
        find = play_game(0, 0, 0, True, True, False, [0, 0, 0])
    if not find:
        for i in range(1, n+1):
            if find:
                break
            if not check[i]:
                check[i] = True
                a.append(i)
                find = find_a(cnt + 1) or find
                check[i] = False
                a.pop()
    return find

print(int(find_a(0)))