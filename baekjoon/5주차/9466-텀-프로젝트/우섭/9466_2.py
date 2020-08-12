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
    getN = int(sys.stdin.readline())
    ls = list(getMap(int, sys.stdin.readline().split(' ')))
    visit = [False for _ in range(getN)]
    end = [False for _ in range(getN)]
    cycle = [False for _ in range(getN)]
    for i in range(getN):
        if not visit[i]:
            dfs(i)
    print(cycle.count(False))