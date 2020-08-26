import sys

for _ in range(int(sys.stdin.readline())):
    n = int(sys.stdin.readline())
    a1 = list(map(int, sys.stdin.readline().split()))
    a2 = list(map(int, sys.stdin.readline().split()))
    dp = [[0]*n for _ in range(3)]
    dp[0][0] = a1[0]
    dp[1][0] = a2[0]
    for i in range(1, n):
        dp[0][i] = max(dp[1][i-1], dp[2][i-1])+a1[i]
        dp[1][i] = max(dp[0][i-1], dp[2][i-1])+a2[i]
        dp[2][i] = max(dp[0][i-1], dp[1][i-1])
    print(max([dp[0][n-1], dp[1][n-1], dp[2][n-1]]))