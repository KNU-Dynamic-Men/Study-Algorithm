#** ArrayQueue Implementation
#** 2020-07-15 LimeOrangePie

import sys
input_length = int(sys.stdin.readline().rstrip())
queue_body = [None] * 10000
queue_backidx = queue_frontidx = 0

for _ in range(input_length):
    user_input = sys.stdin.readline().rstrip().split(" ")
    if user_input[0] == 'push':
        queue_body[queue_backidx] = int(user_input[1])
        queue_backidx += 1
    elif user_input[0] == 'pop':
        if queue_frontidx >= queue_backidx:
            print(-1)
            continue
        print(queue_body[queue_frontidx])
        queue_frontidx += 1
    elif user_input[0] == 'size':
        print(queue_backidx - queue_frontidx)
    elif user_input[0] == 'empty':
        print('1' if queue_frontidx >= queue_backidx else '0')
    elif user_input[0] == 'front':
        if queue_frontidx >= queue_backidx:
            print(-1)
            continue
        print(queue_body[queue_frontidx])
    elif user_input[0] == 'back':
        if queue_frontidx >= queue_backidx:
            print(-1)
            continue
        print(queue_body[queue_backidx - 1])