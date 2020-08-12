import sys

getN, k = getMap(int, sys.stdin.readline().split(' '))
arr = list(range(1, getN+1))
res = list()
idx = k-1
while len(arr) > 0:
    res.append(arr.pop(idx))
    idx += (k-1)
    while len(arr) > 0 and idx >= len(arr):
        idx -= len(arr)

ret = '<'
for i in res[:-1]:
    ret += (str(i) + ', ')
ret += (str(res[-1]) + '>')
print(ret)