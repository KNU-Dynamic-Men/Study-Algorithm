import sys

getN = int(sys.stdin.readline())
for i in range(getN):
    line = list(sys.stdin.readline().strip())
    left = list()
    right = list()
    for getC in line:
        if getC == '<' and left:
            right.append(left.pop())
        elif getC == '>' and right:
            left.append(right.pop())
        elif getC == '-' and left:
            left.pop()
        elif getC not in {'<','>','-'}:
            left.append(getC)
    print(f"{''.join(left)}{''.join(right[::-1])}")