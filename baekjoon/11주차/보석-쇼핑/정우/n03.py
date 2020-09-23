from collections import defaultdict

def solution(gems):
    gem_set = set(gems)
    gem_num = len(gem_set)
    dic = defaultdict(int)
    l_idx, r_idx = 0, 0
    cand_list = []
    flag = True
    while l_idx < len(gems) and r_idx < len(gems):
        if flag == False and gems[l_idx-1] in dic.keys():
            dic[gems[l_idx-1]] -= 1
        elif flag == True:
            dic[gems[r_idx]] += 1

        if dic[gems[l_idx-1]] == 0:
            del dic[gems[l_idx-1]]

        if len(dic) == gem_num:
            cand_list.append([l_idx, r_idx])
            l_idx += 1
            flag = False
        else:
            r_idx += 1
            flag = True

    cand_list = sorted(cand_list, key=lambda x: (x[1]-x[0], x[0]))

    return [cand_list[0][0]+1, cand_list[0][1]+1]
    
print(solution(
    ["DIA", "RUBY", "RUBY", "DIA", "DIA", "EMERALD", "SAPPHIRE", "DIA"], # gems
)) # result: [3, 7]

print(solution(
    ["AA", "AB", "AC", "AA", "AC"], # gems
)) # result: [1, 3]

print(solution(
    ["XYZ", "XYZ", "XYZ"], # gems
)) # result: [1, 1]

print(solution(
    ["ZZZ", "YYY", "NNNN", "YYY", "BBB"], # gems
)) # result: [1, 5]
