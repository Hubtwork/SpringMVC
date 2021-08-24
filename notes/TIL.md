

### (Spring - Coyote) inputStream, reader 사용 시 Body Parameter 손실

#### 21. 08.24 - `kotlin, SpringMVC, Servlet`



#### 문제 상황

- **HttpServletRequest** 객체의 `inputStream` 을 이용해 직접 메세지를 읽어들이는 것과 `getParameter()` 를 이용해 각각 기능 구현
- 두 가지 기능을 순차적으로 실행할 경우 <u>후에 실행되는 메소드가 작동하지 않는 문제</u> 발생

#### 문제 분석

- 공식 문서에서도 **getParameter** 와 **inputStream / reader** 를 혼용하지 말라고 설명

  > **Java Doc - HttpServletRequest#getParameter 중**
  >
  > If the parameter data was sent in the request body, such as occurs with an HTTP POST request, then reading the body directly via [`getInputStream()`](https://docs.oracle.com/javaee/6/api/javax/servlet/ServletRequest.html#getInputStream()) or [`getReader()`](https://docs.oracle.com/javaee/6/api/javax/servlet/ServletRequest.html#getReader()) can interfere with the execution of this method.
  >
  > [링크](https://docs.oracle.com/javaee/6/api/javax/servlet/ServletRequest.html#getParameter(java.lang.String))

- 서블릿 스펙 상으로 **HttpServletRequest** 의 요청을 읽는 메소드는 아래 3가지

  - `getInputStream`
  - `getReader`

  - `getParameter, getParameterNames, getParameterValues, getParameterMap`

- 위 3가지 중 `getParameter` 계열의 메소드를 첫 호출 시 **Map** 에 **URI Query String & POST-ed data** 의 데이터를 넣어 생성함

  - **Query String** 의 경우, URI 스트링을 파싱하므로 **Map** 을 만들어도 영향 x

- **POST-ed data** 의 경우, 아래 조건을 만족해야만 **Parameter Map** 이 만들어짐 ( 미충족시 동작 X )

  - HTTP(S) 의 **POST** 요청
  - Content Type 이 **application/x-www-form-urlencoded**

  - `getParameter` 계열의 메소드 중 하나 최초 실행 시

- 위 조건이 만족되었을 경우에 **inputStream** 을 이용해 데이터를 읽어 맵을 만듬, 즉 위 **파라미터 맵** 을 먼저 만들 경우, **inputStream** 이나 **reader** 는 더 이상 읽을 수 있는 값이 존재하지 않아 아무런 데이터를 읽을 수 없음

- 반대로 **inputStream** 이나 **reader** 를 먼저 실행해 body 데이터를 읽었을 경우, `getParameter` 계열의 메소드를 호출해도 이미 **Map** 을 만들기 위해 **inputStream** 이 더이상 읽을 데이터가 존재하지 않으므로 **파라미터 맵** 이 만들어지지 않음



---

