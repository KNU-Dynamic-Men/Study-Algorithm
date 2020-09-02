from sys import stdin

class Node:
    def __init__(self, number, left, right):
        self.parent = -1
        self.number = number
        self.left = left
        self.right = right

def find_inorder(val, dep):

    if tree[val].left != -1:
        find_inorder(tree[val].left, dep + 1)

    arr.append(tree[val].number)
    deparr[dep].append(len(arr))

    if tree[val].right != -1:
        find_inorder(tree[val].right, dep + 1)


n = int(stdin.readline())
tree = []
arr = []
deparr = [[] * n for _ in range(n+1)]

for i in range(n+1):
    tree.append(Node(i, -1, -1))

for _ in range(n):
    num, l, r = map(int, stdin.readline().split(' '))
    tree[num].left = l
    tree[num].right = r
    tree[l].parent = num
    tree[r].parent = num
root = 0

for i in tree:
    if i.number != 0 and i.parent == -1:
        root = i.number
        break

find_inorder(root, 1)
maxint = 0
maxdep = 0
for i in range(n+1):
    tmp = 0
    if len(deparr[i]) > 1:
        tmp = deparr[i][-1] - deparr[i][0] + 1

    elif len(deparr[i]) == 1:
        tmp = 1
    if maxint < tmp:
        maxint = tmp
        maxdep = i

print(f'{maxdep} {maxint}')
