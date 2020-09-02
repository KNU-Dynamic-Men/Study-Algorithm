from sys import stdin

# 노드 구현
class Node:
    def __init__(self, number, left, right):
        self.parent = -1
        self.number = number
        self.left = left
        self.right = right

# 중위 탐색
def find(number, depth):
    global idx, maxdepth

    # 최대 깊이 저장
    if maxdepth < depth:
        maxdepth = depth

    # 왼쪽 노드 존재 시 탐색
    if tree[number].left != -1:
        find(tree[number].left, depth + 1)

    # 현재 노드를 기준으로
    # 동일한 깊이의 최소, 최대값 갱신
    minarr[depth] = min(minarr[depth], idx)
    maxarr[depth] = idx
    idx += 1

    # 오른쪽 노드 존재 시 탐색
    if tree[number].right != -1:
        find(tree[number].right, depth + 1)


n = int(stdin.readline())
tree = []
minarr = [n+1 for _ in range(n+1)]
maxarr = [0 for _ in range(n+1)]

# 기본 노트 세팅
for i in range(n+1):
    tree.append(Node(i, -1, -1))

# 노드 입력받기
for _ in range(n):
    num, l, r = map(int, stdin.readline().split(' '))
    tree[num].left = l
    tree[num].right = r
    tree[l].parent = num
    tree[r].parent = num

# 루트 노드 찾기
root = 0
for i in tree:
    if i.number != 0 and i.parent == -1:
        root = i.number
        break

# 탐색 진행
idx = 1
maxdepth = 1
find(root, 1)

# 넓이 최대값 및 해당 깊이 구하기
max = tmp = maxarr[1] - minarr[1] + 1
dep = 1
for i in range(1, maxdepth+1):
    tmp = maxarr[i] - minarr[i] + 1
    if tmp > max:
        max = tmp
        dep = i

print(f'{dep} {max}')