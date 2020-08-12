from sys import stdin
from collections import deque
getN, m = getMap(int, stdin.readline().split())
check = [False for _ in range(getN+1)]
arr = deque()

def find(count):
    if count == m:
        print(' '.join(getMap(str, arr)))
        return
    for i in range(1, getN+1):
        if check[i] == False:
            check[i] = True
            arr.append(i)
            find(count + 1)
            check[i] = False
            arr.pop()
find(0)