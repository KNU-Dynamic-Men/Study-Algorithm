import sys

T = int(sys.stdin.readline())
for _ in range(T):
    n = int(sys.stdin.readline())
    arr = list(map(int, sys.stdin.readline().split()))

    dic = dict()
    for i in range(1, n+1):
        dic[i] = arr[i-1]

    looser_cnt = 0
    for i in range(1, n+1):
        x = i
        can_loosers = []
        is_looser = True
        while x in dic.keys():
            tmp = dic.pop(x)
            if tmp == x:
                break
            elif tmp == i:
                is_looser = False
                break
            can_loosers.append(x)
            x = tmp
        can_looser_cnt = 0
        for candidate in can_loosers:
            if candidate == x:
                break
            can_looser_cnt += 1
        if is_looser:
            looser_cnt += can_looser_cnt
    print(looser_cnt)