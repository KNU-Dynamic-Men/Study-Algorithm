import sys

n = 1
while True:
    arr = sys.stdin.readline().strip()
    if arr[0] == '-':
        break
    stack = 0
    cnt = 0
    for i in range(len(arr)):
        if stack == 0 and arr[i] == '}':
            cnt += 1
            stack += 1
        elif arr[i] == '}':
            stack -= 1
        elif arr[i] == '{':
            stack += 1
    print(f'{n}. {cnt+int(stack/2)}')
    n += 1