# **2020 카카오 블라인드 1차 4번 - 가사 검색**

## **1. 개요**

[https://programmers.co.kr/learn/courses/30/lessons/60060](https://programmers.co.kr/learn/courses/30/lessons/60060)

## **2. 코드**

Python3

```python
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
```

## **3. 설명**

1. word들을 트라이 자료구조 형태로 저장한다.
    1. word의 길이가 1인 트라이 자료구조, 2인 트라이 자료구조, ... 100,000인 트라이 자료구조를 모두 생성해둔다.
    2. 각 길이에 맞는 트라이 자료구조에 word를 저장한다.
    3. 단, 물음표가 query의 앞에도 올 수 있고, 뒤에서 올 수 있으므로 각 상황에 맞게 검색할 용도로 word를 순방향으로도 저장하고 역방향으로도 저장한다.
2. query의 길이에 맞는 트라이에서 query를 검색한다.
    1. 앞 글자부터 하나씩 떼어서 트라이 자료구조를 탐색한다.
    2. 물음표가 나오기 시작하면 검색을 중단하고 해당 트라이 자료구조 객체가 갖고 있는 글자의 수를 결과값으로 저장한다.
        - 트라이 자료구조를 word의 글자마다 만들었으므로, 물음표가 나온 시점에선 더 이상 query의 뒷부분을 탐색할 필요가 없다.
