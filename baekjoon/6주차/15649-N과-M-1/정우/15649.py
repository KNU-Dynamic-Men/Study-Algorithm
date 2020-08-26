import sys

def loop(arr, res, cnt, m):
    if cnt == m:
        print(res)
        return
    toggles = []
    for i in range(len(arr)):
        if arr[i] == 0:
            toggles.append(i)
    for i in toggles:
        arr[i] = 1
        loop(arr, f"{res} {i+1}", cnt+1, m)
        arr[i] = 0
        

n, m = map(int, sys.stdin.readline().split())
arr = [0] * n

for i in range(n):
    arr[i] = 1
    loop(arr, i+1, 1, m)
    arr[i] = 0