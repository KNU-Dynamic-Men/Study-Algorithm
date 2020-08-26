import sys

N = int(sys.stdin.readline())
buildings = []
for _ in range(N):
    buildings.append(int(sys.stdin.readline()))
stack = [(buildings[-1], 0)]
cnt = 0
for i in range(N-2, -1, -1):
    t = s = 0
    while stack and stack[-1][0] < buildings[i]:
        s += stack.pop()[1]
        t += 1
    stack.append((buildings[i], t+s))
    cnt += t+s
print(cnt)