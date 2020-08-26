import sys, itertools

N, S = map(int, sys.stdin.readline().split())
arr = list(map(int, sys.stdin.readline().split()))
cnt = 0
for i in range(1,N+1):
    for combi in itertools.combinations(arr,i):
        cnt = cnt+1 if sum(combi) == S else cnt
print(cnt)