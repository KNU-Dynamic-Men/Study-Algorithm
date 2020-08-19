import sys

getN = int(sys.stdin.readline().strip())
arr = []
for _ in range(getN):
    line = sys.stdin.readline().strip().split(' ')
    if line[0] == 'push':
        arr.append(line[1])
    elif line[0] == 'pop':
        if len(arr) > 0:
            print(arr.pop(0))
        else:
            print(-1)
    elif line[0] == 'size':
        print(len(arr))
    elif line[0] == 'empty':
        print(int(not bool(arr)))
    elif line[0] == 'front':
        if len(arr) > 0:
            print(arr[0])
        else:
            print(-1)
    elif line[0] == 'back':
        if len(arr) > 0:
            print(arr[-1])
        else:
            print(-1)