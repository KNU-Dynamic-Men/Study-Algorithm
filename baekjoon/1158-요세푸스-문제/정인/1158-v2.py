import sys

[people_num, kth_removal] = [int(s) for s in sys.stdin.readline().rstrip().split(" ")]
people_list = list(range(1 ,people_num + 1))
josepus_list = list()
cursor = (kth_removal - 1) % people_num

while people_list:
    josepus_list.append(people_list[cursor])
    people_list[cursor] = None
    # print(people_list)

    new_cursor = (cursor + kth_removal) % len(people_list)
    if cursor + kth_removal >= len(people_list):
        people_list = list(filter(None, people_list))
        if not people_list:
            break
    cursor = new_cursor % len(people_list)

print(josepus_list.__str__().replace("[", "<").replace("]", ">"))