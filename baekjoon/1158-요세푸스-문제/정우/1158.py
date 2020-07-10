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

n, k = map(int, sys.stdin.readline().split(' '))
ll = LinkedList()
for i in range(1, n+1):
    node = Node(i)
    ll.append(node)
ll.set_circle()
cnt = 1
res = []
while ll.get_size() > 0:
    if cnt % k == 0:
        res.append(ll.curr.curr)
        ll.delete()
    else:
        ll.curr = ll.curr.next
    cnt += 1

ret = '<'
for i in res[:-1]:
    ret += (str(i) + ', ')
ret += (str(res[-1]) + '>')
print(ret)