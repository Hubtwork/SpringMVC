

## Servlet

#### HttpServletRequest

- HTTP Request 메세지를 파싱

- `getParameter(name)` - query Parameter 로드

  - `getParameterValues(name)` - 복수의 쿼리 파라미터가 담긴 요청의 경우 **Array** 로 받아올 수 있음 ( ex_ 검색 옵션 )

  > 파라미터가 복수일 경우 **`getParameter`** 는 첫번째 값 반환

- `setAttribute(k, v) / getAttribute(k)` - 요청 ~ 응답까지만 유지되는 해당 요청에 대한 임시 Key-Value 저장 기능

- **Msg, Header, Body** 관련 메소드 / 멤버 체크할 것

##### POST 요청 처리

- `inputStream` - 메세지 Body 의 파라미터를 **StreamUtils** 이용해 인코딩, 파싱해 활용

  ~~~kotlin
  val inputStream = request.inputStream
  val message = StreamUtils.copyToString(inputStream, StandardCharsets.UTF_8)
  val user:User = objectMapper.readValue(message, User::class.java)
  
  ~~~

  - 직접 파싱 혹은 **ObjectMapper** 이용해 `Data Class` 에 저장



#### HttpServletResponse

- `setContentType` - 컨텐츠 타입
- `setCharacterEncoding` - 인코딩 ( utf-8 권장 )
- `getWriter()` - Response Writer 로드

