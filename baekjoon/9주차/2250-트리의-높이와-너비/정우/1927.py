import sys

class Node:
    parent = None
    def __init__(self, i, left, right):
        self.i = i
        self.left = left
        self.right = right

# def init_node(arr, node):
#     arr[node.i][3] = 1
#     for i in range(node.i, 0, -1):
#         if arr[i][1] == node.i or arr[i][2] == node.i:
#             init_node(arr, Node())
#             break

def init_node(tree, arr, num, left, right):
    for i in range()
    tree[left] + tree[right]


n = int(sys.stdin.readline())
tree = [[0]*4 for _ in range(n+1)]
arr = [[0,0,0,0]]
for _ in range(n):
    arr.append(list(map(int, sys.stdin.readline().split()))+[0])
print(arr)

i, left, right = arr[1][0], arr[]
for i in range(len(arr), 0, -1):
    if arr[i][3] == 1:
        continue
    num, left, right = arr[i]
    tree[num][0], tree[num][1] = left, right
    init_node(tree, arr, num, left, right)
