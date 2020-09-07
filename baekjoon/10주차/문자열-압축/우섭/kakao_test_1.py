def solution(s):
    if len(s) == 1:
        return 1
    ans = len(s)
    for i in range(1, len(s) // 2 + 1):
        tmp = s[:i]
        count = 1
        st = ''
        for j in range(i, len(s), i):
            if tmp == s[j:j+i]:
                count += 1
            else:
                if count == 1:
                    st += tmp
                else:
                    st += str(count) + tmp
                count = 1
                tmp = s[j:j+i]
        if len(s) % i != 0:
            st += tmp
        else:
            if count > 1:
                st += str(count) + tmp
            else:
                st += tmp
        ans = min(ans, len(st))

    return ans


if __name__ == "__main__":
    print(solution(input()))