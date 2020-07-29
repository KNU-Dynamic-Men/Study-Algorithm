import sys
import time

[people_num, kth_removal] = [int(s) for s in sys.stdin.readline().rstrip().split(" ")]
queue_list = list(range(1, people_num + 1))
queue_size = people_num
queue_frontidx = 0
queue_backidx = queue_size - 1

begin = time.time()
result_str = '<'
for _ in range(people_num - 1):
    for _k in range(kth_removal - 1):
        queue_backidx = (queue_backidx + 1) % queue_size
        queue_list[queue_backidx] = queue_list[queue_frontidx]
        queue_frontidx = (queue_frontidx + 1) % queue_size
    result_str += f'{queue_list[queue_frontidx]}, '
    queue_frontidx = (queue_frontidx + 1) % queue_size
if queue_frontidx != queue_backidx + 1: result_str += str(queue_list[queue_frontidx])
result_str += '>'
print(result_str)
end = time.time()
print(f'Execution Time: {end - begin}')