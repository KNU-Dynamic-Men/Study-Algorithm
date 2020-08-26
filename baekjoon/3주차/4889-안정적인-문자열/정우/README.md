# **4889 - 안정적인 문자열**

## **1. 개요**

[https://www.acmicpc.net/problem/4889](https://www.acmicpc.net/problem/4889)

## **2. 코드**

```python
import sys

n = 1
while True:
    arr = sys.stdin.readline().strip()
    if arr[0] == '-':
        break
    stack = 0
    cnt = 0
    for i in range(len(arr)):
        if stack == 0 and arr[i] == '}':
            cnt += 1
            stack += 1
        elif arr[i] == '}':
            stack -= 1
        elif arr[i] == '{':
            stack += 1
    print(f'{n}. {cnt+int(stack/2)}')
    n += 1
```

## **3. 설명**

1. `{`가 나오면 스택에 저장한다.
2. `}`가 나오면 스택에 있는 `{`를 하나 뺀다.
3. 스택이 비어 있는데 `}`가 나온다면 `{`도 변경 후 스택에 저장한다. -> `cnt += 1`
4. 모두 종료 후 스택에 `{`가 남아 있다면, 이를 쌍을 맞춰 주어야 하기 때문에 절반이 `}`로 변경 되어야 한다. -> `cnt += (스택 길이)/2`

## **4. 여정**

1. 성공했던 코드와 비슷하게 했으나 스택이 비어 있을 때를 적절히 처리해주지 못함
2. 뻘짓
3. 뻘짓
4. 뻘짓
5. 성공

## **5. 결과**
![image](https://user-images.githubusercontent.com/41278416/87930574-4bf4f680-cac3-11ea-8d2d-e64371b49335.png)