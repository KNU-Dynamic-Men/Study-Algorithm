import sys

sys.setrecursionlimit(10000000)

def dfs(t):
    if visit[t]:
        return
    if before[t] != -1 and not visit[before[t]]:
        next[before[t]] = t
        return
    visit[t] = True
    if next[t] != -1:
        dfs(next[t])
    for i in graph[t]:
        dfs(i)


def solution(n, path, order):
    global graph, visit, before, next

    head = 0
    graph = [[] for _ in range(n)]
    visit = [False for _ in range(n)]

    before = [-1 for _ in range(n)]
    next = [-1 for _ in range(n)]

    for a, b in path:
        graph[a].append(b)
        graph[b].append(a)

    for a, b in order:
        before[b] = a

    if before[head] != -1:
        return False

    visit[head] = True
    for i in graph[head]:
        dfs(i)

    for i in range(n):
        if not visit[i]:
            return False

    return True


if __name__ == "__main__":
    print(solution(9,
             [[0, 1], [0, 3], [0, 7], [8, 1], [3, 6], [1, 2], [4, 7], [7, 5]],
             [[8, 5], [6, 7], [4, 1]]))
