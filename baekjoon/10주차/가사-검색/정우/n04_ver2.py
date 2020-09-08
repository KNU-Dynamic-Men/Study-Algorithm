class Node:
    """
    A node that consists of a trie.
    """
    
    def __init__(self, key, count=0, data=None):
        self.key = key
        self.count = count
        self.children = {}

class Trie:
    def __init__(self):
        self.head = Node(None)
    
    """
    트라이에 문자열을 삽입합니다.
    """
    def insert(self, string):
        curr_node = self.head
        
        for char in string:
            if char not in curr_node.children:
                curr_node.children[char] = Node(char)
            curr_node.count += 1
            curr_node = curr_node.children[char]
    
    
    """
    주어진 단어 string이 트라이에 존재하는지 여부를 반환합니다.
    """
    def search(self, string):
        curr_node = self.head
        
        for char in string:
            if char == '?':
                return curr_node.count
            if char in curr_node.children:
                curr_node = curr_node.children[char]
            else:
                return 0

def solution(words, queries):
    answer = []
 
    front_trie_list = [Trie() for _ in range(100001)]
    back_trie_list = [Trie() for _ in range(100001)]

    for word in words:
        back_trie = back_trie_list[len(word)]
        back_trie.insert(word[::-1])
        front_trie = front_trie_list[len(word)]
        front_trie.insert(word)

    for query in queries:
        if query[0] == '?':
            trie = back_trie_list[len(query)]
            count = trie.search(query[::-1])
        else:
            trie = front_trie_list[len(query)]
            count = trie.search(query)
        answer.append(count)

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