import sys

n = int(sys.stdin.readline())
for i in range(n):
    line = list(sys.stdin.readline().strip())
    left = list()
    right = list()
    for c in line:
        if c == '<' and left:
            right.append(left.pop())
        elif c == '>' and right:
            left.append(right.pop())
        elif c == '-' and left:
            left.pop()
        elif c not in {'<','>','-'}:
            left.append(c)
    print(f"{''.join(left)}{''.join(right[::-1])}")