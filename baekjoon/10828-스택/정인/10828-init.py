#** ArrayStack Implementation
#** 2020-07-15 LimeOrangePie

import sys
input_length = int(sys.stdin.readline().rstrip())
stack_body = [None] * 10000
stack_topidx = 0

for _ in range(input_length):
    user_input = sys.stdin.readline().rstrip().split(" ")
    if user_input[0] == 'push':
        stack_body[stack_topidx] = int(user_input[1])
        stack_topidx += 1
    elif user_input[0] == 'pop':
        if stack_topidx <= 0:
            print(-1)
            continue
        print(stack_body[stack_topidx - 1])
        stack_topidx -= 1
    elif user_input[0] == 'size':
        print(stack_topidx)
    elif user_input[0] == 'empty':
        print('1' if stack_topidx <= 0 else '0')
    elif user_input[0] == 'top':
        if stack_topidx <= 0:
            print(-1)
            continue
        print(stack_body[stack_topidx - 1])