### BOJ 1475 방번호

</br>

## 1. 개요

https://www.acmicpc.net/problem/1475

</br>

## 2. 코드

```kotlin
import kotlin.math.max

/**
 * numbers : 입력 문자열
 * plasticNumbers : 플라스틱 번호 0 ~ 9의 필요 개수
 */
fun main() = with(System.`in`.bufferedReader()) {
    // 방 번호 숫자 한 글자씩 저장
    val numbers = readLine()
    var plasticNumbers = Array<Int>(PLASTIC_NUMBER) { 0 }

    val result = getNumOfSet(numbers, plasticNumbers)
    println(result)
}

/**
 * 각 숫자에 해당하는 플라스틱 번호의 필요 개수 증가
 */
fun getNumOfSet(numbers: String, plasticNumbers: Array<Int>): Int {
    numbers.forEach { plasticNumbers[it-'0']++ }

    plasticNumbers[6] += plasticNumbers[9]  // 필요한 9의 개수를 6에 추가

    var maxVal = 0
    for (i in 0..8) {
        if (i == 6) // 6은 9, 9는 6을 대체할 수 있는 특징 때문에 2로 나눈다.
            plasticNumbers[i] = (plasticNumbers[i] + 1) / 2

        maxVal = max(maxVal, plasticNumbers[i]) // 필요 개수가 가장 큰 값이 필요한 세트 수
    }
    return maxVal
}

const val PLASTIC_NUMBER = 10
```

</br>

## 3. 설명

플라스틱 숫자 한 세트는 `0~9`까지의 숫자 `한 개`만을 가지고 있기 때문에 제일 `빈도가 높은 숫자의 개수`만큼 플라스틱 숫자 세트가 필요하게 된다.

단, `6은 9, 9는 6`을 대체할 수 있기 때문에 6이 3개가 필요하다고 해서 3 세트가 필요한 것은 아니다.

`(6과 9의 필요한 총합의 수 + 1) / 2`로 나오는 수가 6과 9를 위해 필요한 `세트 수`가 된다.

각 숫자 중 필요한 개수가 제일 큰 값이 `세트 수`가 된다.

</br>

## 4. 결과

![https://s3-us-west-2.amazonaws.com/secure.notion-static.com/9e909524-60bd-4de3-a7cf-cc6dc662b338/Untitled.png](https://s3-us-west-2.amazonaws.com/secure.notion-static.com/9e909524-60bd-4de3-a7cf-cc6dc662b338/Untitled.png)

</br>

## 5. 회고

처음에는 플라스틱 번호의 `잔여 개수`를 배열로 잡고, 부족하다면 `세트 수를 증가`시킨 후 배열의 모든 원소를 1 증가 시켰다.

그러나, 필요 개수를 구하고, 그것의 최대값을 구하는 것이 더 간단하다는 점을 몰랐다.

</br>

이 문제의 또 하나의 특징은 `6 → 9, 9 → 6`이 되는 점이 결국 `(두 숫자의 필요 개수 합 + 1) / 2`의 몫이 필요한 세트 수란 것이다. (ceil 함수를 사용하여도 된다.)

즉, 한 세트에 6과 9는 0.5개의 비중을 차지한다는 점이다.