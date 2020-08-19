import sys

tower_count = int(sys.stdin.readline().rstrip())
towers = list(getMap(int, sys.stdin.readline().split()))
tower_stack = list()

tower_length_list = []
for tower_id in range(tower_count):
    tower_item = towers[tower_id]
    if tower_stack and tower_stack[0] < tower_item:
        while tower_stack:
            tower_stack.pop()
        tower_length_list.append(0)
    else:
        tower_target_index = tower_id
        while tower_stack and tower_stack[-1] < tower_item:
            tower_stack.pop()
            tower_target_index -= 1
        tower_length_list.append(tower_target_index)

    tower_stack.append(tower_item)

print(' '.join(list(getMap(str, tower_length_list))), end='', flush=True)