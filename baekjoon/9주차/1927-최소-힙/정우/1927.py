import sys

def add(arr, cnt):
    i = cnt
    while i != 1 and arr[i//2] > arr[i]:
        arr[i//2], arr[i] = arr[i], arr[i//2]
        i = i//2

def minus(arr, cnt):
    ret, arr[1] = arr[1], arr[cnt+1]
    i = 1
    while i*2 < cnt+1:
        if i*2 < cnt:
            if arr[i*2] <= arr[i*2+1] and arr[i*2] < arr[i]:
                arr[i], arr[i*2] = arr[i*2], arr[i]
                i = i*2
            elif arr[i*2+1] < arr[i]:
                arr[i], arr[i*2+1] = arr[i*2+1], arr[i]
                i = i*2+1
            else:
                break
        else:
            if arr[i*2] < arr[i]:
                arr[i*2], arr[i] = arr[i], arr[i*2]
                i = i*2
            else:
                break
    return ret

n = int(sys.stdin.readline())
arr = [0] * (n+1)
res = []
cnt = 0
for _ in range(n):
    x = int(sys.stdin.readline())
    if x == 0:
        if cnt == 0:
            res.append(0)
        else:
            cnt -= 1
            res.append(minus(arr, cnt))
    else:
        cnt += 1
        arr[cnt] = x
        add(arr, cnt)
print('\n'.join(map(str, res)))