import sys

def hanoi(getN, source, to, sub):
    if getN == 1:
        print(source, to)
        return
    hanoi(getN-1, source, sub, to)
    print(source, to)
    hanoi(getN-1, sub, to, source)

N = int(sys.stdin.readline())
print(2**N-1)
hanoi(N, 1, 3, 2)