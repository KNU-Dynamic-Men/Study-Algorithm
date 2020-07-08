import sys

body_left = list(sys.stdin.readline().strip())
body_right = list()
num_commands = int(sys.stdin.readline())

for _ in range(num_commands):
    command = sys.stdin.readline().strip()
    if command == 'L': # move left
        if len(body_left) > 0: body_right.append(body_left.pop())
    elif command == 'D': # move right
        if len(body_right) > 0: body_left.append(body_right.pop())
    elif command == 'B': # delete left
        if len(body_left) > 0: body_left.pop()
    elif command[0] == 'P': # append left
        body_left.append(command.split(" ")[1])
    else:
        raise RuntimeError("Unexpected input: " + command)

body_right.reverse()

body_left_concat = ''.join(body_left)
body_right_concat = ''.join(body_right)
print(body_left_concat, end='')
print(body_right_concat)