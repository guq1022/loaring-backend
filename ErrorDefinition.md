# LoaRing - 로아링 프로젝트

---

**[메인페이지로 이동](README.md)**

# 1.에러정의

## 1-1. Global Error

- Global Error는 global을 접두어로 사용한다.
- Global Error는 errorCode: errorMessage 형식으로 작성

### 예시

```properties
# properties
global.overMaxTotalPrice : 상품 가격 * 수량의 최대값은 500,000,000 입니다! 현재 값은 {1} 입니다!
```

## 1-2. Field Error

- Field Error에는 우선순위가 존재함
- Field Error 우선 순위 (아래로 갈수록 우선순위가 낮아짐)

### 예시

```properties
# properties
NotBlank                  : 공백일 수 없습니다!
NotBlank.java.lang.String : 빈 문자열 일 수 없습니다!
NotBlank.addItemForm.name : 상품명은 필수입니다!

NotEmpty                  : 값은 null or "" 일 수 없습니다.
NotEmpty.java.lang.String : 빈 문자열 일 수 없습니다!
ValidEnum                 : 존재하지 않는 타입입니다.
```

위와 같이 코드를 작성했다면

- addItemForm의 name이 빈 칸이라면 "상품명은 필수입니다!"가 출력됨
- addItemForm이 아닌 다른 객체의 name이 빈 칸이라면 "이름은 필수입니다!"가 출력됨
- addItemFrom의 email이 빈 칸이라면 String이기 때문에 "빈 문자열 일 수 없습니다!"가 출력됨
- String 타입도 아닌 필드가 빈 칸이라면 "공백일 수 없습니다!"가 출력됨

## 1-3. 필드명, parameter 출력

- {0} : 필드명, {1}, {2}, ..은 파라미터를 의미

### 예시

``` java
// java
@Range(min = 100, max = 1000000);
private Long price;
```

### properties

```properties
# properties
Range.item.price : { 0 }는 {2}원 ~ {1}원 사이 값이여야 합니다!"
```

### 결과

```text
price는 100원 ~ 1,000,000원 사이 값이여야 합니다!
```

## 1-4. Type Error

- Type Error는 typeMismatch 사용
- TypeError 우선 순위는 아래로 갈수록 우선순위가 낮음

```properties
# properties
typeMismatch                   : 입력타입이 잘못되었습니다.
typeMismatch.java.lang.Integer : 숫자가 입력되야 합니다.
typeMismatch.java.lang.String  : 문자가 입력되야 합니다.
```

price, quantity 필드에 문자를 넣었다면 price는 "가격은 숫자여야 합니다!"을 출력하고 quantity는 "숫자가 입력되야 합니다!"을 출력함
이 특성들을 이용해 에러 메세지를 세분화 할 수 있고, 범용성 있게 쓸 수도 있음