from sys import stdin

getN = int(stdin.readline())
tower = list(getMap(int, stdin.readline().split(' ')))
ans = []
while(tower):
    t = tower.pop()
    idx = len(tower)
    is_find = False
    for i in reversed(tower):
        if i >= t:
            ans.append(idx)
            is_find = not is_find
            break
        idx -= 1
    if not is_find:
        ans.append(0)

for i in ans:
    print(i, end=' ')