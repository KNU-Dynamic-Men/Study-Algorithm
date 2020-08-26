from sys import stdin
t = int(stdin.readline())
for _ in range(t):
    n = int(stdin.readline())
    sticker = [list(map(int, stdin.readline().split(' '))) for _ in range(2)]
    dp = [[0] * n for _ in range(2)]
    # 초기값
    dp[0][0] = sticker[0][0]
    dp[1][0] = sticker[1][0]
    dp[0][1] = sticker[0][1] + dp[1][0]
    dp[1][1] = sticker[1][1] + dp[0][0]

    for i in range(2, n):
        dp[0][i] = sticker[0][i] + max(dp[0][i - 2], dp[1][i - 2], dp[1][i - 1])
        dp[1][i] = sticker[1][i] + max(dp[1][i - 2], dp[0][i - 2], dp[0][i - 1])

    print(max(dp[0][n-1], dp[1][n-1]))