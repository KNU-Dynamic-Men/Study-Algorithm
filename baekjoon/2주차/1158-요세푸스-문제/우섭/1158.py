import sys

getN, k = getMap(int, sys.stdin.readline().split())
ans = []
list = [x for x in range(1, getN+1)]
index = k - 1
while len(list) > 0:
    if len(list) > index:
        ans.append(list.pop(index))
        index += k - 1
    else:
        index = index % len(list)
        ans.append(list.pop(index))
        index += k - 1

result = '<'
for i in ans[:-1]:
    result += '{}, '.format(i)
result += '{}>'.format(ans[-1])
print(result)