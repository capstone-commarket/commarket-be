# REST API 설계 컨벤션 (API Style Guide)

본 문서는 서버 간 및 FE-BE 간 통신 시 사용하는 **REST API 규칙**을 정의한다.  
목표는 **일관성**, **가독성**, **예측 가능성**이다.

---

## 1. 기본 규칙
- API는 **RESTful** 원칙을 최대한 따른다.
- 자원(Resource)은 **명사**, 동작은 **HTTP Method(동사)**로 표현한다.
- **응답 형식은 JSON**으로 통일한다.

---

## 2. URL 작성 규칙

###  **자원은 복수형 (s)** 로 작성
/users
/events
/attendances

markdown
코드 복사

###  하위 자원은 **계층 구조**로 표현
/users/{userId}/events
/events/{eventId}/attendances

###  단어는 **소문자 + 하이픈(-)** 사용
/event-categories ✅
/eventCategories ❌
/event_categories ❌

---

## 3. HTTP Method 규칙

| Method | 목적 | 예시 URL | 설명 |
|---|---|---|---|
| GET | 데이터 조회 | `GET /users/{id}` | 조회만 수행 |
| POST | 신규 생성 | `POST /users` | Request Body 필요 |
| PUT | 전체 수정 | `PUT /users/{id}` | 모든 값 변경 |
| PATCH | 부분 수정 | `PATCH /users/{id}` | 일부 필드만 변경 |
| DELETE | 삭제 | `DELETE /users/{id}` | 삭제 수행 |

### 예시
GET /events # 이벤트 목록 조회
POST /events # 이벤트 생성
GET /events/{id} # 특정 이벤트 조회
PATCH /events/{id} # 이벤트 내용 일부 수정
DELETE /events/{id} # 이벤트 삭제

---

## 4. Request / Response 규칙

###  Request DTO
- 입력 데이터 구조를 명확하게 정의
- Controller 내부에서만 사용

```java
public record CreateEventRequest(String title, String location, LocalDateTime date) {}
```
### Response DTO
- Entity 를 직접 반환하지 않는다
- 필요한 값만 골라서 전달

```java
public record EventResponse(Long id, String title, LocalDateTime date) {}
```

## 5. 공통 응답 형식
모든 API 응답은 다음 ApiResponse 형태를 따른다.

```json
{
  "success": true,
  "data": { ... },
  "message": null
}
```
Java 예시
```java
public record ApiResponse<T>(boolean success, T data, String message) {

    public static <T> ApiResponse<T> ok(T data) {
        return new ApiResponse<>(true, data, null);
    }

    public static <T> ApiResponse<T> fail(String message) {
        return new ApiResponse<>(false, null, message);
    }
}
```

## 6. 에러 처리 규칙
에러 응답 예시
```json
{
  "success": false,
  "message": "USER_NOT_FOUND"
}
```
에러는 enum 으로 관리
```java
public enum ErrorCode {
    USER_NOT_FOUND,
    EVENT_NOT_ACTIVE,
    INVALID_AUTH_TOKEN
}
```

예외 처리 핸들링
```java
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(AppException.class)
    public ResponseEntity<ApiResponse<Void>> handle(AppException e) {
        return ResponseEntity.status(e.getStatus())
                .body(ApiResponse.fail(e.getErrorCode().name()));
    }
}
```

## 7. 상태 코드 (HTTP Status Code)
| 코드 | 의미 | 사용 예시 |
| 200 OK | 정상 처리 | 조회 성공 |
| 201 Created | 생성 성공 | 신규 리소스 생성 |
| 400 Bad Request | 잘못된 요청 | 검증 실패 |
| 401 Unauthorized | 인증 필요 | 로그인 필요 API |
| 403 Forbidden | 권한 없음 | 관리자 전용 API |
| 404 Not Found | 리소스 없음 | 존재하지 않는 ID 요청 |
| 500 Internal Server Error | 서버 문제 | 예외 처리 불가 상황 |

## 8. API 예시 종합
이벤트 생성
```bash
POST /events
Request Body
```
request
```json
{
  "title": "개강 총회",
  "location": "강당",
  "date": "2025-01-10T19:00:00"
}
```
Response
```json
{
  "success": true,
  "data": {
    "id": 1,
    "title": "개강 총회",
    "date": "2025-01-10T19:00:00"
  }
}
```