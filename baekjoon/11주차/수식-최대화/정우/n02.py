import itertools, re

def isdigit(str):
    try:
        tmp = float(str)
        return True
    except ValueError:
        return False

def cal(expression, target_oper):
    idx = 0
    ret = []
    for i, e in enumerate(expression):
        if e == target_oper:
            if i == idx:
                ret.append(str(eval(f'{ret.pop()}{e}{expression[i+1]}')))
            else:
                ret.append(str(eval(f'{expression[i-1]}{e}{expression[i+1]}')))
            idx = i+2
        elif not isdigit(e):
            for tmp in expression[idx:i+1]:
                ret.append(tmp)
            idx = i+1
    for tmp in expression[idx:]:
        ret.append(tmp)
    return ret


def solution(expression):
    oper_list = []
    if expression.find('+') != -1:
        oper_list.append('+')
    if expression.find('-') != -1:
        oper_list.append('-')
    if expression.find('*') != -1:
        oper_list.append('*')
    
    ex_list = []
    idx = 0
    for i, e in enumerate(expression):
        if not isdigit(e):
            ex_list.append(expression[idx:i])
            ex_list.append(expression[i:i+1])
            idx = i+1
    ex_list.append(expression[idx:])

    maxi = 0
    case_list = list(itertools.permutations(oper_list, len(oper_list)))
    for case in case_list:
        res = ex_list
        for oper in case:
            res = cal(res, oper)
        maxi = maxi if maxi > abs(int(res[0])) else abs(int(res[0]))

    return maxi
    
print(solution(
    "100-200*300-500+20", # expression
)) # result: 60420

print(solution(
    "50*6-3*2", # expression
)) # result: 300
