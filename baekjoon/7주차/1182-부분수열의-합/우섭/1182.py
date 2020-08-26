from sys import stdin
from collections import deque
n, s = map(int, stdin.readline().split())
t = list(map(int, stdin.readline().split(' ')))
check = [False for _ in range(n)]
arr = deque()
ans = 0

def find(index):
    if sum(arr) == s and len(arr) != 0:
        global ans
        ans += 1
    for i in range(index, n):
        if check[i] == False:
            check[i] = True
            arr.append(t[i])
            find(i)
            check[i] = False
            arr.pop()
find(0)
print(ans)