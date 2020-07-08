import sys
import math
room_no = sys.stdin.readline().strip().replace("9", "6")

number_dist_list = {}
for num in range(9):
    number_dist_list[str(num)] = 0

for num in room_no:
    number_dist_list[num] += 1
number_dist_list['6'] = math.ceil(number_dist_list['6'] / 2)

max_counts = 0
for num in range(9):
    max_counts = max(max_counts, number_dist_list[str(num)])

print(max_counts)