from sys import stdin

n = int(stdin.readline())
a = 0
tower = []
stack = []
for _ in range(n):
    tower.append(int(stdin.readline()))
for i in range(n):
    while(stack):
        if stack[-1] <= tower[i]:
            stack.pop()
        else:
            break
    a += len(stack)
    stack.append(tower[i])
print(a)

