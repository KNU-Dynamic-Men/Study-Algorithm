from collections import deque

def balance(s):
    t = 0
    for idx, i in enumerate(s):
        if i == '(':
            t += 1
        if i == ')':
            t -= 1
        if t == 0:
            return idx

def is_perfect(s):
    t = deque()
    for i in list(s):
        if i == '(':
            t.append('(')
        else:
            if not t:
                return False
            else:
                t.pop()
    if t:
        return False
    return True
        
def reverse(s):
    tmp = ''
    for i in list(s):
        tmp += ')' if i == '(' else '('
    return tmp

def solution(s):
    if len(s) == 0 or is_perfect(s):
        return s
    idx = balance(s)
    u, v = s[:idx+1], s[idx+1:]
    if is_perfect(u):
        return u + solution(v)
    else:
        return '(' + solution(v) + ')' + reverse(u[1:len(u)-1])