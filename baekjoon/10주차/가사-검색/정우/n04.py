class Node:
    def __init__(self, w):
        self.nexts = dict()
        self.w = w
        self.cnt = 0

class Trie:
    def __init__(self):
        self.heads_dic = dict()
    def _add_node(self, node, word):
        if len(word) != 0:
            if word[0] in node.nexts.keys():
                n = node.nexts[word[0]]
            else:
                n = Node(word[0])
                node.nexts[word[0]] = n
        if len(word) > 1:
            self._add_node(n, word[1:])
        else:
            node.cnt += 1

    def add_word(self, word):
        if word[0] not in self.heads_dic.keys():
            self.heads_dic[word[0]] = Node(word[0])
        self._add_node(self.heads_dic[word[0]], word[1:])

    def _find_node(self, node, word):
        if len(word) <= 1:
            return node.cnt
        sumi = 0
        if word[0] == '?':
            for n in node.nexts.values():
                sumi += self._find_node(n, word[1:])
        elif word[0] in node.nexts.keys():
            n = node.nexts[word[0]]
            sumi += self._find_node(n, word[1:])
        return sumi

    def find_word(self, word):
        if word[0] in self.heads_dic.keys():
            return self._find_node(self.heads_dic[word[0]], word[1:])
        elif word[0] == '?':
            sumi = 0
            for n in self.heads_dic.values():
                sumi += self._find_node(n, word[1:])
            return sumi
        else:
            return 0

def solution(words, queries):
    answer = []
    foward_trie, backward_trie = Trie(), Trie()
    for word in words:
        foward_trie.add_word(word)
        backward_trie.add_word(word[::-1])

    for query in queries:
        if query[-1] == '?':
            answer.append(foward_trie.find_word(query))
        else:
            answer.append(backward_trie.find_word(query[::-1]))

    return answer

print(solution(
    ["frodo", "front", "frost", "frozen", "frame", "kakao"],    # words
    ["fro??", "????o", "fr???", "fro???", "pro?"]               # queries
))                                                  # result: [3, 2, 4, 1, 0]

print(solution(
    ["faaaa", "faaab", "faaac", "faaa", "faaaaa", "faaaab"],
    ["f????"]
))

print(solution(
    ["f"],
    ["?"] 
))