import sys

def print_arr(arr):
    for a in arr:
        print(a)

def print_arr2(arr):
    for i in range(len(arr)-1, -1, -1):
        print(''.join(arr[i][1:-1]))

b = ' '
n = int(sys.stdin.readline())
arr = [[b]*(n*2+1) for _ in range(n)]

line_1_range_list = [(1,n*2),]
range_start_flag = False
for i in range(n):
    range_cache = []
    for line_1_start, line_1_end in line_1_range_list:
        if i%3==0:
            line_1_cnt = 0
            for j in range(line_1_start, line_1_end):
                if line_1_cnt == 5:
                    line_1_cnt = 0
                else:
                    arr[i][j] = '*'
                    line_1_cnt += 1
        if i%3==1:
            for j in range(line_1_start+1, line_1_end-1):
                if (arr[i-1][j-2]==b or arr[i-1][j+2]==b) and arr[i-1][j]=='*':
                    arr[i][j] = '*'

        if i%3==2:
            for j in range(line_1_start+2, line_1_end-2):
                if arr[i-1][j-1]=='*' and arr[i-1][j+1]=='*':
                    arr[i][j] = '*'
                    if range_start_flag:
                        range_start_flag = False
                        range_cache.append((start, j))
                    else:
                        range_start_flag = True
                        start = j+1
    
    if i%3==2:
        line_1_range_list = range_cache

print_arr2(arr)
