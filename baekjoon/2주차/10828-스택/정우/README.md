# **10845 - 스택**

## **1. 개요**

[https://www.acmicpc.net/problem/10828](https://www.acmicpc.net/problem/10828)

## **2. 코드**

```python
import sys

getN = int(sys.stdin.readline().strip())
arr = []
for _ in range(getN):
    line = sys.stdin.readline().strip().split(' ')
    if line[0] == 'push':
        arr.append(line[1])
    elif line[0] == 'top':
        if len(arr) > 0:
            print(arr[-1])
        else:
            print(-1)
    elif line[0] == 'pop':
        if len(arr) > 0:
            print(arr.pop())
        else:
            print(-1)
    elif line[0] == 'size':
        print(len(arr))
    elif line[0] == 'empty':
        print(int(not bool(arr)))
```

## **3. 설명**

1. 간단한 스택 문제

## **4. 여정**

1. 통과

## **5. 결과**
![image](https://user-images.githubusercontent.com/41278416/87217092-cf566f80-c380-11ea-91b9-9ece3374ff6c.png)