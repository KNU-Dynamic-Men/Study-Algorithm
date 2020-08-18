from sys import stdin
# a ^ b % m
def pow(a, b, m):
    if b == 1:
        return a % m
    val = pow(a, b//2, m)
    val = val * val % m
    if (b % 2 == 0):
        return val
    return val * a % m

a, b, m = map(int, stdin.readline().split(' '))
print(pow(a, b, m))