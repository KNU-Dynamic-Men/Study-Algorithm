# **5397 - 키로거**

## **1. 개요**

[https://www.acmicpc.net/problem/5397](https://www.acmicpc.net/problem/5397)

## **2. 코드**

```python
import sys

n = int(sys.stdin.readline())
for i in range(n):
    line = list(sys.stdin.readline().strip())
    left = list()
    right = list()
    for c in line:
        if c == '<' and left:
            right.append(left.pop())
        elif c == '>' and right:
            left.append(right.pop())
        elif c == '-' and left:
            left.pop()
        elif c not in {'<','>','-'}:
            left.append(c)
    print(f"{''.join(left)}{''.join(right[::-1])}")
```

## **3. 설명**

1. 커서 왼쪽에 위치하는 문자들과 오른쪽에 위치하는 문자들을 각각 `left`, `right`로 지정.
2. `<` 나오면 `right`의 마지막 문자를 빼내어 `left`의 마지막 위치에 삽입
3. `>`도 `right`과 `left`의 역할만 바뀔뿐 마찬가지.
4. `-` 나오면 `left`의 마지막 원소를 삭제
5. 비밀번호로 사용되는 문자 나오면 `left`의 마지막 위치에 삽입
6. 모두 종료 후 `left`는 그대로, `right`는 뒤집어서 출력

## **4. 여정**

1. `-`가 나올때 `left`에 남아 있는 원소가 있는지 확인하지 못하여 런타임 에러.
2. 비밀번호로 사용되는 문자를 저장할때 `-`를 걸러내지 않아서 틀림.
3. 통과

## **5. 결과**
![image](https://user-images.githubusercontent.com/41278416/87126081-8e9a2000-c2c6-11ea-8219-3e6af79bbe97.png)