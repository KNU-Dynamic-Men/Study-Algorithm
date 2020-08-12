import sys, collections

T = int(sys.stdin.readline().strip())
for _ in range(T):
    p = sys.stdin.readline().strip()
    getN = int(sys.stdin.readline().strip())
    x = sys.stdin.readline().strip()[1:-1]
    if x:
        x = collections.deque(x.split(','))
    else:
        x = []

    reverse = False
    error = False
    for single_p in p:
        if single_p == 'R':
            reverse = not reverse
        elif single_p == 'D':
            if len(x) == 0:
                error = True
                break
            elif reverse:
                x.pop()
            else:
                x.popleft()
    if error:
        print('error')
    else:
        if reverse:
            ret = ','.join(list(x)[::-1])
        else:
            ret = ','.join(x)
        print(f'[{ret}]')