import sys, math

# N = sys.stdin.readline() # 왜 런타임 에러?..
N = input()
arr = [0,]*10
for i in N:
    arr[int(i)] += 1
arr[6] = math.ceil((arr[6]+arr[9])/2)
print(max(arr[0:9]))