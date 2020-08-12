# **1158 - 요세푸스 문제**

## **1. 개요**

[https://www.acmicpc.net/problem/1158](https://www.acmicpc.net/problem/1158)

## **2. 코드**

### Linked List
```python
import sys

class Node:
    prev = None
    next = None
    def __init__(self, i):
        self.curr = i

class LinkedList:
    head = None
    size = 0
    def set_head(self, node:Node):
        self.head = node
        self.curr = node
    def append(self, node:Node):
        if self.head is None:
            self.set_head(node)
        self.curr.next = node
        node.prev = self.curr
        self.curr = node
        self.size += 1
    def delete(self):
        self.curr.prev.next = self.curr.next
        self.curr.next.prev = self.curr.prev
        self.curr = self.curr.next
        self.size -= 1
    def set_circle(self):
        self.curr.next = self.head
        self.head.prev = self.curr
        self.curr = self.head
    def get_size(self):
        return self.size

getN, k = getMap(int, sys.stdin.readline().split(' '))
ll = LinkedList()
for i in range(1, getN+1):
    node = Node(i)
    ll.append(node)
ll.set_circle()
getCnt = 1
res = []
while ll.get_size() > 0:
    if getCnt % k == 0:
        res.append(ll.curr.curr)
        ll.delete()
    else:
        ll.curr = ll.curr.next
    getCnt += 1

ret = '<'
for i in res[:-1]:
    ret += (str(i) + ', ')
ret += (str(res[-1]) + '>')
print(ret)
```

### Array
```python
import sys

getN, k = getMap(int, sys.stdin.readline().split(' '))
arr = list(range(1, getN+1))
res = list()
idx = k-1
while len(arr) > 0:
    res.append(arr.pop(idx))
    idx += (k-1)
    while len(arr) > 0 and idx >= len(arr):
        idx -= len(arr)

ret = '<'
for i in res[:-1]:
    ret += (str(i) + ', ')
ret += (str(res[-1]) + '>')
print(ret)
```

## **3. 설명**

Linked List (통과 못함)
1. 순환형 Linked List를 만들고 하나씩 옮겨 탐색하며 `k`번째 마다 pop

Array
1. Array에 1부터 `getN`까지의 수를 전부 넣어 초기화.
2. Array의 `k*m`번째 인덱스에 있는 원소를 pop. (m=1 to 순환횟수)

## **4. 여정**

1. Linked List로 시도했을때 시간 초과. -> Array로 변경
2. 출력 예시를 따르지 않아 틀림
3. 성공

언뜻 보기에는 Linked List가 빨라 보이지만, 이 구조는 하나씩 계속 순환할 수 밖에 없기 때문에 시간이 많이 걸림. 입력 예시의 수가 크지 않기 때문에 Array의 pop으로 충분히 가능.

## **5. 결과**
![image](https://user-images.githubusercontent.com/41278416/87133716-aecfdc00-c2d2-11ea-8076-9b4447ae8ec4.png)