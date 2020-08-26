import sys

def hanoi(n, source, to, sub):
    if n == 1:
        print(source, to)
        return
    hanoi(n-1, source, sub, to)
    print(source, to)
    hanoi(n-1, sub, to, source)

N = int(sys.stdin.readline())
print(2**N-1)
hanoi(N, 1, 3, 2)