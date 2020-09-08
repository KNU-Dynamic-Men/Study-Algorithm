# **2020 카카오 블라인드 1차 1번 - 문자열 압축**

## **1. 개요**

[https://programmers.co.kr/learn/courses/30/lessons/60057](https://programmers.co.kr/learn/courses/30/lessons/60057)

## **2. 코드**

Python3

```python
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
```

## **3. 설명**

1. 앞에서부터 i개의 글자씩 잘라 캐쉬에 저장한다.
2. 뒤에 자른 i개의 글자가 저장한 캐쉬와 같은지 비교한다.
    1. 같다면 count를 증가시킨 다음, 다음의 i개의 글자를 비교한다.
    2. 같지 않다면 count값과 캐쉬의 내용을 정답 문자열에 붙인다.
3. 1-2를 반복한다.
