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