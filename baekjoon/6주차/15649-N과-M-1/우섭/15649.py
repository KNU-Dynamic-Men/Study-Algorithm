from sys import stdin
from collections import deque
n, m = map(int, stdin.readline().split())
check = [False for _ in range(n+1)]
arr = deque()

def find(count):
    if count == m:
        print(' '.join(map(str, arr)))
        return
    for i in range(1, n+1):
        if check[i] == False:
            check[i] = True
            arr.append(i)
            find(count + 1)
            check[i] = False
            arr.pop()
find(0)