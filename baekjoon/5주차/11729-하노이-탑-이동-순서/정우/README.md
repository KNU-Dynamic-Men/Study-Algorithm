# **11729 - 하노이 탑 이동 순서**

## **1. 개요**

[https://www.acmicpc.net/problem/11729](https://www.acmicpc.net/problem/11729)

## **2. 코드**

Python3

```python
import sys

def hanoi(n, source, to, sub):
    if n == 1:
        print(source, to)
        return
    hanoi(n-1, source, sub, to)
    print(source, to)
    hanoi(n-1, sub, to, source)

N = int(sys.stdin.readline())
print(2**N-1)
hanoi(N, 1, 3, 2)
```

## **3. 설명**

1. 맨 처음에 이동에 필요한 횟수를 출력하고, 이동 순서를 찾기 위한 재귀 탐색을 실시한다.
2. 재귀 탐색 알고리즘
    1. 원반이 한 개면 그냥 옮길 수 있다.(종료 조건)
    2. 원반이 n개일 때
        1. 1번 기둥에 있는 n개 원반 중 n-1개를 2번 기둥으로 옮긴다.(3번 기둥은 보조)
        2. 1번 기둥에 남아 있는 가장 큰 원반을 3번 기둥으로 옮긴다.(출력)
        3. 2번 기둥에 남아 있는 n-1개 원반을 다시 3번 원반으로 옮긴다.(1번 기둥은 보조)

## **4. 여정**

1. 통과

## **5. 결과**
![image](https://user-images.githubusercontent.com/41278416/89097638-a5421b80-d41b-11ea-9512-cdaddb803af8.png)