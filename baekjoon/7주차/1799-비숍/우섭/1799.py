from sys import stdin
n = int(stdin.readline())
chess = []
for _ in range(n):
    chess.append(list(map(int, stdin.readline().rstrip().split(' '))))
croleft = [False]*(2*n-1)
croright = [False]*(2*n-1)
ans = [0, 0]

def find(x, y, k, color):
    ans[color] = max(k, ans[color])
    if y >= n:
        if y % 2 == 0:
            y = 1
        else:
            y = 0
        x += 1
    
    if x >= n:
        return

    if chess[x][y] == 1 and not croleft[x+y] and not croright[x-y+n-1]:
        croleft[x+y] = croright[x-y+n-1] = True
        find(x, y+2, k+1, color)
        croleft[x+y] = croright[x-y+n-1] = False
    
    find(x, y+2, k, color)

find(0, 0, 0, 0)
find(0, 1, 0, 1)
print(sum(ans))