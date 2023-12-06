# LoaRing - 로아링 프로젝트

---

## 목차

---

**[메인페이지로 이동](README.md)**

* [커밋 메시지 규칙](#1.-커밋-메시지-규칙)
* [GIT 자주 사용하는 명령어](#2-git-자주-사용하는-명령어)
* [GIT 기본 명령어](#3-git-기본-명령어)

## 1. 커밋 메시지 규칙

### 접두사

````text
feat : 새로운 기능 추가
refactor : 코드 리펙토링
test : 테스트 코드, 리펙토링 테스트 코드 추가
fix : 버그 수정
docs : 문서 수정
style : 코드 포맷팅, 세미콜론 누락, 코드 변경이 없는 경우
chore : 빌드 업무 수정, 패키지 매니저 수정
````

### 메시지 작성 예시

```text
feat : Add 메시지 내용 
feat : Modify 메시지 내용 
feat : Remove 메시지 내용 
```

## 2. GIT 자주 사용하는 명령어

### 1. Upstream main (pull 받기)

```shell

# 내 브랜치 위치를 확인한다. 
git branch 

# 1.동기화 upstream sync 
git remote update
git fetch upstream main
 
# 현재 브랜치에 병합 
git merge upstream/main 

# 새로운 기능 생성 
git checkout -b feat/브랜치명

# 내 push origin  
git push origin feat/브랜치명
 
```

### 2. 브랜치 생성

```shell
git checkout -b feat/브랜치명
```

### 3. 브랜치 PR 날리기

- 깃허브로 브랜치 보내기

```shell
git push origin feat/브랜치명
```

- 깃허브에서 PR 날리기

### 4. 브랜치 merge

```shell
# 머지하고 싶은 브랜치로 이동 
git checkout 브랜치명 

# 머지하고 싶은 브랜치를 머지
git merge 브랜치명 
```

## 3. GIT 기본 명령어

- 브랜치 한칸 뒤로 가기

```shell
# --hard, 변경 사항 삭제
# --soft, 변경사항은 유지, staged 상태로 만듦
# --mixed, 변경사항은 유지, unstaged 상태로 만듦

git reset --hard head~1

```

- 브랜치 확인

```shell
# 브랜치 확인 로컬 
git branch -t

# 브랜치 확인 로컬, 리모트 
git branch -a 

# 브랜치 확인 리모트
git branch -r 
```

- 브랜치 생성

```shell
# 브랜치를 생성(작업 중 생성)
git checkout -b feat/브랜치명

# 브랜치를 생성(처음부터 생성)
git branch 브랜치명
```

- 브랜치 삭제

```shell
 
# 로컬에 있는 브랜치 삭제 
# 옵션 -d 한번 물어보고 삭제 
# 옵션 -D 바로 삭제 
git branch -D 브랜치명
 
# 오리진 서버 브랜치 삭제
git push origin -d 브랜치명
```

- 브랜치 변경

```shell
# 현재 브랜치 이름 변경 
git branch -m 브랜치명

# 다른 브랜치 이름 변경
git branch -m feat/브랜치명 feat/바꾸는_브랜치명 
```

- remote 와 동기화 하기

```shell
git remote update
```

- 병합하기

```shell
# ex1) 
git merge 브랜치명

# ex2)
git rebase 브랜치명

# ex3) 
git merge --rebase 브랜치명 
```

