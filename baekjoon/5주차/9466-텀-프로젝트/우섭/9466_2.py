import sys
sys.setrecursionlimit(150000)
from collections import deque

def dfs(idx):
    visit[idx] = True
    next = ls[idx]-1
    if not visit[next]:
        dfs(next)
    else:
        if not end[next]:
            a = next
            while(True):
                cycle[a] = True
                if a == idx: break
                a = ls[a]-1
    end[idx] = True

t = int(sys.stdin.readline())
for _ in range(t):
    n = int(sys.stdin.readline())
    ls = list(map(int, sys.stdin.readline().split(' ')))
    visit = [False for _ in range(n)]
    end = [False for _ in range(n)]
    cycle = [False for _ in range(n)]
    for i in range(n):
        if not visit[i]:
            dfs(i)
    print(cycle.count(False))