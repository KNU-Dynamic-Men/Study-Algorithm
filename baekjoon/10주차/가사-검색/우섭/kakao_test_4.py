class Node:

    def __init__(self, item):
        self.item = item
        self.count = 0
        self.next = []

def insert(node, ch):
    if not node.next:
        node.next.append(Node(ch))
    for i in node.next:
        if i.item == ch:
            return i
    node.next.append(Node(ch))
    return node.next[-1]

def insert_word(tree, reverse, word):
    wlist = list(word)

    # 정방향
    head = tree
    head.count += 1
    for i in wlist:
        head = insert(head, i)
        head.count += 1

    # 역방향
    head = reverse
    head.count += 1
    for i in wlist[::-1]:
        head = insert(head, i)
        head.count += 1

def find_query(tree, word):
    only_word = word.replace('?', '')
    head = tree
    for i in list(only_word):
        if not head.next:
            return 0
        for j in head.next:
            if j.item == i:
                head = j
                break
    return head.count

def solution(words, queries):
    answer = []
    tree = [Node('0') for _ in range(100000)]
    tree_reverse = [Node('0') for _ in range(100000)]
    for i in words:
        insert_word(tree[len(i) - 1], tree_reverse[len(i) - 1], i)
    for i in queries:
        if i[0] != '?':
            answer.append(find_query(tree[len(i) - 1], i))
        else:
            answer.append(find_query(tree_reverse[len(i) - 1], i[::-1]))

    return answer


if __name__ == "__main__":

    # print(solution(["frodo", "front", "frost", "frozen", "frame", "kakao"],
    # ["fro??", "????o", "fr???", "fro???", "pro?", "?????", "??????"]))
    print(solution(
        ["frodo", "front", "frost", "frozen", "frame", "kakao"],  # words
        ["fro??", "????o", "fr???", "fro???", "pro?"]  # queries
    ))  # result: [3, 2, 4, 1, 0]

    print(solution(
        ["faaaa", "faaab", "faaac", "faaa", "faaaaa", "faaaab"],
        ["f????"]
    ))

    print(solution(
        ["f"],
        ["?"]
    ))

    print(solution(
        ["faaaa", "faaab", "faaac", "faaa", "faaaaa", "faaaab"],
        ["?????"]
    ))

    print(solution(
        ["faaaa", "faaab", "faaac", "faaa", "faaaaa", "faaaab"],
        ["????"]
    ))
