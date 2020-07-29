import sys
import time

class Queue(object):
    # def __init__(self, nsize):
    #     super().__init__()
    #     self.array = [None] * nsize
    #     self.arraysize = len(self.array)

    #     self.frontidx = 0
    #     self.backidx = 0
    #     self.itemsize = 0

    def __init__(self, data):
        super().__init__()
        self.array = data
        self.arraysize = len(self.array)

        self.frontidx = 0
        self.backidx = 0
        self.itemsize = 0

    def put(self, item):
        # if self.itemsize >= self.arraysize:
        #     raise RuntimeError("This queue doesn't support array expansion!")
        self.array[self.backidx] = item
        self.backidx = (self.backidx + 1) % self.arraysize
        self.itemsize += 1

    def get(self):
        # if self.itemsize <= 0:
        #     raise RuntimeError("No item to serve!")
        item = self.array[self.frontidx]
        self.frontidx = (self.frontidx + 1) % self.arraysize
        self.itemsize -= 1
        return item

    def empty(self):
        return self.itemsize == 0


[people_num, kth_removal] = [int(s) for s in sys.stdin.readline().rstrip().split(" ")]

# people_queue = Queue(people_num)
# for people_id in range(1, people_num + 1):
#     people_queue.put(people_id)

begin = time.time()
people_queue = Queue(list(range(1, people_num + 1)))

result = []
for _ in range(people_num):
    for _k in range(kth_removal - 1):
        people_queue.put(people_queue.get()) # k-1 items
    result.append(people_queue.get())

print(result.__str__().replace('[', '<').replace(']', '>'))
end = time.time()
print(f'Execution Time: {end - begin}')