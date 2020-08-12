# 1158 - 요세푸스 문제

## 1. 개요
https://www.acmicpc.net/problem/1158    
- **요세푸스 수열**이란?
- [요세푸스 문제(위키피디아)](https://ko.wikipedia.org/wiki/%EC%9A%94%EC%84%B8%ED%91%B8%EC%8A%A4_%EB%AC%B8%EC%A0%9C)에서 따온 수열
- [유래와 방식을 좀 더 알기 쉽게 정리된 블로그](https://m.blog.naver.com/PostView.nhn?blogId=attractorlim&logNo=221030930874&proxyReferer=https:%2F%2Fwww.google.com%2F)
- ~~즉 **요세푸스가 살기위해** 머리를 굴려서 나온거였다~~

## 2. 코드
```python
import sys

getN, k = getMap(int, sys.stdin.readline().split())
ans = []
list = [x for x in range(1, getN+1)]
index = k - 1
while len(list) > 0:
    if len(list) > index:
        ans.append(list.pop(index))
        index += k - 1
    else:
        index = index % len(list)
        ans.append(list.pop(index))
        index += k - 1

result = '<'
for i in ans[:-1]:
    result += '{}, '.format(i)
result += '{}>'.format(ans[-1])
print(result)
```

## 3. 설명
1. 구현 방법 구상

    - LinkedList를 구현을 할까 생각하였으나 우선 List를 이용하여 구현
    - 다음 순번이 지정된 배열을 초과했을 때를 우선적으로 고려

2. 입력 및 변수지정

    - ```sys.stdin.readline().split()```을 이용하여 ```getN, k```값을 입력받는다.
    - ```list```는 정렬되기 전 모든 수가 저장된 리스트
    - ```ans```는 완성된 요세푸스 수열이 저장될 리스트
    - ```index```는 리스트에서 탐색할 위치
    ```python
    getN, k = getMap(int, sys.stdin.readline().split())
    ans = []
    list = [x for x in range(1, getN+1)]
    index = k - 1
    ``` 

3. 세부 사항 구현

    - ```list``` 배열에 아무런 값도 남아있지 않을 때 까지 반복한다.
    - ```index```가 ```list```의 크기보다 작은 경우 ```index```에 해당하는 값을 ```pop()```하고 수열 ```ans```에 저장한다. 
    ```python
    if len(list) > index:
        ans.append(list.pop(index))
        index += k - 1
    ```
    - ```index```가 ```list```의 크기보다 큰 경우 **나머지 연산**을 통해 오버플로우된 값을 제외한 나머지를 ```index```로 지정하여 해당 ```index```에 해당하는 값을 ```pop()```하고 수열 ```ans```에 저장한다.
    ```python
    else:
        index = index % len(list)
        ans.append(list.pop(index))
        index += k - 1
    ```

4. 출력

    - 지정된 출력 형식이 있으므로 해당 출력 형식에 맞추어 출력할 수 있도록 한다.
        > <3, 6, 2, 7, 5, 1, 4>
    - ```result``` 변수에 먼저 ```'<'```값을 넣고 뒤에 문자를 붙이는 형태로 진행하였다.
    ```python
    result = '<'
    ```
    - 배열의 맨 마지막 원소 이전까지 반복하여 값을 붙여넣는다.
    ```python
    for i in ans[:-1]:
        result += '{}, '.format(i)
    ```
    - 마지막 값을 추가하고 ```'>'```를 추가한 후 출력한다.
    ```python
    result += '{}>'.format(ans[-1])
    print(result)
    ```

## 4. 시행 착오

1. ```k```번째가 아닌 ```k+1```번째를 배열에서 선택하는 문제

    - 구현 시 ```list``` 배열에서 ```pop()```를 진행한 후 다음 ```index```를 탐색하므로, 전체 배열의 크기는 **1 줄어든 상태**가 된다.
    - 따라서, index 계산 시 **1을 감소시켜 주어야** 정상적으로 다음 값을 탐색하게 된다.
    ```python
    ans.append(list.pop(index))
    index += k - 1
    ```

## 5. 결과

![image](https://user-images.githubusercontent.com/29600820/87238631-15263d00-c440-11ea-9a47-c5a6bf077d45.png)
