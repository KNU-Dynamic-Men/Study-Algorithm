# 1406 - 에디터

## 1. 개요
https://www.acmicpc.net/problem/1406

## 2. 코드
```java
import java.io.*;
import java.util.LinkedList;
import java.util.ListIterator;

public class Main {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        LinkedList<Character> list = new LinkedList<Character>();

        String input = br.readLine();
        int count = Integer.parseInt(br.readLine());

        for (int i = 0; i < input.length(); i++) {
            list.add(input.charAt(i));
        }

        ListIterator<Character> it = list.listIterator(input.length());

        for (int i = 0; i < count; i++) {
            String comm = br.readLine();

            if(comm.startsWith("L")) {
                if(it.hasPrevious()) {
                    it.previous();
                }
            } else if (comm.startsWith("D")) {
                if(it.hasNext()) {
                    it.next();
                }
            } else if (comm.startsWith("B")) {
                if(it.hasPrevious()) {
                    it.previous();
                    it.remove();
                }
            } else if (comm.startsWith("P")) {
                it.add(comm.charAt(2));
            }
        }

        StringBuilder sb = new StringBuilder();
        for(char t : list) {
            sb.append(t);
        }
        System.out.print(sb.toString());

    }
}
```

## 3. 설명

1. 구현 방법

    - 문자열을 입력하고 제거하는 작업 진행 시 초기엔 배열을 사용하려 하였으나, 중간 문자 삽입시 배열을 밀고 당기는 과정에서 처리 시간이 많이 걸린다는 문제점이 있어 **시간을 가장 적게 소모할 수 있다고 판단한 LinkedList**를 사용하기로 하였다.
    - LinkedList 모듈이 구현되어 있어 **가장 쉽게 사용할 수 있는 JAVA**를 사용하여 풀기로 하였다.
    - ~~사실 Python으로 구현하려다 실패했다~~

2. 주어진 시간 제한이 매우 짧기 때문에 입력을 받을 때 시간이 가장 적게 소모되는 BufferedReader를 사용하여 입력을 받았다.
```java
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
```

3. LinkedList에 문자열을 한 글자씩 저장하였다. 
```java
    for (int i = 0; i < input.length(); i++) {
        list.add(input.charAt(i));
    }
```

4. 일반적인 Iterator인 경우, LinkedList 탐색 시 ```next()```를 이용하여 다음 값으로 넘어갈 수는 있어도 이전 값으로 돌아올 수 없지만, ListIterator의 경우 List 컬렉션의 클래스에서만 사용 가능하다는 제한이 있지만, ```previous()```를 이용하여 이전 값으로 돌아가서 탐색을 진행할 수 있다.    
이를 사용하여 커서 이동, 입력 및 삭제를 구현하였다.

```java
    for (int i = 0; i < count; i++) {
        String comm = br.readLine();

        // L을 입력받은 경우
        if(comm.startsWith("L")) {

            // 왼쪽에 글자가 존재하는 경우 왼쪽으로 이동
            if(it.hasPrevious()) {
                it.previous();
            }
        }
        
        // D를 입력받은 경우
        else if (comm.startsWith("D")) {

            // 마지막 글자가 아닌 경우 오른쪽으로 이동
            if(it.hasNext()) {
                it.next();
            }
        }
        
        // B를 입력받은 경우
        else if (comm.startsWith("B")) {

            // 왼쪽에 글자가 존재하는 경우 왼쪽 글자를 삭제
            if(it.hasPrevious()) {
                it.previous();
                it.remove();
            }
        }
        
        // P를 입력받은 경우
        else if (comm.startsWith("P")) {

            // 입력받은 글자를 LinkedList에 추가
            it.add(comm.charAt(2));
        }
    }
```

5. 출력의 경우, ```System.out.print()```를 자주 호출할 경우 시간 제한에 걸릴 수 있어 **StringBuilder를 통하여 문자열 생성 후 출력**하였다.
```java
    StringBuilder sb = new StringBuilder();
    for(char t : list) {
        sb.append(t);
    }
    System.out.print(sb.toString());
```

## 4. 결과

![image](https://user-images.githubusercontent.com/29600820/86479044-5a82a480-bd86-11ea-9f88-56c73e89cd1c.png)