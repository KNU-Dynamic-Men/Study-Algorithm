import sys

N = int(sys.stdin.readline())
arr = [list(map(int, sys.stdin.readline().split())) for _ in range(N)]
left = [False for _ in range(2*N)]
right = [False for _ in range(2*N)]
x = [False for _ in range(2*N)]
y = [False for _ in range(2*N)]

for i in range(N):
    for j in range(N):
        if arr[i][j] == 1:
            left[i+(N-j-1)] = True
            right[i+j] = True
            if (i+j)%2==0:
                x[i+j] = True
            else:
                y[i+j] = True

left = list(map(int, left))
right = list(map(int, right))
x = list(map(int, x))
y = list(map(int, y))

print(left)
print(right)
flag = 0
for i, j, k in zip(left,right,range(2*N)):
    if i or j:
        right[k] = 1
        if not i==j==1:
            flag = 1
# print(right)

res = sum(right)
vertical_center = [i[N//2] for i in arr]
horizontal_center = arr[N//2]
print(vertical_center)
print(horizontal_center)
print(res, flag)

if N==1:
    print(res)
else:
    if N%2==0:
        print(res-1)
    else:
        print(res-min(sum(vertical_center), sum(horizontal_center)))
