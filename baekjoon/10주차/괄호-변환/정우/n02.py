def fix(u, v):
    s1 = f'({v})'
    s2 = ''
    for s in u[1:-1]:
        if s=='(':
            s2 += ')'
        else:
            s2 += '('
    return s1+s2

def is_valid(u):
    stack = 0
    for i in range(len(u)):
        if u[i]=='(':
            stack += 1
        else:
            stack -= 1
        if stack < 0:
            return False
    return True

def split_uv(w):
    if w == '':
        return w
    stack = 1 if w[0]=='(' else -1
    for i in range(1, len(w)):
        if w[i]=='(':
            stack += 1
        else:
            stack -= 1
        if stack == 0:
            if is_valid(w[:i+1]):
                return w[:i+1] + split_uv(w[i+1:])
            else:
                return fix(w[:i+1], split_uv(w[i+1:]))

def solution(p):
    answer = split_uv(p)
    return answer

print(solution("(()())()")) # result: (()())()
print(solution(")("))       # result: ()
print(solution("()))((()")) # result: ()(())()