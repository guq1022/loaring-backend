# LoaRing - 로아링 프로젝트

---


**[메인페이지로 이동](README.md)**

### 개발환경

| 분류       | Tool    | 버전        | 용도              |
|----------|---------|-----------|-----------------|
| OS       | 리눅스     | 18.04     | 배포 운영체제         |
| Language | JAVA    | 17        | 프로젝트 자바 버전      |
| Docs     | Swagger |           | 문서 API          |
| DB       | H2      |           | 테스트             |
| DB       | MYSQL   | 8         | 로컬, 운영 영속성 범위   |
| DB       | 몽고DB    | latest       | 로스크아크 API 데이터 저장 |
| DB       | Redis   | 3.0.504   | 로아링 로그인 DB      |
| SCM      | GIT     | none      | 형상관리            |
| Docker   |         |           | 로컬, 개발인프라       |
| AWS      |         | | 인프라 서비스         |
| SERVER   | Tomcat  | 9이상       | 애플리케이션 서버       |

### 사용기술

| 사용기술           | 설명               |
|----------------|------------------|
| JPA            | 도메인 주도 개발        |
| QueryDSL       | 도메인 주도 개발 쿼리 조회  |
| WebClient      | 웹클라이언트, 로아 API 요청 |
| FireBase       | 푸시               |
| SpringSecurity | 로그인 토큰           |
