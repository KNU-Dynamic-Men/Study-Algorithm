# 5397 - 키로거

## 1. 개요
https://www.acmicpc.net/problem/5397

## 2. 코드
```python
import sys

count = int(sys.stdin.readline())
for i in range(count):
    left = list()
    right = list()
    for input in sys.stdin.readline().strip():
        if input == '<' and left:
            right.append(left.pop())
        elif input == '>' and right:
            left.append(right.pop())
        elif input == '-' and left:
            left.pop()
        elif input not in {'<', '>', '-'}:
            left.append(input)
    print("{0}{1}".format("".join(getMap(str, left)), "".join(getMap(str, right[::-1]))))
```

## 3. 설명
1. 구현 방법 구상
   
    - 지난 주차에 풀었던 [1406 - 에디터](https://www.acmicpc.net/problem/1406)와 비슷한 문제
    - [지난 주차](https://github.com/KNU-Dynamic-Men/Study-Algorithm/tree/master/baekjoon/1406-%EC%97%90%EB%94%94%ED%84%B0/%EC%9A%B0%EC%84%AD)에는 JAVA를 이용하여 해결하였으나, [정우의 풀이](https://github.com/KNU-Dynamic-Men/Study-Algorithm/tree/master/baekjoon/1406-%EC%97%90%EB%94%94%ED%84%B0/%EC%A0%95%EC%9A%B0)를 참고하여 Python으로 풀이를 하였다.
    - JAVA로는 LinkedList와 ListIterator를 이용해서 풀었으나, Python에서는 기본 모듈로 LinkedList가 존재하지 않아 List로 풀이
    
2. 입력받기

    - ```input()```으로 입력을 받는 대신 ```sys.stdin.readline()```을 사용하여 입력 시간을 최소화
    - 참고: https://www.acmicpc.net/board/view/855
    ```python
    import sys
    
    count = int(sys.stdin.readline())
    ```
    
3. ```list()```

    - 커서 위치 기준 왼쪽과 오른쪽에 입력받은 값을 저장하는 ```list()```를 생성한다.
    - 여기서, ```right```의 경우 역순으로 저장한다는 점을 기억한다.
    ```python
    left = list()
    right = list()
    ```
   
4. 입력

    - 입력에 따라 작동 방식을 구현한다.
    - ```<```가 입력된 경우 왼쪽 리스트의 값을 ```pop()```하여 오른쪽에 추가하여 커서를 왼쪽으로 이동한 것 처럼 구현한다.
    - ```>```가 입력된 경우 오른쪽 리스트의 값을 ```pop()```하여 왼쪽에 추가하여 커서를 오른쪽으로 이동한 것 처럼 구현한다.
    - ```-```가 입력된 경우 왼쪽 리스트의 값을 ```pop()```하여 왼쪽의 값을 제거한다.
    - 그 외의 값이 입력된 경우 왼쪽 리스트에 추가한다.

    ```python
    for input in sys.stdin.readline().strip():
        if input == '<' and left:
            right.append(left.pop())
        elif input == '>' and right:
            left.append(right.pop())
        elif input == '-' and left:
            left.pop()
        elif input not in {'<', '>', '-'}:
            left.append(input)
    ```
 
 ## 4. 시행착오

1. 커맨드가 비밀번호에 입력되는 문제
    ```python
    else:
        left.append(input)
    ```
    - 가장 처음 인식받을 때 커맨드인 ```'<', '>', '-'```가 인식된 경우, ```if-else``` 구문에서 이를 걸러내지 않고 그대로 리스트에 추가
    - 마지막 ```else```를 ```elif```로 바꾸고 ```not in {'<', '>', '-'}```구문을 추가하여 커맨드를 리스트에 넣지 않도록 처리

2. 출력 시 맨 처음 문자열만 출력되는 문제
    
    - 출력 코드가 ```for```문장 안에 들어가있지 않아서 생긴 문제
    - ~~python은 들여쓰기가 중요하다는 걸 다시 한 번 느꼈다~~
 
 ## 5. 결과
 ![image](https://user-images.githubusercontent.com/29600820/87236942-a5598780-c42a-11ea-89a6-d3c24b645ea8.png)
        