from sys import stdin
getN = int(stdin.readline())
ans = 0
hor = [False]*getN
croleft = [False]*(2*getN-1)
croright = [False]*(2*getN-1)

def find(t):
    if t == getN:
        global ans
        ans += 1
        return
    for i in range(getN):
        if hor[i] or croleft[i + t] or croright[t - i + getN - 1]: continue
        hor[i] = croleft[i + t] = croright[t - i + getN - 1] = True
        find(t+1)
        hor[i] = croleft[i + t] = croright[t - i + getN - 1] = False

find(0)
print(ans)
