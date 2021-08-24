

## Servlet

#### HttpServletRequest

- HTTP Request 메세지를 파싱

- `getParameter(name)` - query Parameter 로드
- `setAttribute(k, v) / getAttribute(k)` - 요청 ~ 응답까지만 유지되는 해당 요청에 대한 임시 Key-Value 저장 기능
- **Msg, Header, Body** 관련 메소드 / 멤버 체크할 것

#### HttpServletResponse

- `setContentType` - 컨텐츠 타입
- `setCharacterEncoding` - 인코딩 ( utf-8 권장 )
- `getWriter()` - Response Writer 로드

