# 5430 - AC

## 1. 개요

https://www.acmicpc.net/problem/5430

## 2. 코드

```python
import sys

def printer(card, is_reverse):
    if card:
        if not is_reverse:
            tmp = '['
            for i in card[:-1]:
                tmp += (str(i) + ',')
            tmp += (str(card[-1]) + ']')
        else:
            tmp = '['
            for i in reversed(card[1:len(card)]):
                tmp += (str(i) + ',')
            tmp += (str(card[0]) + ']')
    else:
        tmp = '[]'
    print(tmp)

n = int(sys.stdin.readline())
for _ in range(n):
    command = list(sys.stdin.readline().strip('\n'))
    length = int(sys.stdin.readline())
    card = sys.stdin.readline().lstrip('[').rstrip('\n').rstrip(']').split(',')
    if card[0] == '':
        card.pop()
    is_reverse = False
    is_end = True
    for i in command:
        if i == 'R':
            is_reverse = not is_reverse
        elif i == 'D':
            if card:
                if is_reverse:
                    card.pop(-1)
                else:
                    card.pop(0)
            else:
                is_end = False
                print('error')
                break
    if is_end:
        printer(card, is_reverse)
```

## 3. 설명

1. 구현 방법 구상

    - 구현이 쉬워보이는 것 치고는 **상당히 정답률이 낮은 문제**
    - 실제로 **시간 초과**나 **틀렸습니다**를 상당히 자주 보았다
    - 입출력 등을 확인해 보고, 틀린 부분이 어느 부분인지 하나씩 점검하면서 구현하였다
    - 계속해서 풀이가 되지 않는다면 [★☆★☆★ [필독] AC FAQ ★☆★☆★](https://www.acmicpc.net/board/view/25456) 글을 참고해보자

2. 입력받기

    ```python
    command = list(sys.stdin.readline().strip('\n'))
    length = int(sys.stdin.readline())
    card = sys.stdin.readline().lstrip('[').rstrip('\n').rstrip(']').split(',')
    ```
    - 매번 읽을 때 **개행문자를 조심하도록 하자**
    - ```strip()```, ```lstrip('')```, ```rstrip('')``` 등을 활용하여 문자열을 전처리 후 ```list``` 타입으로 저장한다.

3. 변수 지정

    ```python
    is_reverse = False
    is_end = True
    ```
    - ```is_reverse``` : 해당 ```list```가 뒤집혀있는지 확인하는 변수
    - ```is_end``` : 해당 커맨드가 정상적으로 완료되었는지 (```error```로 인해 ```for```문이 중간에 종료되었는지 판단용)

4. 커맨드 작동 구현

    ```python
    for i in command:
        if i == 'R':
            is_reverse = not is_reverse
        elif i == 'D':
            if card:
                if is_reverse:
                    card.pop(-1)
                else:
                    card.pop(0)
            else:
                is_end = False
                print('error')
                break
    ```

    - R 커맨드의 경우 ```is_reverse``` 값을 수정하여 뒤집혔는지 여부를 판단
    - D 커맨드의 경우, ```is_reverse```에 따라 배열의 처음이 버려지는지, 마지막이 버려지는지 확인 후 ```pop()```을 작동한다.
    - D 커맨드시 배열이 비어있는 경우 ```error```를 출력하고 종료한다.

5. 출력

    ```python
    def printer(card, is_reverse):
        if card:
            if not is_reverse:
                tmp = '['
                for i in card[:-1]:
                    tmp += (str(i) + ',')
                tmp += (str(card[-1]) + ']')
            else:
                tmp = '['
                for i in reversed(card[1:len(card)]):
                    tmp += (str(i) + ',')
                tmp += (str(card[0]) + ']')
        else:
            tmp = '[]'
        print(tmp)
    ```

    - 배열이 뒤집혀있는지 여부를 확인한 후 출력한다.
    - 비어있는 배열은 ```[]```를 출력한다.

## 4. 시행착오

1. 시간 초과

    - 별 생각 없이 배열을 뒤집는다는 커맨드가 들어올 경우 배열을 바로 뒤집어줌
    ```python
    if i == 'R':
        card.reverse()
    ```
    - ![image](https://user-images.githubusercontent.com/29600820/87681847-1a75e580-c7ba-11ea-9676-76ef727d4c33.png)
    - **바로 시간 초과행**
    - 배열을 뒤집는 작동을 여러번 하게 될 경우 시간이 오래 걸리게 되고, 초과로 이어질 수 있어 ```boolean``` 값으로 배열이 뒤집히는 여부를 확인하고 작동하게 함

2. ~~의미를 알 수 없는~~ 출력 값 형태

    ```cmd
    RRD
    6
    [1,1,2,3,5,8]
    [1,2,3,5,8]]
    ```
    ```cmd
    1
    R
    0
    []
    [
    ]
    ```
    - 문자열 입력 시 ```[, ], \n```등의 문자가 제대로 사라지지 않고 그대로 읽어들이게 되고, 이를 배열에 넣게 되어 문제 발생
    - 어째서인지 ```strip(']')```가 정상적으로 작동하지 않아 ```lstrip('[').rstrip('\n').rstrip(']')``` 와 같이 수정하여 구현

3. 이상한 입력 값 상태

    ```cmd
    D
    0
    []
    []
    ```
    - 비어있는 배열을 입력받고 D 커맨드가 입력된 경우 ```error```가 아닌 그대로 빈 배열을 출력하는 문제
    - 입력시 ```split()``` 함수로 인해 비어있는 배열이 아닌 ```['']``` 상태의 비어있는 값이 들어간 배열이 생성됨
    - 해당 배열은 ```pop()``` 을 통하여 비어있는 배열로 만들어준 후 작동하도록 구현

## 5. 결과

![image](https://user-images.githubusercontent.com/29600820/87682897-5c535b80-c7bb-11ea-8a0e-86df64178dea.png)

- 실행 시간이 ```3812 ms```로 매우 느리게 나온 것을 확인하였다.
- D 커맨드 입력 시 pop()을 하는 과정에서 배열의 순서가 재배치되어 실행 시간이 매우 느려진 것 같다
- 다시 구현하게 되는 경우 pop()을 하는 방법이 아닌, 해당 값을 보이지 않게 하는 방법을 연구해보아야 겠다