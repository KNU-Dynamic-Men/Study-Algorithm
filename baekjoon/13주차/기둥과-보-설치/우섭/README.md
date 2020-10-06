# 카카오 2020년 상반기 1차 - 기둥과 보 설치

## 1. 개요

https://programmers.co.kr/learn/courses/30/lessons/60061

## 2. 코드
```python
def check(building):
    for x, y, floor in building:
        if not floor:
            if y \
                    and [x, y, 1] not in building \
                    and [x - 1, y, 1] not in building \
                    and [x, y - 1, 0] not in building:
                return False
        else:
            if [x, y - 1, 0] not in building \
                    and [x + 1, y - 1, 0] not in building \
                    and not ([x - 1, y, 1] in building and [x + 1, y, 1] in building):
                return False
    return True


def solution(n, build_frame):
    from collections import deque
    answer = deque()
    for x, y, a, b in build_frame:
        value = [x, y, a]
        if b:
            answer.appendleft(value)
            if not check(answer):
                answer.popleft()
        elif value in answer:
            answer.remove(value)
            if not check(answer):
                answer.appendleft(value)
    answer = map(list, answer)
    return sorted(answer, key=lambda x: (x[0], x[1], x[2]))
```

## 3. 설명

1. 구현 방법 구상

    - 시뮬레이션 문제
    - 처음에는 좌표에 직접 그래프를 그리면서 진행
    - 코드 작성하다 보니 점점 좌표 계산에서 꼬이고...
    - 결국 코드 완성도 못하고 리셋
    - [블로그](https://velog.io/@tjdud0123/%EA%B8%B0%EB%91%A5%EA%B3%BC-%EB%B3%B4-%EC%84%A4%EC%B9%98-2020-%EC%B9%B4%EC%B9%B4%EC%98%A4-%EA%B3%B5%EC%B1%84-python)를 참고하여 풀이 진행

2. 구현

    - 먼저 기둥 또는 보를 설치하거나 삭제할 수 있는지 체크하는 함수를 구현한다
    - 먼저 해당 기둥이나 보를 **먼저** 설치하거나 삭제한 후, 해당 건물이 조건에 맞는지 체크한다
    - 만약 해당 건물이 주어진 조건에 맞다면 그대로 두지만, 조건에 맞지 않다면 롤백시킨다
    ```python
    def check(building):
    ```
    - 현재 만들어진 건물의 배열을 인자로 받는 함수를 만든다
    ```python
    for x, y, floor in building:
    ```
    - `for`문을 실행한다
    - `x`: 기둥 또는 보가 설치된 `x`좌표
    - `y`: 기둥 또는 보가 설치된 `y`좌표
    - `floor`: 기둥이면 `0`, 보면 `1`
    ```python
    if not floor:
        if y \
                and [x, y, 1] not in building \
                and [x - 1, y, 1] not in building \
                and [x, y - 1, 0] not in building:
            return False
    ```
    - 기둥을 체크한다
    - 문제의 조건인 `기둥은 바닥 위에 있거나 보의 한쪽 끝 부분 위에 있거나, 또는 다른 기둥 위에 있어야 합니다.`를 확인한다
    - 해당 `if`문을 모두 충족하게 되면 해당 건물은 조건에 맞지 않다는 의미이므로 `False`를 반환한다
    - `y`: 해당 좌표가 **바닥이 아니다**
    - `[x, y, 1] not in building and [x - 1, y, 1] not in building`: 해당 좌표가 **보의 한쪽 끝이 아니다**
    - `[x, y - 1, 0] not in building`: 해당 좌표가 **다른 기둥 위가 아니다**
    ```python
    else:
        if [x, y - 1, 0] not in building \
                and [x + 1, y - 1, 0] not in building \
                and not ([x - 1, y, 1] in building and [x + 1, y, 1] in building):
            return False
    ```
    - 보를 체크한다
    - 문제의 조건인 `보는 한쪽 끝 부분이 기둥 위에 있거나, 또는 양쪽 끝 부분이 다른 보와 동시에 연결되어 있어야 합니다.`를 확인한다
    - 해당 `if`문을 모두 충족하게 되면 해당 건물은 조건에 맞지 않다는 의미이므로 `False`를 반환한다
    - `[x, y - 1, 0] not in building and [x + 1, y - 1, 0] not in building`: 해당 좌표가 **한쪽 끝 부분이 기둥 위가 아니다**
    - `not ([x - 1, y, 1] in building and [x + 1, y, 1] in building)`: 해당 좌표가 **양쪽 끝 부분이 다른 보와 동시에 연결되어 있지 않았다**
    ```python
    answer = deque()
    for x, y, a, b in build_frame:
        value = [x, y, a]
        if b:
            answer.appendleft(value)
            if not check(answer):
                answer.popleft()
        elif value in answer:
            answer.remove(value)
            if not check(answer):
                answer.appendleft(value)
    ```
    - `solution` 함수는 문제에 주어진 대로 작성한다
    - deque 배열을 선언한다 ~~`popleft`등등 사용해서 조금이라도 시간복잡도 줄이려고 했는데 잘된거인지는 모르겠다~~
    - `for`문을 사용하여 `build_frame`을 하나씩 읽는다
    - `answer` 배열에 추가할 `value` 변수를 만든다
    - 추가인 경우 `appendleft`를 사용하여 `answer`에 추가하여 주고, `check`함수를 이용하여 확인 시 조건에 충족되지 않은 배열이 되는 경우 해당 추가한 값을 제거한다
    - 삭제인 경우 먼저 해당 값이 `answer`배열에 있는지 확인하고, 있는 경우 먼저 해당 값을 제거한 후, `check`함수를 이용하여 확인 시 조건에 충족되지 않은 배열이 되는 경우 해당 삭제한 값을 다시 추가한다
## 4. 결과

- 정확성 테스트
    ```
    테스트 1 〉	통과 (0.08ms, 10.3MB)
    테스트 2 〉	통과 (0.14ms, 10.3MB)
    테스트 3 〉	통과 (0.10ms, 10.3MB)
    테스트 4 〉	통과 (0.37ms, 10.3MB)
    테스트 5 〉	통과 (0.28ms, 10.2MB)
    테스트 6 〉	통과 (2.18ms, 10.3MB)
    테스트 7 〉	통과 (0.03ms, 10.3MB)
    테스트 8 〉	통과 (0.09ms, 10.2MB)
    테스트 9 〉	통과 (0.13ms, 10.3MB)
    테스트 10 〉	통과 (379.30ms, 10.5MB)
    테스트 11 〉	통과 (2209.99ms, 10.6MB)
    테스트 12 〉	통과 (298.38ms, 10.4MB)
    테스트 13 〉	통과 (2466.57ms, 10.6MB)
    테스트 14 〉	통과 (242.62ms, 10.4MB)
    테스트 15 〉	통과 (2300.04ms, 10.6MB)
    테스트 16 〉	통과 (255.03ms, 10.5MB)
    테스트 17 〉	통과 (1953.61ms, 10.7MB)
    테스트 18 〉	통과 (1388.73ms, 10.6MB)
    테스트 19 〉	통과 (1413.24ms, 10.5MB)
    테스트 20 〉	통과 (1379.89ms, 10.5MB)
    테스트 21 〉	통과 (1785.45ms, 10.5MB)
    테스트 22 〉	통과 (1384.79ms, 10.4MB)
    테스트 23 〉	통과 (1737.74ms, 10.6MB)
    ```