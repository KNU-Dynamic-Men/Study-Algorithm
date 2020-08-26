from sys import stdin

n = int(stdin.readline())
tower = list(map(int, stdin.readline().split(' ')))
ans = []
stack = []
for i in range(n):
    while stack:
        if stack[-1][1] > tower[i]:
            ans.append(stack[-1][0] + 1)
            break
        stack.pop()
    if not stack:
        ans.append(0)
    stack.append((i, tower[i]))
for i in ans:
    print(i, end=' ')