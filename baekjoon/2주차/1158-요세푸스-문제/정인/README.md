# 1158 - 요세푸스 문제

## 01 개요
- 문제: [boj/1158 요세푸스 문제](https://www.acmicpc.net/problem/1158)
- 구현: Python3

## 02 코드
```python
import sys

[people_num, kth_removal] = [int(s) for s in sys.stdin.readline().rstrip().split(" ")]
queue_list = list(range(1, people_num + 1))
queue_size = people_num
queue_frontidx = 0
queue_backidx = queue_size - 1

result_str = '<'
for _ in range(people_num - 1):
    for _k in range(kth_removal - 1):
        queue_backidx = (queue_backidx + 1) % queue_size
        queue_list[queue_backidx] = queue_list[queue_frontidx]
        queue_frontidx = (queue_frontidx + 1) % queue_size
    result_str += f'{queue_list[queue_frontidx]}, '
    queue_frontidx = (queue_frontidx + 1) % queue_size
if queue_frontidx != queue_backidx + 1: result_str += str(queue_list[queue_frontidx])
result_str += '>'
print(result_str)
```

## 03 여정
- 각 시도에서는 최대값인 `5000 5000`을 입력으로 해보았다.
  ### 첫번째 시도 `Execution Time: 8.556999206542969`
  - LinkedList로 되지 않을까? → 실패 (LinkedList의 복잡성이 매우 큰 것으로 예상)
  - 왜인지는 모르겠지만 마지막 5번째 시도에서와 0.32초 정도 차이가 나는데 백준에서는 `Execution Timeout` 오류로 실패함
  ### 두번째 시도 `Execution Time: 20.583155393600464` ~~예???~~
  - [Queue를 이용해서 해결했다는 글](https://hyeonnii.tistory.com/156)을 보고 한번 구현해보려고 함 → 실패
  - 어째서인지 실행시간이 첫번째 시도의 약 2.5배로 나온다. 왜죠? → 나중에 세번째 시도로 확인한 결과, 클래스 개념이 들어가는 순간 **굉장히** 느려진다고 판단했음.
  ### 세번째 시도 `Execution Time: 8.236513376235962`
  - 이번에는 두번째 시도에서의 Queue를 클래스를 제거하고 싹다 바꿔서 실행해보자 → 성공! 
  - 어째서 첫번째 시도랑 다른게 있는건지는 잘 모르겠음 (첫번째는 답이 틀린게 아니라 시간 초과라니까 뭐...)

## 04 결과 ~~와, 시도 참 많이 했네~~
![image](https://user-images.githubusercontent.com/5201073/87515437-cafac100-c6b6-11ea-8c65-b1c924ee6e28.png)

