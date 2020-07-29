# **10845 - 큐**

## **1. 개요**

[https://www.acmicpc.net/problem/10845](https://www.acmicpc.net/problem/10845)

## **2. 코드**

```python
import sys

n = int(sys.stdin.readline().strip())
arr = []
for _ in range(n):
    line = sys.stdin.readline().strip().split(' ')
    if line[0] == 'push':
        arr.append(line[1])
    elif line[0] == 'pop':
        if len(arr) > 0:
            print(arr.pop(0))
        else:
            print(-1)
    elif line[0] == 'size':
        print(len(arr))
    elif line[0] == 'empty':
        print(int(not bool(arr)))
    elif line[0] == 'front':
        if len(arr) > 0:
            print(arr[0])
        else:
            print(-1)
    elif line[0] == 'back':
        if len(arr) > 0:
            print(arr[-1])
        else:
            print(-1)
```

## **3. 설명**

1. 단순한 큐 문제

## **4. 여정**

1. 통과

## **5. 결과**
![image](https://user-images.githubusercontent.com/41278416/87217207-c619d280-c381-11ea-892d-f78adc7d2e74.png)