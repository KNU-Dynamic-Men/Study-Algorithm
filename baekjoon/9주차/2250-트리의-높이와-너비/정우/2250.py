import sys

def up_from_leaf_to_root_with_calculating_childs_num(arr, num):
    abs_num = abs(num)
    parent = arr[abs_num][0]
    if parent == 0: # 루트 노드
        return
    my_child_num = arr[abs_num][3]+arr[abs_num][4]+1
    if parent < 0:  # 부모 노드가 오른쪽에 있을 때
        arr[abs(parent)][3] = my_child_num
    else:           # 부모 노드가 왼쪽에 있을 때
        arr[parent][4] = my_child_num
    up_from_leaf_to_root_with_calculating_childs_num(arr, parent)

def go_down_from_root_to_leaf_with_calculating_width(arr, width, level, left, right, left_flag, right_flag):
    if left == -1 or right == -1:
        return width, level
    child_width, next_left, next_right = 0, -1, -1

    if left != -1:
        if not left_flag:
            child_width += arr[left][4]+1
        else:
            child_width -= arr[left][3]+1
        if arr[left][1] != -1:
            next_left = arr[left][1]
            left_flag=False
        elif arr[left][2] != -1:
            next_left = arr[left][2]
            left_flag=True
    if right != -1:
        if not right_flag:
            child_width += arr[right][3]+1
        else:
            child_width -= arr[right][4]+1
        if arr[right][2] != -1:
            next_right = arr[right][2]
            right_flag=False
        elif arr[right][1] != -1:
            next_right = arr[right][1]
            right_flag=True

    next_width = width + child_width
    res_width, res_level = go_down_from_root_to_leaf_with_calculating_width(arr, next_width, level+1, next_left, next_right, left_flag, right_flag)
    if next_width < res_width:
        return res_width, res_level
    else:
        return next_width, level+1

n = int(sys.stdin.readline())
arr = [[0,0,0,0,0] for _ in range(n+1)]
# [0:parent_num, 1:left_child_num, 2:right_child_num, 3:left_childs_count, 4:right_childs_count]

root_candidate = set(range(1, n+1))
leafs = []
for _ in range(n):
    num, c1, c2 = map(int, sys.stdin.readline().split())
    arr[num][1], arr[num][2] = c1, c2
    if c1 != -1:
        arr[c1][0] = -num
        root_candidate.remove(c1)
    if c2 != -1:
        arr[c2][0] = num
        root_candidate.remove(c2)
    if c1 == c2 == -1:
        leafs.append(num)
root = root_candidate.pop()

for leaf_num in leafs:
    up_from_leaf_to_root_with_calculating_childs_num(arr, leaf_num)

left_child, right_child = arr[root][1], arr[root][2]
x, y = go_down_from_root_to_leaf_with_calculating_width(arr, 1, 1, left_child, right_child, False, False)
print(y, x)
