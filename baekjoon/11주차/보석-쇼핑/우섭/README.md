# 카카오 2020 인턴십 - 보석 쇼핑

## 1. 개요

https://programmers.co.kr/learn/courses/30/lessons/67258

## 2. 코드

```python
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
```

## 3. 설명

1. 구현 방법 구상

    - 완전탐색을 하게 되면 답은 구할 수 있겠지만 시간이 매우 오래 걸리게 된다
    - 입력 데이터의 수는 최대 `100000`
    - [**투 포인터**](https://blog.naver.com/kks227/220795165570?viewType=pc) 알고리즘을 이용하여 풀이
    ```python
    start, end = 0, 0
    answer = [0, len(gems)]
    gem_dict = {gems[0]: 1}
    gem_count = len(set(gems))
    ```
    - `start, end`: 앞, 뒤 포인터의 인덱스
    - `answer`: 정답이 저장된 배열
    - `gem_dict`: 보석의 갯수가 저장되어 있는 `dict`
    - `gem_count`: 모든 보석의 수, 중복을 허용하지 않는 `set`을 사용하여 구한다
    ```python
    while start < len(gems) and end < len(gems):
    ```
    - `start`와 `end` 포인터가 보석 배열의 길이를 벗어나지 않는 동안 계속 탐색한다
    ```python
    if len(gem_dict) < gem_count:
        if end == len(gems) - 1:
            break
        end += 1
        if gem_dict.get(gems[end]) is None:
            gem_dict[gems[end]] = 1
        else:
            gem_dict[gems[end]] += 1
    ```
    - `gem_dict`의 수가 `gem_count`보다 작은 경우 **현재 `start`와 `end` 사이에 모든 보석이 들어있지 않다**는 의미이므로 `end` 포인터를 오른쪽으로 움직이면서 탐색한다
    - `end`의 값이 보석 배열의 크기와 같아진 경우 탐색을 종료한다
    - `gem_dict`에 현재 보석이 존재하지 않는 경우 추가하여 주고, 이미 있는 경우 개수를 증가시켜 준다
    ```python
    else:
        if end - start < answer[1] - answer[0]:
            answer = [start + 1, end + 1]
        if gem_dict[gems[start]] == 1:
            del gem_dict[gems[start]]
        else:
            gem_dict[gems[start]] -= 1
        start += 1
    ```
    - `gem_dict`의 수가 `gem_count`와 같은 경우 **현재 `start`와 `end` 사이에 모든 보석이 한 개 이상 있다**는 의미이므로 `start` 포인터를 오른쪽으로 움직이면서 탐색한다
    - 현재 포인터 간의 거리가 이전에 저장된 포인터 간의 거리보다 작은 경우 현재 포인터의 인덱스를 저장하여 준다
    - 현재 `start` 포인터가 가리키는 보석의 개수가 1개인 경우 `gem_dict`에서 해당 보석을 제거한다
    - 그 외인 경우 해당 보석의 수를 1 감소시킨다
    - `start`를 1 증가시켜 다음 보석을 탐색하거나, `end` 인덱스를 다시 탐색하도록 한다

## 4. 결과

- 정확성 테스트
    ```
    테스트 1 〉	통과 (0.03ms, 10.3MB)
    테스트 2 〉	통과 (0.13ms, 10.2MB)
    테스트 3 〉	통과 (0.42ms, 10.2MB)
    테스트 4 〉	통과 (0.38ms, 10.3MB)
    테스트 5 〉	통과 (0.65ms, 10.2MB)
    테스트 6 〉	통과 (0.01ms, 10.1MB)
    테스트 7 〉	통과 (0.03ms, 10.3MB)
    테스트 8 〉	통과 (0.87ms, 10.2MB)
    테스트 9 〉	통과 (1.24ms, 10.3MB)
    테스트 10 〉	통과 (0.86ms, 10.2MB)
    테스트 11 〉	통과 (1.14ms, 10.3MB)
    테스트 12 〉	통과 (1.74ms, 10.3MB)
    테스트 13 〉	통과 (2.25ms, 10.3MB)
    테스트 14 〉	통과 (2.23ms, 10.5MB)
    테스트 15 〉	통과 (4.16ms, 10.6MB)
    ```
- 효율성 테스트
    ```
    테스트 1 〉	통과 (7.51ms, 10.6MB)
    테스트 2 〉	통과 (9.64ms, 10.6MB)
    테스트 3 〉	통과 (15.30ms, 11.2MB)
    테스트 4 〉	통과 (11.71ms, 11.9MB)
    테스트 5 〉	통과 (23.35ms, 11.9MB)
    테스트 6 〉	통과 (30.04ms, 12.2MB)
    테스트 7 〉	통과 (36.68ms, 12.6MB)
    테스트 8 〉	통과 (38.43ms, 12.9MB)
    테스트 9 〉	통과 (45.15ms, 13.4MB)
    테스트 10 〉	통과 (50.75ms, 13.7MB)
    테스트 11 〉	통과 (61.37ms, 14.6MB)
    테스트 12 〉	통과 (39.55ms, 15.4MB)
    테스트 13 〉	통과 (56.24ms, 16.3MB)
    테스트 14 〉	통과 (93.85ms, 17.2MB)
    테스트 15 〉	통과 (95.49ms, 17.9MB)
    ```
![image](https://user-images.githubusercontent.com/29600820/93982960-e96af000-fdbc-11ea-8c2e-7d2bcf0282ec.png)