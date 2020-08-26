n = int(input())
img = [[' ']*n for _ in range(n)]

def square(y, x, k):
    if k == 1:
        img[y][x] = '*'
        return

    tmp = k // 3
    square(y, x, tmp)
    square(y, x+tmp, tmp)
    square(y, x+tmp*2, tmp)
    square(y+tmp, x, tmp)
    square(y+tmp, x+tmp*2, tmp)
    square(y+tmp*2, x, tmp)
    square(y+tmp*2, x+tmp, tmp)
    square(y+tmp*2, x+tmp*2, tmp)

square(0, 0, n)
for i in img:
    print(''.join(i))