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
