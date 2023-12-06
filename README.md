# LoaRing - 로아링 프로젝트

---

## 목차

---

1. [개발 환경](DevelopmentAOP.md)
2. [개발관점](#개발관점)
3. [개발가이드](#개발-가이드)
4. [예약어](#예약어)

5. 샘플코드
    1. [Controller 샘플](#Controller-샘플)
    2. [Application 샘플](#Application-샘플)
    2. [Entity 샘플](#Entity-샘플)
    4. [VO(Embeddable) 샘플](#voembeddable-샘플)

6. 가이드
    * [GIT](GIT.md)
    * [Docker](Docker.md)

7. [에러정의](ErrorDefinition.md)

## 개발관점

- 도메인 테스트 관점 `작은 단위 테스트`로 작성.
- 서비스 테스트 관점 프로세스간의 `행위 테스트` 작성.
- API 테스트 관점 `API의 작성 유무 및 문서화` 작성.

# 개발 가이드

## 패키지 정의

| 패키지명        | 패키지용도                                   |
|-------------|-----------------------------------------|
| config      | 패키지 범위의 설정                              |
| controller  | API 요청 영역                               |
| dto         | DTO 정의                                  |
| application | 서비스 조합                                  |
| domain      | 도메인 정의(Entity, DomainService, VO, enum) |
| infra       | 인프라스트럭처                                 |
| exception   | 패키지 영역 예외                               |

## 1. Controller 정의

Header의 정의는 API 요청의 명세서를 의미하기도합니다.

- Header 정의
   ```
    header :  {
        content-type : "application/json"
        access : "application/json"
        Authentication : "Bearer 토큰"
    }

### API 설계

슬래시 구분자(/)는 계층 관계를 나타내는 데 사용

```yaml
http://restapi.example.com/study/java                (o)
http://restapi.example.com/lostark/characters/weapon (o)
```

URI 마지막 문자로 슬래시(/)를 포함하지 않는다.

```text
http://restapi.example.com/lostark/characters/weapon/ (x)
http://restapi.example.com/lostark/characters/weapon  (o)
```

하이픈(-)은 URI 가독성을 높이는데 사용
밑줄(_)은 URI에 사용하지 않는다.

```text
http://restapi.example.com/lostArk/characters/weapon   (x)
http://restapi.example.com/lost_ark/characters/weapon  (x)
http://restapi.example.com/lost-ark/characters/weapon  (o)
```

리소스 간의 관계를 표현할 때

```text
http://restapi.example.com/characters/{userid}/equipments (o)
```

### HTTP METHOD 표 [참고](https://sanghaklee.tistory.com/61)

일반적인 API 요청은 응답은 `200` 을 사용합니다. 그러나 restfulApi 설계시 각각 메소드에 따라 응답 값을 다르게 설정해 주어야합니다. </br>
그래서 아래와 같은 표를 참고 하여 Controller를 설계 합니다.

| 기능  | METHOD | 요청 annotation  | 응답시 자바 코드                    | 응답코드 |
|-----|--------|----------------|------------------------------|------|
| 조회  | GET    | @GetMapping    | ResponseEntity.ok(데이터);      | 200  |
| 생성  | POST   | @PostMapping   | ResponseEntity.created(데이터); | 201  |
| 수정  | PATCH  | @PatchMapping  | ResponseEntity.ok(데이터);      | 200  |
| 삭제  | DELETE | @DeleteMapping | ResponseEntity.noContent();  | 204  |

## 2. [Service 정의](#application-샘플)

Spring에서 제공하는 hibernate 를 사용하는 경우 Application에서 @Transaction 을 반드시 명시해야 사용할 수 있습니다. </br>
이러한 이유 때문에 `@Transactional`을 클래스 단위 메소드 단위로 정의해서 사용해야합니다.

- 조회 (#application-샘플)
    - @Transactional(readOnly = true)

- 추가, 수정 삭제
    - @Transactional

## 예약어

| 단어         |     | 용도                         |
|------------|-----|----------------------------|
| Base       | 접두사 | 프록제트의 기본 셋팅 및 유틸           |
| Controller | 접미사 | API Controller 클래스명        |
| Service    | 접미사 | Application 서비스명           |
| Query      | 접미사 | Application 조회만 사용         |
| Commend    | 접미사 | Application 생성, 수정, 삭제만 사용 |
| Config     | 접미사 | 프로젝트 셋팅, 유틸 셋팅             |
| Request    | 접미사 | Controller 에서 요청           |
| Response   | 접미사 | Controller 에서 응답           |
| Repository | 접미사 | 엔티티 Repository             |
| Client     | 접미사 | 외부 요청을 위한 API              |
| Q          | 접두사 | querydsl을 사용하는 경우          |
| Validation | 접미사 | Controller 단계에서 값 검증       |
| match      | 접두사 | equals 외 비교하는 용도           |
| search     | 접두사 | 검색                         |
| find       | 접두사 | 조회                         |
| get        | 접두사 | 조회                         |
| save       | 접두사 | 생성                         |
| create     | 접두사 | 생성                         |
| add        | 접두사 | 추가                         |
| modify     | 접두사 | 수정                         |
| change     | 접두사 | 수정                         |
| remove     | 접두사 | 삭제                         |
| is         | 접두사 | 조건이나 상태를 확인                |
| verify     | 접두사 | 유효성을 검증                    |
| check      | 접두사 | 어떠한 값 또는 로직을 체크            |

## Controller 샘플

```java

@RestController
@RequestMapping("/api/products")
@RequiredArgsConstructor
public class productServiceController {

	private final ProductService productService;

	@GetMapping
	public ResponseEntity<BaseResponse> getProducts(
		// validation 검증
		@Valid AdventurerEmailRequest adventurerEmailRequest) {

		// ...생략 아래 코드는 변경 될 수 있음
		var baseResponse = BaseResponse.builder()
			.headers()
			.data(AdventurerResponse.builder()
				.email(adventurer.getEmail())
				.password(null)
				.account(adventurer.getAccount())
				.build())
			.build();
		// ...생략

		// 조회 : 응답 200
		return ResponseEntity.ok(baseResponse);
	}

	@PostMapping
	public ResponseEntity<BaseResponse> createProduct(
		// validation 검증
		@RequestBody @Valid ProductSaveRequest productSaveRequest) {

		// ...생략

		// 저장 : 응답 201
		return ResponseEntity.created(baseResponse);
	}

	@PatchMapping("/{id}")
	public ResponseEntity<BaseResponse> modifyPrice(@PathVariable("id") Long id,
		// validation 검증
		@Valid ProductPriceRequest productPriceRequest) {

		// ...생략

		// 수정 : 응답 200
		return ResponseEntity.ok(baseResponse);
	}

	@DeleteMapiing("/{id}")
	public ResponseEntity<BaseResponse> removeProduct(
		// validation 검증
		@PathVariable("id") Long id, @Valid ProductSaveRequest productSaveRequest) {

		// ...생략

		// 삭제 : 응답 204
		return ResponseEntity.noContent(baseResponse);
	}


}

```

## Application 샘플

```java

@Service
@RequiredArgsConstructor
public class ProductService {

	private final ProductRepository productRepository;

	// 다건 페이징 조회
	public Page<Product> getProducts(Pageable pageable) {

		return productRepository.findAll(pageable);
	}

	// 다건 리스트
	@Transactional(readOnly = true)
	public List<Product> getProducts(SearchProduct searchProduct) {
		return productRepository.findAll(searchProduct);
	}

	// 다건 리스트
	@Transactional(readOnly = true)
	public List<Product> getProducts() {
		return productRepository.findAll();
	}

	// 단건 조회
	@Transactional(readOnly = true)
	public Product getProduct() {

		// ex) ID 조회
		var findProduct = productRepository.findById("1L");

		// ex) 필드 조회
		var findProduct = productRepository.findByEmail("test@gamil.com");

		return findProduct;
	}

	// 저장
	@Transactional
	public Product save() {
		return productRepository.save(saveProduct);
	}

	// 가격 수정
	@Transactional
	public Product modifyPrice() {
		var findById = productRepository.findById("1L");
		findById.modifyPrice("1000");
	}

	// 삭제
	@Transactional
	public void removeProduct() {

		var findProduct = productRepository.findById("1L")
			.orElseThrow(() -> new BaseEntityNotFoundException("상품"));

		productRepository.delete(findProduct);
	}

}
```

## Entity 샘플

- 모든 Entity는 BaseEntity를 상속해야한다.
- PK 같은 경우 UUID 또는 대체키 사용
- Entity 생성시 Aggeregate 를 기점으로 Repository 한개 만을 생성한다
    - Repository Entity 별로 생성하지 해야한다면 JPA의 `영속성 전이`에 대해서 공부 후 사용

```java

@Entity
@Builder
@Getter
@EqualsAndHashCode(of = "id")
@AllArgsConstructor(access = PRIVATE)
@NoArgsConstructor(access = PROTECTED)
public class 엔티티명 extends BaseEntity {

	// ex1) 
	@Id
	@GeneratedValue(strategy = IDENTITY)
	private Long id;

	// ex2) 
	@Id
	@UuidGenerator
	private UUID id;

	// ex3) 커스텀 id 필요시 문의 !

}


// Repository
public interface AdventurerRepository extends JpaRepository<Adventurer, Long> {

}
```

## VO(Embeddable) 샘플

Vo형식은 반드시 Embeddable을 이용하여 작성하지 않아도 된다.

- VO를 생성시 Embeddable 방식 구현
- 일반 필드로 구현

```java

//.... 생략
public class 엔티티명 {

	// ex1)
	private String name;

	// ex2) 
	@Embedded
	private Name name;

}


@Getter
@Embeddable /* 중요 */
@EqualsAndHashCode /* 중요 */
@AllArgsConstructor(access = PRIVATE)
@NoArgsConstructor(access = PROTECTED)
public class Name {

	private String value;
}


```