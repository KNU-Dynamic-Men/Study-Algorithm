# **1406 - 에디터**

## **1. 개요**

[https://www.acmicpc.net/problem/1406](https://www.acmicpc.net/problem/1475)

## **2. 코드**

### LinkedList version

```python
import sys

class Node:
    prev = None
    next = None
    def __init__(self, s:str):
        self.item = s

class LinkedList:
    def set_head(self, node:Node):
        self.head:Node = node
        self.cursor:Node = self.head # 커서는 항상 Node의 오른쪽에 위치
    def get_head(self) -> Node:
        return self.head
    def move_right(self):
        if self.cursor == None:
            self.cursor = self.head
        elif self.cursor.next != None:
            self.cursor = self.cursor.next
    def move_left(self):
        if self.cursor != None:
            self.cursor = self.cursor.prev
    def insert(self, node:Node):
        if self.cursor == None: # 맨 앞 추가
            node.next = self.head
            self.head.prev = node
            self.head = node
        else:
            next = self.cursor.next
            if next:
                next.prev = node
            self.cursor.next = node
            node.next = next
            node.prev = self.cursor
        self.cursor = node
    def delete(self):
        if self.cursor == None:
            return
        prev = self.cursor.prev
        next = self.cursor.next
        if next != None:
            next.prev = prev
        if prev != None:
            prev.next = next
        else: # 맨 앞 글자 삭제시 head 이동
            self.head = next
        self.cursor = prev

# Linked List initialize
st = sys.stdin.readline().strip()
ll = LinkedList()
ll.set_head(Node(st[0]))
for s in st[1:]:
    node = Node(s)
    ll.insert(node)

# Commands process
cmd_num = int(sys.stdin.readline().strip())
for _ in range(cmd_num):
    cmd = sys.stdin.readline().split()
    if cmd[0] == 'P':
        s = cmd[1]
        node = Node(s)
        ll.insert(node)
    if cmd[0] == 'L':
        ll.move_left()
    if cmd[0] == 'D':
        ll.move_right()
    if cmd[0] == 'B':
        ll.delete()

# Get full string
curr = ll.get_head()
res = curr.item
while curr.next != None:
    curr = curr.next
    res += curr.item
print(res)
```

### Stack version

```python
import sys

left = list(sys.stdin.readline().strip())
right = list()
input()
for cmd in sys.stdin:
    if cmd[0] == 'L' and left:
        right.append(left.pop())
    elif cmd[0] == 'D' and right:
        left.append(right.pop())
    elif cmd[0] == 'B' and left:
        left.pop()
    elif cmd[0] == 'P':
        left.append(cmd[2])
print(f"{''.join(left)}{''.join(right[::-1])}")
```

## **3. 설명**

### LinkedList version

1. 초기에 주어지는 문자열을 링크드 리스트에 삽입
2. 명령어를 차례대로 읽은 후, 명령어가 지시하는대로 리스트 변경
3. 출력

### Stack version

1. 커서를 기준으로 왼쪽은 `left` 배열에, 오른쪽은 `right` 배열에 넣는다.
    - 초기 문자열은 모두 커서의 왼쪽에 있으므로 모두 `left` 배열에 넣어 초기화 한다.
2. 커서가 왼쪽으로 이동하는 `L` 명령어의 경우 `left` 배열의 마지막 원소를 `pop` 하고, 해당 원소를 `right` 배열의 마지막에 추가한다.
3. 커서가 오른쪽으로 이동하는 `R` 명령어의 경우도 똑같이 반대로 동작한다.
4. `B` 명령어의 경우 커서 왼쪽, 즉, `left` 배열의 마지막 원소를 삭제한다.
5. `P` 명령어의 경우 커서 왼쪽, 즉, `left` 배열의 마지막에 추가한다.
6. 2~5번을 입력 명령어에 따라 반복한다.
7. 모두 끝나면 `left`는 그대로, `right`는 뒤집어서 출력한다.

## **4. 결과**

### LinkedList version

![image](https://user-images.githubusercontent.com/41278416/86471025-85193100-bd77-11ea-8703-29f00529c671.png)

### Stack version

![image](https://user-images.githubusercontent.com/41278416/86474660-4470e600-bd7e-11ea-90f4-ec846cf076a2.png)