import sys

getN = 1
while True:
    arr = sys.stdin.readline().strip()
    if arr[0] == '-':
        break
    stack = 0
    getCnt = 0
    for i in range(len(arr)):
        if stack == 0 and arr[i] == '}':
            getCnt += 1
            stack += 1
        elif arr[i] == '}':
            stack -= 1
        elif arr[i] == '{':
            stack += 1
    print(f'{getN}. {getCnt+int(stack/2)}')
    getN += 1