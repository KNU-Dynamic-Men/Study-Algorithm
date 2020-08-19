# **5430 - AC**

## **1. 개요**

[https://www.acmicpc.net/problem/5430](https://www.acmicpc.net/problem/5430)

## **2. 코드**

```python
import sys

T = int(sys.stdin.readline().strip())
for _ in range(T):
    p = sys.stdin.readline().strip()
    getN = int(sys.stdin.readline().strip())
    x = sys.stdin.readline().strip()[1:-1]
    if x:
        x = x.split(',')
    else:
        x = []

    reverse = False
    error = False
    for single_p in p:
        if single_p == 'R':
            reverse = not reverse
        elif single_p == 'D':
            if len(x) == 0:
                error = True
                break
            elif reverse:
                x.pop()
            else:
                x.pop(0)
    if error:
        print('error')
    else:
        if reverse:
            ret = ','.join(x[::-1])
            print(f'[{ret}]')
        else:
            ret = ','.join(x)
            print(f'[{ret}]')
```

## **3. 설명**

1. 숫자를 앞에서 뺄건지, 뒤에서 뺄건지 정하는 플래그 변수 `reverse`를 사용한다.
2. `R`이 나오면 `reverse`의 값을 토글시킨다.
3. `D`가 나왔을땐
    1. `reverse`가 `False`이면 앞에서 숫자를 뺀다.
    2. `reverse`가 `True`이면 뒤에서 숫자를 뺀다.
4. 출력

## **4. 여정**

1. 출력 형식을 따르지 않아 실패
2. 성공

## **5. 결과**
![image](https://user-images.githubusercontent.com/41278416/87904741-f2c49d00-ca99-11ea-8f8f-409e3c8e05a0.png)