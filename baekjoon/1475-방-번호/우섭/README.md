# 1475 - 방 번호

## 1. 개요
https://www.acmicpc.net/problem/1475

## 2. 코드
```python
import math

table = [0 ,0, 0, 0, 0, 0, 0, 0, 0]
value = list(map(int, str(input())))

for i in value:
    if i == 6 or i == 9:
        table[6] += 0.5
    else:
        table[i] += 1

table[6] = math.ceil(table[6])
print(max(table))
```

## 3. 설명

1. 한 세트 안에 0~9의 숫자가 존재한다.

2. 6과 9는 동일한 숫자로 판단한다.    
다만 한 세트 안에는 6과 9가 같이 들어가 있으므로 한 세트에는 6이 2개 들어가 있는 것으로 보고 0.5개를 더한다.
```python
for i in value:
    if i == 6 or i == 9:    # 6과 9가 입력되는 경우
        table[6] += 0.5
    else:                   # 그 외의 경우
        table[i] += 1
```

3. 0.5의 경우, 세트를 반만 사용하지 않으므로 반올림하여 계산한다.    
round 함수의 경우, **ROUND_HALF_EVEN**, 즉 **반올림할 때 짝수에 묶는 방식**으로 작동하므로 정상적으로 작동하지 않을 수 있다. 따라서 math 모듈의 ceil 함수를 통해 올림 처리를 하였다.
```python
table[6] = math.ceil(table[6])
```

4. 사용해야 하는 세트의 수는 가장 많이 사용하는 숫자를 전부 포함해야 하므로 **최대값**을 찾아 출력한다.
```python
print(max(table))
```

## 4. 결과

![주석 2020-07-02 143104](https://user-images.githubusercontent.com/29600820/86320019-cecb2400-bc70-11ea-9a69-5a0061df4b12.png)