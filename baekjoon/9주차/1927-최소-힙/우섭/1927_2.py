from sys import stdin
import heapq

n = int(stdin.readline())
h = []
for _ in range(n):
    input = int(stdin.readline())
    if input == 0:
        if len(h) == 0:
            print(0)
        else:
            print(heapq.heappop(h))
    else:
        heapq.heappush(h, input)