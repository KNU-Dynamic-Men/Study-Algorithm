from sys import stdin
n, k = map(int, stdin.readline().split())
dp = [20000 for _ in range(k+1)]
coin = []
dp[0] = 0
for _ in range(n):
    coin.append(int(stdin.readline()))
for i in range(1, k+1):
    for j in coin:
        if i - j >= 0:
            dp[i] = min(dp[i], dp[i - j] + 1)
print(dp[k] if dp[k] != 20000 else -1)
