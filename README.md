# LoaRing - 로아링 프로젝트

---

## 개발관점

- 도메인 테스트 관점 `작은 단위 테스트`로 작성.
- 서비스 테스트 관점 프로세스간의 `행위 테스트` 작성.
- API 테스트 관점 `API의 작성 유무 및 문서화` 작성.


# GIT 작업하기
```shell

# 1.동기화 upstream sync 
git remote update
git fetch upstream main

# 내 브랜치 위치를 확인한다. 

# 내가 원하는 위치가 아닌경우 브랜치 이동 (선택) 
git checkout 브랜치명 

# 내 브랜치에 병합  -> (main)
git merge upstream/main
   
# 새로운 기능 생성 
git checkout -b feat/브랜치명

# 만약 동기화 안 했으면  "1.동기화 upstream sync" 작업을 수행
git commit -am "커밋명"

# 내 push origin  
git push origin feat/브랜치명
 
```

## 머지가 된 경우 
```shell
git remote update
git fetch upstream main

# 내가 머지하고 싶은 브랜치 
git merge upstream/main    
```





## 유의사항

## 커밋 메시지 규칙
````text
feat : 새로운 기능 추가
ex) 
feat : Add ~
feat : Modify ~
feat : Remove ~

refactor : 코드 리펙토링
test : 테스트 코드, 리펙토링 테스트 코드 추가
fix : 버그 수정

docs : 문서 수정
style : 코드 포맷팅, 세미콜론 누락, 코드 변경이 없는 경우
chore : 빌드 업무 수정, 패키지 매니저 수정
````

- config 변경시 `branch`를 생성하여 별도 PR로 작성

```shell
# 1. 수정사항발생시
#  
git checkout -b feature/config-수정명
or 
git branch feature/config-수정명

# 2. 개발 완료 후 PR 날리기  
git push origin feature/config-수정명

# 3. github 에서 pr 작성 
```

##

# 2.명명규칙

## URL 명명규칙

- URL은 복수형으로 작성

```text
    api/products
```

- 요구사항의 기능은 `HttpMethod` 으로만 해결
    - 코드 작성시 `HttpMethod` 해결 되지 않는다면, 같이 논의 또는 혼자 가능시 혼자 개발

##     

## 예약어

### find

- DB 에서데이터를 찾은 경우
- 또는 데이터를 찾는 경우

```javascript

var findProduct = ProductRepository.findAll();

```

### saved* or save*

```javascript

var saveProduct = ProductRequest.toEntity();
var savedProduct = ProductRepository.save(saveProduct)

```

# 3.에러정의

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