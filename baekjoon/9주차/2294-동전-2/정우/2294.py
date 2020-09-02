import sys

n, k = map(int, sys.stdin.readline().split())
arr = []
dp = [10001] * (k+1)
for _ in range(n):
    i = int(sys.stdin.readline())
    if i <= k:
        dp[i] = 1
    arr.append(i)
arr = list(set(arr))
res = []

for i in range(1, k+1):
    for a in arr:
        if i-a >= 0:
            dp[i] = min(dp[i-a]+1, dp[i])
if dp[-1] == 10001:
    print(-1)
else:
    print(dp[-1])