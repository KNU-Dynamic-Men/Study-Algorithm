from sys import stdin
from collections import deque

t = int(stdin.readline())
for _ in range(t):
    getN = int(stdin.readline())
    ls = list(getMap(int, stdin.readline().split(' ')))
    count = 0
    for i in range(getN):
        a = i
        find = False
        if ls[a]-1 == a:
            find = True
        while(not find):
            tmp = ls[a] - 1
            if tmp == i:
                find = True
                break
            if tmp == a:
                break
            a = tmp
        if not find:
            count += 1
    print(count)
