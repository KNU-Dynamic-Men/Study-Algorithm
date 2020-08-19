import sys

getN = int(sys.stdin.readline())
stack = []
for _ in range(getN):
    input = sys.stdin.readline()
    if 'push' in input:
        stack.append(int(input.split()[1]))
    elif 'pop' in input:
        print(stack.pop(0) if len(stack) > 0 else -1)
    elif 'size' in input:
        print(len(stack))
    elif 'empty' in input:
        print(1 if len(stack) == 0 else 0)
    elif 'front' in input:
        print(stack[0] if len(stack) != 0 else -1)
    elif 'back' in input:
        print(stack[-1] if len(stack) != 0 else -1)
