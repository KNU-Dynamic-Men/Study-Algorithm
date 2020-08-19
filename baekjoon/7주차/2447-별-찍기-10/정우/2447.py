import sys

def loop(y, x, n):
    if n == 1:
        arr[y][x] = '*'
        return
    d = n//3
    for i in range(3):
        for j in range(3):
            if not i==j==1:
                loop(i*d+y, j*d+x, d)

N = int(sys.stdin.readline())
arr = [[' ' for _ in range(N)] for _ in range(N)]
loop(0, 0, N)
for i in range(N):
    print(''.join(arr[i]))