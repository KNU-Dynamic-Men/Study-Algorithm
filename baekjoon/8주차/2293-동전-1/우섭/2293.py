from sys import stdin
n, k = map(int, stdin.readline().split())
coin = []
for _ in range(n):
    coin.append(int(stdin.readline()))
dp = [0 for _ in range(k+1)]
dp[0] = 1
for i in coin:
    for j in range(i, k+1):
        dp[j] += dp[j - i]
print(f"{dp[k]}")