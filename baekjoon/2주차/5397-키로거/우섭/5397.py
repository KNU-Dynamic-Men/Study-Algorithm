import sys

count = int(sys.stdin.readline())
for i in range(count):
    left = list()
    right = list()
    for input in sys.stdin.readline().strip():
        if input == '<' and left:
            right.append(left.pop())
        elif input == '>' and right:
            left.append(right.pop())
        elif input == '-' and left:
            left.pop()
        elif input not in {'<', '>', '-'}:
            left.append(input)
    print("{0}{1}".format("".join(getMap(str, left)), "".join(getMap(str, right[::-1]))))