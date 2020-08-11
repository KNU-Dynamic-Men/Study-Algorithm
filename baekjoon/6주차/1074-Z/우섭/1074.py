from sys import stdin
n, r, c = map(int, stdin.readline().split(' '))

# k = 시작점 값
# t = 변의 길이
def findz(x, y, k, t):
    if t == 1:
        return k
    
    half = t//2
    if x < half and y < half:
        return findz(x, y, k, half)
    elif x < half and y >= half:
        return findz(x, y - half, k + (half)**2, half)
    elif x >= t/2 and y < half:
        return findz(x - half, y, k + ((half)**2)*2, half)
    else:
        return findz(x - half, y - half, k + ((half)**2)*3, half)

print(findz(r, c, 0, 2**n))