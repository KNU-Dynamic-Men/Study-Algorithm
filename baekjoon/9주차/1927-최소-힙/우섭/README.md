# 1927 - 최소 힙

## 1. 개요

- https://www.acmicpc.net/problem/1927
- *힙*(**Heap**)이란?
- 완전이진트리(complete binary tree)를 기본으로 한 자료구조
- **우선순위 큐**(Priority Queue)의 기본이 되는 자료구조
- A가 B의 부모노드(parent node) 이면, A의 키(key)값과 B의 키값 사이에는 **대소관계가 성립**한다.

## 2. 코드

1. *1927.py* - 직접 구현한 **Heap**
```python
from sys import stdin

class heap:

    def __init__(self):
        self.queue = []

    def enqueue(self, item):
        self.queue.append(item)
        self.moveup(0, len(self.queue) - 1)

    def moveup(self, first, last):
        newitem = self.queue[last]
        while last > first:
            parent = (last - 1) >> 1
            now = self.queue[parent]
            if newitem < now:
                self.queue[last] = now
                last = parent
                continue
            break
        self.queue[last] = newitem

    def dequeue(self):
        if self.queue:
            last = self.queue.pop()
            if self.queue:
                reitem = self.queue[0]
                self.queue[0] = last
                self.movedown(0)
                return reitem
            return last
        else:
            return 0

    def movedown(self, now):
        end = len(self.queue)
        start = now
        newitem = self.queue[now]
        child = (now << 1) + 1
        while child < end:
            right = child + 1
            if right < end and not self.queue[child] < self.queue[right]:
                child = right
            self.queue[now] = self.queue[child]
            now = child
            child = (now << 1) + 1
        self.queue[now] = newitem
        self.moveup(start, now)


n = int(stdin.readline())
item = heap()
for _ in range(n):
    t = int(stdin.readline())
    if t == 0:
        print(item.dequeue())
    else:
        item.enqueue(t)

```

2. *1927_2.py* - **heapq**를 이용한 풀이
```python
from sys import stdin
import heapq

n = int(stdin.readline())
h = []
for _ in range(n):
    input = int(stdin.readline())
    if input == 0:
        if len(h) == 0:
            print(0)
        else:
            print(heapq.heappop(h))
    else:
        heapq.heappush(h, input)
```

## 3. 풀이

1. 구현 방법 구상

    - 문제 이름에서 나온 것과 같이 **최소 힙**을 구현하면 된다
    - 학부 수업 중 자료구조 수업 시간에 배운 내용을 되새기면서 구현

2. `heap` 클래스 구현

    ```python
    class heap:

        def __init__(self):
            self.queue = []
    ```
    - `heap` `class` 생성 시 배열 하나를 둔다
    ```python
    def moveup(self, first, last):
        newitem = self.queue[last]
        while last > first:
            parent = (last - 1) >> 1
            now = self.queue[parent]
            if newitem < now:
                self.queue[last] = now
                last = parent
                continue
            break
        self.queue[last] = newitem
    ```
    - `moveup` 함수를 구현한다
    - `first`: 탐색의 임계점
    - `last`: 현재 노드
    - 부모 노드를 확인하고 값을 비교한다
    - 부모 노드보다 값이 작은 경우 현재 위치에 부모 노드 값을 저장하고 다음 부모 노드를 탐색한다
    - 부모 노드보다 값이 큰 경우 현재 위치에 값을 저장한다
    ```python
    parent = (last - 1) >> 1
    ```
    - 부모 노드의 `index` 값은 `(현재 노드 - 1) * 2`
    ```python
    def movedown(self, now):
        end = len(self.queue)
        start = now
        newitem = self.queue[now]
        child = (now << 1) + 1
        while child < end:
            right = child + 1
            if right < end and not self.queue[child] < self.queue[right]:
                child = right
            self.queue[now] = self.queue[child]
            now = child
            child = (now << 1) + 1
        self.queue[now] = newitem
        self.moveup(start, now)
    ```
    - `movedown` 함수를 구현한다
    - `now`: 해당 노드의 인덱스
    - 해당 노드를 리프 노드로 옮긴다
    - 리프 노드로 이동이 완료되었으면 다음 부모 노드를 탐색하면서 위치를 갱신한다
    ```python
    def enqueue(self, item):
        self.queue.append(item)
        self.moveup(0, len(self.queue) - 1)
    ```
    - `heap`에 `item`값을 추가하여 주는 `enqueue` 구현
    - 트리의 맨 뒤에 값을 추가하여 준 후 (그래프 기준으로는 가장 끝) **부모 노드의 값을 비교하면서** 위로 탐색을 진행한다
    ```python
    def dequeue(self):
        if self.queue:
            last = self.queue.pop()
            if self.queue:
                reitem = self.queue[0]
                self.queue[0] = last
                self.movedown(0)
                return reitem
            return last
        else:
            return 0
    ```
    - `heap`의 최소값을 반환해주는 `dequeue` 구현
    - 트리의 마지막 값을 `pop`해준 다음 루트 노드의 값을 따로 저장해둔 후 루트 노드에 `pop`해준 값을 저장한다
    - 루트 노드를 자식 노드와 비교하면서 위치를 이동시킨다
    - 이동이 완료되면 따로 저장해 두었던 루트 노드의 값을 반환한다

## 4. 결과
![image](https://user-images.githubusercontent.com/29600820/91953810-3ae30a80-ed3c-11ea-9283-e1c66691f283.png)

## 5. `heapq`를 이용한 결과
![image](https://user-images.githubusercontent.com/29600820/91954164-5fd77d80-ed3c-11ea-9500-86fbcb61bcc4.png)
- ~~있는 걸 쓰는 게 더 좋고 편할 때가 많다~~