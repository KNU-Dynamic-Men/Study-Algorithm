import sys

sys.stdin.readline()
arr = list(map(int, sys.stdin.readline().split()))
ret = [0 for _ in range(len(arr))]
stack = [(len(arr)-1, arr[-1]),] # [(idx, value)]
for i in range(len(arr)-2, -1, -1):
    idx, value = stack[-1]
    while value < arr[i]:
        ret[idx] = i+1
        stack.pop()
        if stack:
            idx, value = stack[-1]
        else:
            break
    stack.append((i, arr[i]))
ret = list(map(str, ret))
print(' '.join(ret))
