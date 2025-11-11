## 1. 클래스 & 파일 네이밍 규칙

| 종류 | 네이밍 규칙 | 예시 |
|------|-------------|------|
| Controller | `XXXController` | `UserController` |
| Service Interface | `XXXService` | `UserService` |
| Service 구현체 | `XXXServiceImpl` (필요 시) | `UserServiceImpl` |
| Repository | `XXXRepository` | `UserRepository` |
| Entity | 단수 명사 | `User`, `Event` |
| DTO (Request) | `XXXRequest` | `CreateUserRequest` |
| DTO (Response) | `XXXResponse` | `UserDetailResponse` |

---

## 2. 메서드 네이밍 규칙

### Controller
- HTTP 동사 기반
getUser(), createUser(), updateUser(), deleteUser()

### Service / Repository
- 데이터 행위 이름 기반
findById(), save(), deleteById(), findAll()

### Boolean
isActive(), hasNext(), canAccess()

---

## 3. DTO 작성 규칙

- Entity 를 외부로 직접 반환하지 않는다.
- Request / Response DTO 는 반드시 구분한다.
- Lombok 사용 가능 (단, 필요 이상 남용 금지).

```java
public record CreateUserRequest(String name, String email) {}
public record UserResponse(Long id, String name, String email) {}
```

## 4. Entity 작성 규칙
Setter 사용 금지 → 명확한 변경 메서드 제공

생성자는 @NoArgsConstructor(access = PROTECTED) 사용

상태 변경은 changeName(), activate() 와 같은 이벤트 메서드로 처리

```java
@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class User {

    @Id @GeneratedValue
    private Long id;

    private String name;

    public void changeName(String name) {
        this.name = name;
    }
}
```

## 6. Controller 응답 규칙
공통 Response Wrapper 사용

```java
public record ApiResponse<T>(boolean success, T data, String message) {
    public static <T> ApiResponse<T> ok(T data) {
        return new ApiResponse<>(true, data, null);
    }
}
```

## 7. 예외 처리 규칙
try/catch 는 Controller 에서 사용하지 않는다.

@RestControllerAdvice + @ExceptionHandler 사용

```java
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiResponse<String>> handle(Exception e) {
        return ResponseEntity.badRequest().body(ApiResponse.error(e.getMessage()));
    }
}
```

## 8. 주석 / 문서화 규칙
### 8-1. 주석 작성 기본 원칙

- **왜(Why)** 와 **무엇(What)** 을 설명하는 데 집중한다.
- 코드만 봐도 명확한 내용은 주석을 작성하지 않는다.
- 로직이 복잡하거나 의도가 명확하지 않을 경우 **설명 주석을 반드시 작성**한다.
- 주석은 **변경된 코드와 맞지 않으면 제거 또는 수정**한다.

---

### 8-2. 클래스(Class) 주석

클래스 상단에는 **해당 클래스의 책임(역할)을 한 줄 또는 두 줄로 설명**한다.

```java
/**
 * User 엔티티 클래스
 * - 사용자 정보를 데이터베이스에 저장하는 도메인 객체입니다.
 */
public class User { ... }
```

### 8-3. 멤버 변수(필드) 주석
특히 의미가 명확하지 않은 값 또는 도메인 개념을 반영한 변수에 주석을 작성한다.

```java
// 사용자 이름
private String name;

// 로그인에 사용하는 고유 이메일 (중복 불가)
@Column(unique = true)
private String email;
```

### 8-4. 메서드 주석
메서드 주석은 아래 정보를 포함한다:

| 항목	| 설명 |
| 무엇을 하는 메서드인지	| 한 줄 설명 |
| 파라미터	| @param 사용 |
| 반환값	| @return 사용 |
| 예외 사항 (Optional)	| @throws 사용 |

```java
/**
 * 사용자의 이름을 변경합니다.
 *
 * @param name 변경할 이름
 * @throws IllegalArgumentException 이름이 비어있거나 null일 경우
 */
public void changeName(String name) {
    if(name == null || name.isBlank()) {
        throw new IllegalArgumentException("이름은 비어있을 수 없습니다.");
    }
    this.name = name;
}
```

### 8-5. Controller 메서드 주석
Controller 에서는 해당 API 의 목적을 명확히 작성한다.

```java
/**
 * 사용자 정보를 조회하는 API
 *
 * @param userId 조회할 사용자 ID
 * @return 사용자 정보 응답
 */
@GetMapping("/{userId}")
public ApiResponse<UserResponse> getUser(@PathVariable Long userId) {
    return ApiResponse.ok(userService.findUser(userId));
}
```

### 8-6. Service 메서드 주석
Service 는 비즈니스 의도 중심으로 설명한다.

```java
/**
 * 사용자 정보를 조회합니다.
 * 존재하지 않으면 예외를 발생시킵니다.
 */
public UserResponse findUser(Long id) {
    User user = userRepository.findById(id)
        .orElseThrow(() -> new UserNotFoundException(id));

    return UserResponse.from(user);
}
```

### 8-7. Repository 주석
단, 직접 작성한 Query 가 있을 때만 주석을 작성한다.

```java
// 최근 가입 순으로 상위 10명 조회
@Query("SELECT u FROM User u ORDER BY u.createdAt DESC LIMIT 10")
List<User> findLatestUsers();
```

### 8-8. 한 줄 주석 사용 기준
로직의 의도를 보완하거나

코드 이해에 필요한 핵심 맥락만 작성한다.

```java
// 이메일 인증 여부 확인
if (!user.isVerified()) { ... }
```

## 9. Git Commit 컨벤션

| Prefix	| 설명 |
| feat	| 새로운 기능 추가 |
| fix	| 버그 수정 |
| docs	| 문서 수정 |
| refactor |	코드 구조 개선 (기능 변화 없음) |
| test |	테스트 코드 작성/변경 |
| chore	| 기타 잡일(빌드, 의존성 등) |

형식

```
[ <type> ] <변경 내용>

[ feat ] 사용자 로그인 기능 구현
[ fix ] 이벤트 생성 시 NullPointerException 해결
```

## 10. 협업 규칙
PR 은 최소 1명 이상의 승인 후 병합

코드 리뷰 시 의견은 “왜”를 중심으로 설명

기능 단위로 커밋, 가능한 작은 단위 유지