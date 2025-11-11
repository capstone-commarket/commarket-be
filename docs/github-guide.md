# Git & GitHub 협업 가이드

본 문서는 팀 프로젝트에서 Git과 GitHub를 통해 협업할 때 사용하는 기본 규칙과 흐름을 정의한다.  
목표는 **충돌 최소화**, **작업 흐름 명확화**, **안정적인 코드 관리**이다.

---

## 1. 저장소(Repository) 기본 규칙

- 모든 개발 작업은 **GitHub 저장소를 기준**으로 진행한다.
- 로컬 변경 사항은 반드시 **Commit → Push → Pull Request(PR)** 절차를 거친 후 병합한다.
- `main` 브랜치는 항상 **배포 가능 상태**를 유지한다.  
  → 즉, main에는 **테스트 통과 + 정상 동작하는 코드만** 합쳐진다.

---

## 2. 사전 준비

### GitHub 저장소 Clone 하기

1) GitHub에서 프로젝트 Repository URL 복사  
2) 로컬 작업 폴더로 이동 후 아래 명령어 실행

```bash
git clone <저장소-URL>
예시:

bash
코드 복사
git clone https://github.com/team-qchecker/backend.git
```

## 3. 브랜치 전략 (Branch Strategy)

### 브랜치 구조
main # 최종 안정 코드
develop # 다음 릴리즈 준비용 (optional, 사용 시)
feature/* # 각 기능 개발 브랜치
fix/* # 버그 또는 오류 수정 브랜치


### 브랜치 네이밍 규칙

| 브랜치 종류 | 네이밍 예시 | 설명 |
|---|---|---|
| 기능 개발 | `feature/login-api` | 새로운 기능 개발 |
| 버그 수정 | `fix/attendance-null` | 오류 수정 |
| 문서 작업 | `docs/update-readme` | README, 문서 수정 |
| 리팩토링 | `refactor/user-service` | 기능 변경 없는 코드 구조 개선 |

브랜치는 **짧고 명확한 의미**로 작성한다.

---

## 4. 작업 흐름 (Workflow)

1) 가장 먼저 main 브랜치 최신화
```bash
git checkout <브랜치명>
git pull origin <브랜치명>
```

2) 코드 작성 후 변경 사항 확인
```bash
git status
git add .
git commit -m "feat: 기능 요약"
```

3) GitHub 로 푸시
```bash
git push origin feature/기능명
```

4) GitHub에서 Pull Request(PR) 생성
→ 리뷰 받고 승인 후 main으로 병합

## 5. rebase 사용 가이드 (중요!)

merge 대신 rebase를 사용하면 커밋 히스토리가 깔끔해진다.

- 사용 상황:

main 브랜치가 업데이트된 이후, 내 feature 브랜치 반영이 필요할 때

- 사용 방법
```bash
git checkout feature/기능명
git fetch origin
git rebase origin/main
```

- 충돌이 발생한 경우
충돌 해결 후
```bash
git add .
git rebase --continue
```

rebase 완료 후 원격 강제 푸시
```bash
git push origin feature/기능명 --force
```
⚠️ --force 는 본인이 혼자 사용하는 브랜치에서만 사용
(공유 브랜치에서는 절대 금지)