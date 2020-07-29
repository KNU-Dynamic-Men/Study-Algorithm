import sys

left = list(sys.stdin.readline().strip())
right = list()
input()
for cmd in sys.stdin:
    if cmd[0] == 'L' and left:
        right.append(left.pop())
    elif cmd[0] == 'D' and right:
        left.append(right.pop())
    elif cmd[0] == 'B' and left:
        left.pop()
    elif cmd[0] == 'P':
        left.append(cmd[2])
print(f"{''.join(left)}{''.join(right[::-1])}")