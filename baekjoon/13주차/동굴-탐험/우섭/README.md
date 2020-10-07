# 카카오 2020 인턴십 - 동굴 탐험

## 1. 개요

https://programmers.co.kr/learn/courses/30/lessons/67259

## 2. 코드

```python
import sys

sys.setrecursionlimit(10000000)

def dfs(t):
    if visit[t]:
        return
    if before[t] != -1 and not visit[before[t]]:
        next[before[t]] = t
        return
    visit[t] = True
    if next[t] != -1:
        dfs(next[t])
    for i in graph[t]:
        dfs(i)


def solution(n, path, order):
    global graph, visit, before, next

    head = 0
    graph = [[] for _ in range(n)]
    visit = [False for _ in range(n)]

    before = [-1 for _ in range(n)]
    next = [-1 for _ in range(n)]

    for a, b in path:
        graph[a].append(b)
        graph[b].append(a)

    for a, b in order:
        before[b] = a

    if before[head] != -1:
        return False

    visit[head] = True
    for i in graph[head]:
        dfs(i)

    for i in range(n):
        if not visit[i]:
            return False

    return True
```

## 3. 설명

1. 구현 방법 구상

    - [블로그에 정리된 글](https://medium.com/@haeseok/%ED%94%84%EB%A1%9C%EA%B7%B8%EB%9E%98%EB%A8%B8%EC%8A%A4-%EB%8F%99%EA%B5%B4-%ED%83%90%ED%97%98-a669d62f304d)을 읽어보고 **DFS**를 사용하여 풀이
    - 모든 경로를 탐색해본다
    - 조건에 의해 갈 수 없는 경우를 따로 체크하며 탐색한다

2. 그래서 어떻게...?

    - 특정 방은 먼저 방문해야 하는 방이 있다
    - 해당 좌표를 배열에 따로 저장해둔다
    - 만약 먼저 방문해야 하는 방을 지나지 않은 경우 해당 방은 **나중에 조건 충족 시 방문할 수 있도록** 따로 배열에 저장해주자

3. 메인 함수

    ```python
    def solution(n, path, order):
        global graph, visit, before, next

        head = 0
        graph = [[] for _ in range(n)]
        visit = [False for _ in range(n)]

        before = [-1 for _ in range(n)]
        next = [-1 for _ in range(n)]
    ```
    - 모든 변수는 `global`로 설정하여 다른 함수에서도 인자로 주지 않고 바로 접근이 가능하도록 구현한다
    - `head`: 그래프의 `head`, 입구는 주어진 조건(`이 동굴에 들어갈 수 있는 유일한 입구는 0번 방과 연결되어 있습니다.`)에 의해 반드시 `0`이므로 `0`으로 설정한다
    - `graph`: 주어진 동굴 그래프를 저장하는 배열
    - `visit`: 해당 방의 방문 여부를 저장하는 배열
    - `before`: 해당 인덱스의 방을 탐색하려면 **먼저 방문해야 하는 방의 인덱스**를 저장하는 배열
    - `next`: 해당 인덱스의 방이 탐색된 후 **다음 탐색이 가능해지는 방의 인덱스**를 저장하는 배열
    ```python
    for a, b in path:
        graph[a].append(b)
        graph[b].append(a)

    for a, b in order:
        before[b] = a
    ```
    - `graph`에 입력된 경로를 저장한다
    - `before`에 먼저 방문해야 하는 방의 인덱스를 저장한다
    ```python
    if before[head] != -1:
        return False
    ```
    - 입구에 진입이 불가능하면 바로 `False`를 반환한다
    ```python
    visit[head] = True
    for i in graph[head]:
        dfs(i)
    ```
    - `head`에서부터 방문을 시작한다
    - `head`에서 이어져있는 방을 하나씩 **DFS**를 이용하여 탐색한다
    ```python
    for i in range(n):
        if not visit[i]:
            return False

    return True
    ```
    - 모든 탐색이 완료되면 방문 여부를 확인한다
    - 하나의 방이라도 방문하지 않았다면 `False`를 반환하고, 아니면 `True`를 반환한다

4. DFS
    ```python
    def dfs(t):
    ```
    - 현재 방의 인덱스를 인자로 받아온다
    ```python
    if visit[t]:
        return
    ```
    - 해당 방이 이미 방문했으면 되돌아간다
    ```python
    if before[t] != -1 and not visit[before[t]]:
        next[before[t]] = t
        return
    ```
    - 해당 방이 **먼저 방문해야 하는 방을 방문하지 않은 경우** `next` 배열에 현재 방을 추가한 후 되돌아간다
    ```python
    visit[t] = True
    if next[t] != -1:
        dfs(next[t])
    for i in graph[t]:
        dfs(i)
    ```
    - 현재 방을 방문 상태로 바꾼다
    - 만약 해당 방 방문 시 다음 방문이 가능한 방이 있는 경우 해당 방으로 이동한다
    - 그 다음 현재 방과 연결된 모든 방을 탐색한다

## 4. 결과

- 정확성 테스트
    ```
    테스트 1 〉	통과 (0.01ms, 10.2MB)
    테스트 2 〉	통과 (0.02ms, 10.3MB)
    테스트 3 〉	통과 (0.12ms, 10.2MB)
    테스트 4 〉	통과 (1.24ms, 10.6MB)
    테스트 5 〉	통과 (0.81ms, 10.4MB)
    테스트 6 〉	통과 (1.12ms, 10.5MB)
    테스트 7 〉	통과 (0.13ms, 10.3MB)
    테스트 8 〉	통과 (0.52ms, 10.4MB)
    테스트 9 〉	통과 (0.87ms, 10.5MB)
    테스트 10 〉	통과 (0.01ms, 10.3MB)
    테스트 11 〉	통과 (0.10ms, 10.3MB)
    테스트 12 〉	통과 (0.02ms, 10.3MB)
    테스트 13 〉	통과 (0.03ms, 10.3MB)
    테스트 14 〉	통과 (0.03ms, 10.2MB)
    테스트 15 〉	통과 (0.12ms, 10.3MB)
    테스트 16 〉	통과 (0.11ms, 10.3MB)
    테스트 17 〉	통과 (1.13ms, 10.5MB)
    테스트 18 〉	통과 (1.11ms, 10.6MB)
    테스트 19 〉	통과 (1.16ms, 10.4MB)
    테스트 20 〉	통과 (0.11ms, 10.3MB)
    테스트 21 〉	통과 (0.10ms, 10.3MB)
    테스트 22 〉	통과 (1.23ms, 10.5MB)
    테스트 23 〉	통과 (0.02ms, 10.3MB)
    테스트 24 〉	통과 (0.03ms, 10.2MB)
    테스트 25 〉	통과 (0.13ms, 10.3MB)
    테스트 26 〉	통과 (1.25ms, 10.5MB)
    테스트 27 〉	통과 (0.13ms, 10.3MB)
    테스트 28 〉	통과 (1.13ms, 10.5MB)
    테스트 29 〉	통과 (0.02ms, 10.3MB)
    테스트 30 〉	통과 (0.01ms, 10.4MB)
    테스트 31 〉	통과 (0.02ms, 10.3MB)
    테스트 32 〉	통과 (0.02ms, 10.3MB)
    테스트 33 〉	통과 (0.78ms, 10.7MB)
    테스트 34 〉	통과 (1.69ms, 11.1MB)
    ```
- 효율성 테스트
    ```
    테스트 1 〉	통과 (341.56ms, 78.6MB)
    테스트 2 〉	통과 (388.94ms, 78.5MB)
    테스트 3 〉	통과 (358.60ms, 79.2MB)
    테스트 4 〉	통과 (364.45ms, 78.3MB)
    테스트 5 〉	통과 (360.16ms, 79MB)
    테스트 6 〉	통과 (361.63ms, 78.1MB)
    테스트 7 〉	통과 (453.81ms, 79.2MB)
    테스트 8 〉	통과 (394.66ms, 78.4MB)
    테스트 9 〉	통과 (141.64ms, 76.4MB)
    테스트 10 〉	통과 (384.09ms, 235MB)
    ```