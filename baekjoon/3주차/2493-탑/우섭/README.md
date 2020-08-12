# 2493 - 탑

## 1. 개요

https://www.acmicpc.net/problem/2493

## 2. 코드

1. 1회차 - [2493.py](https://github.com/KNU-Dynamic-Men/Study-Algorithm/blob/master/baekjoon/2493-탑/우섭/2493.py) *시간 초과* 
```python
from sys import stdin

getN = int(stdin.readline())
tower = list(getMap(int, stdin.readline().split(' ')))
ans = []
while(tower):
    t = tower.pop()
    idx = len(tower)
    is_find = False
    for i in reversed(tower):
        if i >= t:
            ans.append(idx)
            is_find = not is_find
            break
        idx -= 1
    if not is_find:
        ans.append(0)

for i in ans:
    print(i, end=' ')
```

2. 2회차 - [2493_2.py](https://github.com/KNU-Dynamic-Men/Study-Algorithm/blob/master/baekjoon/2493-탑/우섭/2493_2.py)
```python
from sys import stdin

getN = int(stdin.readline())
tower = list(getMap(int, stdin.readline().split(' ')))
ans = []
stack = []
for i in range(getN):
    while stack:
        if stack[-1][1] > tower[i]:
            ans.append(stack[-1][0] + 1)
            break
        stack.pop()
    if not stack:
        ans.append(0)
    stack.append((i, tower[i]))
for i in ans:
    print(i, end=' ')
```

## 3. 설명

1. 1회차 구상

    - 문제 자체는 간단해 보인다
    - 맨 마지막에서부터 왼쪽으로 가면서 자신보다 큰 값이 나오면 저장
    - 이를 반복하면 풀릴 것이다!
    - **그러나...**

2. 풀이 실패 및 문제점 확인

    - ![image](https://user-images.githubusercontent.com/29600820/87773997-eb657f80-c85e-11ea-9b7f-10eecaac2980.png)
    - ~~눈앞을 캄캄하게 만들어버린~~ **시간 초과**
    - 풀이 자체는 맞았지만, ```while```문과 ```for```문을 이중으로 쓰는 이상 Worst 시간 복잡도는 ```O(getN^2)```가 나온다 [참고 링크: 테스트 케이스 추가 요청](https://www.acmicpc.net/board/view/48073)
    - 이 문제의 테스트케이스는 ```N은 1 이상 500,000 이하```이다. ```O(getN^2)```의 시간 복잡도로는 1.5초 안에 **절대 안풀린다**
    - ```Stack```을 사용하라는 힌트를 받고 새로 구상하였다

3. ```Stack```을 활용한 풀이

    - 정답 배열인 ```ans```와 함께 또다른 배열인 ```stack```을 생성한다.
    ```python
    ans = []
    stack = []
    ```
    - ```stack``` 배열은 전파가 도달한 탑의 인덱스와 높이가 저장된다
    - ```stack``` 배열의 가장 뒤의 값을 확인하여 높이를 비교한다. 전파가 수신 가능한 높이, 즉 전파를 발송한 탑보다 ```stack``` 배열에 저장된 탑의 높이가 더 높은 경우 ```stack```에 저장된 인덱스를 ```ans``` 배열에 저장한다
    - 그렇지 않은 경우 ```stack```에서 ```pop()```을 하여 그 다음 탑을 탐색한다
    - 이렇게 낮은 탑은 더이상 수신할 수 없기 때문에 ```pop()```을 통해 버림으로서 시간복잡도를 단축시킨다
    ```python
    while stack:
        if stack[-1][1] > tower[i]:
            ans.append(stack[-1][0] + 1)
            break
        stack.pop()
    ```
    - ```stack```이 비어버릴 때 까지 탐색하지 못하면 현재 전파를 발송한 탑이 가장 높은 탑이고, 수신할 수 있는 탑이 없다는 의미이므로 ```ans``` 배열에 ```0```을 저장한다
    ```python
    if not stack:
        ans.append(0)
    ```
    - 마지막으로, 현재 탑의 인덱스와 높이를 ```stack```에 ```append```한다.
    ```python
    stack.append((i, tower[i]))
    ```

## 4. 캡쳐를 통한 과정 확인

1. 테스트케이스
```cmd
6
4 2 1 5 6 3
```

2. 처음 ```stack```에는 아무 값이 없으므로 ```0```이 ```ans```에 추가된 후 바로 ```stack```에 ```(0, 4)```가 추가된다
![image](https://user-images.githubusercontent.com/29600820/87775553-40a29080-c861-11ea-950f-982b8bd40e51.png)
![image](https://user-images.githubusercontent.com/29600820/87775713-81020e80-c861-11ea-9634-3fef7840d7a0.png)

3. 다음 탑은 높이가 ```2```이며, ```1```번째 탑의 높이가 ```4```이므로 전파가 수신된다. ```ans```에 ```1```을 추가하고 ```stack```에 ```(1, 2)```가 추가된다
![image](https://user-images.githubusercontent.com/29600820/87775841-b9095180-c861-11ea-89ac-ba7a97752be2.png)
![image](https://user-images.githubusercontent.com/29600820/87775900-ce7e7b80-c861-11ea-8f67-41510c02916d.png)

4. 같은 방법으로 3번째 탑까지 진행한다.

5. 4번째 탑의 탐색을 진행한다. ```stack``` 배열에 있는 값을 하나씩 탐색하는데, 전파를 수신하지 못하는, 4번째 탑보다 높이가 낮은 경우 ```pop()```을 해주어 더 이상 해당 탑을 탐색하지 않도록 한다.
![image](https://user-images.githubusercontent.com/29600820/87776773-40a39000-c863-11ea-94fc-81c988e4ef62.png)

6. 4번째 탑의 경우 최종적으로 전파를 수신할 탑이 존재하지 않기 때문에 ```ans```에 ```0```을 추가하고 다음 탑의 탐색을 진행한다.
![image](https://user-images.githubusercontent.com/29600820/87776874-6fba0180-c863-11ea-9051-201eb3adc6c6.png)

7. 마지막 탑까지 탐색하고 답을 출력한다.
![image](https://user-images.githubusercontent.com/29600820/87776943-8eb89380-c863-11ea-9778-a140a1258b93.png)
![image](https://user-images.githubusercontent.com/29600820/87776970-9bd58280-c863-11ea-87a9-102ff2e4a5bf.png)

## 5. 결과

![image](https://user-images.githubusercontent.com/29600820/87774221-3b444680-c85f-11ea-969e-036a47292e40.png)
