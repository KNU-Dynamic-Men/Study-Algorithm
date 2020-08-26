import collections
import sys

for _ in range(int(sys.stdin.readline().rstrip())):
    function_list = list(sys.stdin.readline().rstrip())
    if int(sys.stdin.readline().rstrip()) > 0:
        array_body = collections.deque(map(int, sys.stdin.readline().rstrip()[1:-1].split(",")))
    else:
        sys.stdin.readline()
        array_body = []

    error = False
    location_positive = True
    for function_value in function_list:
        if function_value == 'D':
            if not array_body:
                error = True
                print("error")
                break
            
            if location_positive:
                array_body.popleft()
            else:
                array_body.pop()
        elif function_value == 'R':
            location_positive = not location_positive

    if not error:
        if location_positive:
            print(f'[{",".join(list(map(str, array_body)))}]')
        else:
            print(f'[{",".join(list(map(str, list(array_body)[::-1])))}]')