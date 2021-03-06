from math import log2
n = int(input())
k = int(log2(n / 3))
stage = [[' '] * n * 2 for _ in range(n)]

def change_for_star(y, x):
    stage[y-2][x+2] = '*'
    stage[y-1][x+1] = stage[y-1][x+3] = '*'
    stage[y][x] = stage[y][x+1] = stage[y][x+2] = stage[y][x+3] = stage[y][x+4] = '*'


def tri(y, x, count):
    if count == 0:
        change_for_star(y, x)
        return

    tmp = 3 * 2 ** (count-1)
    tri(y - tmp, x + tmp, count-1)
    tri(y, x, count-1)
    tri(y, x + 2 * tmp, count-1)

tri(n-1, 0, k)
for i in stage:
    print(''.join(i))