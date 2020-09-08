def solution(s):
    answer = len(s)
    for i in range(1, len(s)//2+1):
        part_answer = ""
        cache = s[0:i]
        cache_cnt = 1
        j = i
        while j+i <= len(s):
            tmp = s[j:j+i]
            if tmp == cache:
                cache_cnt += 1
            else:
                if cache_cnt > 1:
                    part_answer += f"{cache_cnt}{cache}"
                else:
                    part_answer += cache
                cache = tmp
                cache_cnt = 1
            j += i

        if cache_cnt > 1:
            part_answer += f"{cache_cnt}{cache}"
        else:
            part_answer += cache

        if j+i > len(s):
            part_answer += s[j:]

        if len(part_answer) < answer:
            answer = len(part_answer)

    return answer

print(solution("aabbaccc"))                 # result: 7
print(solution("ababcdcdababcdcd"))         # result: 9
print(solution("abcabcdede"))               # result: 8
print(solution("abcabcabcabcdededededede")) # result: 14
print(solution("xababcdcdababcdcd"))        # result: 17