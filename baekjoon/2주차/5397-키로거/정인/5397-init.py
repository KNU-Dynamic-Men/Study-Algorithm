import sys

for _ in range(int(sys.stdin.readline().rstrip())):
    list_input = list(sys.stdin.readline().rstrip())

    list_left = []
    list_right = []

    for item in list_input:
        if item == '-':
            if list_left: list_left.pop()
        elif item == '<':
            if list_left: list_right.append(list_left.pop())
        elif item == '>':
            if list_right: list_left.append(list_right.pop())
        else:
            list_left.append(item)

    list_right.reverse()
    print(f'{"".join(list_left)}{"".join(list_right)}')