from sys import stdin
n = int(stdin.readline())
ans = 0
hor = [False]*n
croleft = [False]*(2*n-1)
croright = [False]*(2*n-1)

def find(t):
    if t == n:
        global ans
        ans += 1
        return
    for i in range(n):
        if hor[i] or croleft[i + t] or croright[t - i + n - 1]: continue
        hor[i] = croleft[i + t] = croright[t - i + n - 1] = True
        find(t+1)
        hor[i] = croleft[i + t] = croright[t - i + n - 1] = False

find(0)
print(ans)
