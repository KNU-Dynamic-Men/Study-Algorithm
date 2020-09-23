def solution(gems):
    start, end = 0, 0
    answer = [0, len(gems)]
    gem_dict = {gems[0]: 1}
    gem_count = len(set(gems))

    while start < len(gems) and end < len(gems):
        if len(gem_dict) < gem_count:
            if end == len(gems) - 1:
                break
            end += 1
            if gem_dict.get(gems[end]) is None:
                gem_dict[gems[end]] = 1
            else:
                gem_dict[gems[end]] += 1
        else:
            if end - start < answer[1] - answer[0]:
                answer = [start + 1, end + 1]
            if gem_dict[gems[start]] == 1:
                del gem_dict[gems[start]]
            else:
                gem_dict[gems[start]] -= 1
            start += 1

    return answer

if __name__ == "__main__":
    print(solution(["DIA", "RUBY", "RUBY", "DIA", "DIA", "EMERALD", "SAPPHIRE", "DIA"]))