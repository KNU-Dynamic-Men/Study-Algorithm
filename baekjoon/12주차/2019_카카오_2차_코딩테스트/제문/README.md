## 1. Model

- Elevator
- Call
- Command

## 2. Http Request

- HttpUrlConnection

## 3. JSON Parshing

- Gson

## 4. 해결 과정

- FIFO 방식으로 엘리베이터에게 Call을 배정한 후, 해당 Call을 태운 후 목표 지점으로 가는 도중에 `태울 수 있는 모든 승객`을 태운다.
- 여기서 `태울 수 있는 모든 승객`이란, 해당 Call의 시작 지점과 목표 지점 사이에 **Enter**와 **Exit**가 모두 이루어지는 승객을 이야기 한다.