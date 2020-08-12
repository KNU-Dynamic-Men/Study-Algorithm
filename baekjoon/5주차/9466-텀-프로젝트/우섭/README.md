# 9466 - 텀 프로젝트

## 1. 개요

https://www.acmicpc.net/problem/9466

## 2. 코드

1. 1차 시도: <span style="color:#fa7268">*시간 초과*</span>
```python
from sys import stdin
from collections import deque

t = int(stdin.readline())
for _ in range(t):
    getN = int(stdin.readline())
    ls = list(getMap(int, stdin.readline().split(' ')))
    count = 0
    for i in range(getN):
        a = i
        find = False
        if ls[a]-1 == a:
            find = True
        while(not find):
            tmp = ls[a] - 1
            if tmp == i:
                find = True
                break
            if tmp == a:
                break
            a = tmp
        if not find:
            count += 1
    print(count)
```

2. 2차시도: <span style="color:#009874">**맞았습니다!!**</span>
```python
import sys
sys.setrecursionlimit(150000)
from collections import deque

def dfs(idx):
    visit[idx] = True
    next = ls[idx]-1
    if not visit[next]:
        dfs(next)
    else:
        if not end[next]:
            a = next
            while(True):
                cycle[a] = True
                if a == idx: break
                a = ls[a]-1
    end[idx] = True

t = int(sys.stdin.readline())
for _ in range(t):
    getN = int(sys.stdin.readline())
    ls = list(getMap(int, sys.stdin.readline().split(' ')))
    visit = [False for _ in range(getN)]
    end = [False for _ in range(getN)]
    cycle = [False for _ in range(getN)]
    for i in range(getN):
        if not visit[i]:
            dfs(i)
    print(cycle.count(False))
```

## 3. 설명

1. 구현 방법 구상

    - 초기 구상: 입력받은 배열에서 DFS를 이용해서 하나하나 탐색하면 되지 않을까...?
    - 구현 후 제출 -> <span style="color:#fa7268">*시간 초과*</span>
    - 전부 다 한 팀으로 구성된 매우 큰 배열인 경우 전부 다 탐색하면 시간이 **매우 오래 걸릴 것**

2. **다시 한 번 생각해보자...**

    - 한 팀이 된다는 것은 노드를 통해 생각해 보았을 때 **사이클 형태의 관계**가 생성된다는 것을 의미한다
    - 이미 방문한 노드, 이미 탐색이 끝난 노드 등을 판단해야 한다
    - 사이클 형태가 있는 노드인지도 따로 분류해야 한다
    - 탐색은 동일하게 깊이 우선 탐색
    - [사이클 찾기: DFS / Ian's Warehouse](https://code0xff.tistory.com/39)에서 아이디어와 구현방법 설명을 보고 직접 구현

3. 변수 지정
    ```python
    t = int(sys.stdin.readline())
    for _ in range(t):
        getN = int(sys.stdin.readline())
        ls = list(getMap(int, sys.stdin.readline().split(' ')))
        visit = [False for _ in range(getN)]
        end = [False for _ in range(getN)]
        cycle = [False for _ in range(getN)]
    ```
    - `t` : 테스트케이스의 개수
    - `getN` : 해당 테스트케이스의 배열의 크기
    - `ls` : 입력으로 주어지는 해당 테스트케이스의 선택된 학생들의 번호
    - `visit` : 해당 노드를 방문했는지 여부를 저장하는 배열
    - `end` : 해당 노드가 탐색이 완료되었는지 여부를 저장하는 배열
    - `cycle` : 해당 노드가 사이클 형태의 관계를 가지고 있는지 여부를 저장하는 배열

4. 탐색 진행
    ```python
    for i in range(getN):
        if not visit[i]:
            dfs(i)
    ```
    - 방문하지 않은 노드라면 **깊이우선탐색**을 진행한다

5. 깊이 우선 탐색(DFS)
    ```python
    visit[idx] = True
    next = ls[idx]-1
    if not visit[next]:
        dfs(next)
    ```
    - 해당 `idx`의 `visit`를 `True`로 바꾸어 해당 노드를 방문하였음을 저장해둔다
    - 해당 노드가 다음 어떤 노드를 가리키는지 `next`에 저장한다
    - 만약 `next` 노드가 방문하지 않은 노드라면 재귀호출을 통하여 해당 노드를 탐색한다

    ```python
    else:
        if not end[next]:
            a = next
            while(True):
                cycle[a] = True
                if a == idx: break
                a = ls[a]-1
    ```
    - 만약 해당 노드가 방문한 적이 있는 노드라면 **탐색이 완료된 노드인지 확인**한다
    - 방문한 적이 있는데 탐색이 완료되지 않은 노드는 **사이클을 형성한 노드**이므로 해당 사이클을 탐색한다
    - `a` 변수에다가 `next` 변수를 저장하고 탐색을 진행한다
    - `cycle`의 해당 노드가 사이클에 구성되어 있음을 저장한다
    - 만약 `a`가 현재 탐색중이던 노드인 `idx`와 같은 값이 되었다면 원점으로 돌아와서 사이클 확인이 완료된 것이므로 `while`문을 빠져나온다
    - 아닌 경우 사이클이 완성될 때 까지 다음 노드를 탐색한다
    ```python
    end[idx] = True
    ```
    - 모든 노드의 확인이 완료되면 `end`배열의 해당 노드를 탐색 완료하였음을 저장해둔다

6. 출력
    ```python
    print(cycle.count(False))
    ```
    - `cycle` 배열을 통해 사이클이 형성되지 않은 노드는 팀이 없는 학생이므로 `cycle` 배열에서 `False`의 개수를 구한 후 출력한다

7. 재귀 횟수 제한
    ```python
    import sys
    sys.setrecursionlimit(150000)
    ```
    - Python은 재귀 시 **<span style="color:#fa7268">최대 깊이가 1000으로 제한되어 있다! </span>**
    - 참고 링크: [Python Central](https://www.pythoncentral.io/resetting-the-recursion-limit/)
    - 해당 문제는 학생 최대 수가 100000명이며, 모든 학생이 연결되어 있는경우 **`dfs` 함수를 100000번 이상 호출될 수도 있다**
    - 따라서 `재귀 최대 깊이를 수정`해주어야만 문제가 정상적으로 해결이 가능해진다

## 4. 결과
![image](https://user-images.githubusercontent.com/29600820/89172202-33a1d300-d5bd-11ea-8a27-fd239bfa12b1.png)

