# **2448 - 별 찍기 - 11**

## **1. 개요**

[https://www.acmicpc.net/problem/2448](https://www.acmicpc.net/problem/2448)

## **2. 코드**

Python3

```python
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

```

## **3. 설명**

1. 별들의 맨 아래칸부터 쌓아 올라간다.
2. 별들의 가장 최소 구성 단위가 가로 5, 세로3 이므로 3줄씩 나눠서 처리한다.
3. 첫 줄은 베이스가 되는 별 5개 저장.
4. 두 번쨰 줄은 첫 번쨰 줄에서 가운데 두 위치에 별 저장.
5. 세 번째 줄은 아래에서 쌓아 올라온 별들의 꼭짓점 위치에 저장.

## **4. 여정**

1. 첫 번째 줄의 시작점과 종료점 -> 다른 별들의 꼭짓점과 연동하지 않아 실패
2. 성공

## **5. 결과**
![image](https://user-images.githubusercontent.com/41278416/89975603-21045980-dca1-11ea-9005-cd697f12f195.png)