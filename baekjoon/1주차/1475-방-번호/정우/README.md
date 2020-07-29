# **1475 - 방 번호**

## **1. 개요**

[https://www.acmicpc.net/problem/1475](https://www.acmicpc.net/problem/1475)

## **2. 코드**

```python
import sys, math

# N = sys.stdin.readline() # 왜 런타임 에러?..
N = input()
arr = [0,]*10
for i in N:
    arr[int(i)] += 1
arr[6] = math.ceil((arr[6]+arr[9])/2)
print(max(arr[0:9]))
```

## **3. 설명**

1. `arr` 배열에 0부터 9까지의 플라스틱 숫자의 개수를 각각 0으로 초기화한다.
2. 방 번호에서 숫자 하나씩 떼어 보며, 각 숫자가 몇 개씩 나오는지 `arr` 배열에 저장한다.
3. 가장 많이 카운팅된 숫자 만큼의 숫자 플라스틱 세트가 필요하므로  `arr` 배열에서 가장 많이 카운팅된 숫자가 답이다.
    - 단, 6과 9는 서로 뒤집어 사용할 수 있으므로 서로의 카운팅 숫자를 합산한 후에 반으로 나눈 숫자를 카운팅 개수로 사용한다.

## **4. 결과**
![image](https://user-images.githubusercontent.com/41278416/86448807-40c76a00-bd52-11ea-80fc-84eef4c05ae7.png)