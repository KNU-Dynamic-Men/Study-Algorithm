import sys

line_number = 1
for line in sys.stdin:
    if line[0] == '-':
        break

    parenthesis_stack = []
    changed_parenthesises = 0

    for parenthesis in line.rstrip():
        if parenthesis == '{':
            parenthesis_stack.append(parenthesis)
        else:  # '}'
            if parenthesis_stack and parenthesis_stack[-1] == '{':
                parenthesis_stack.pop()
            else:
                changed_parenthesises += 1
                parenthesis_stack.append('{')

    # Remaining parenthesis
    changed_parenthesises += int(len(parenthesis_stack) / 2)

    print(f'{line_number}. {changed_parenthesises}')
    line_number += 1