import sys

n = int(sys.stdin.readline())
stack = []
for _ in range(n):
    input = sys.stdin.readline()
    if 'push' in input:
        stack.append(int(input.split()[1]))
    elif 'pop' in input:
        print(stack.pop() if len(stack) > 0 else -1)
    elif 'size' in input:
        print(len(stack))
    elif 'empty' in input:
        print(1 if len(stack) == 0 else 0)
    elif 'top' in input:
        print(stack[-1] if len(stack) != 0 else -1)