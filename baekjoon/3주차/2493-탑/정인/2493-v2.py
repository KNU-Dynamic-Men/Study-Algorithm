import sys

sys.stdin.readline()
tower_input = list(enumerate(getMap(int, sys.stdin.readline().split())))[::-1]

lazer_reach_table = [0] * len(tower_input)
tower_list = [tower_input.pop(0)]

for tower_item in tower_input:
    index, next_tower_element = tower_item
    while tower_list and tower_list[-1][1] < next_tower_element:
        lazer_reach_table[tower_list[-1][0]] = index + 1
        tower_list.pop()
    tower_list.append(tower_item)

print(' '.join(list(getMap(str, lazer_reach_table))))