### Web Server

- `클라이언트 - 웹 - 서버` , **HTTP** 기반 통신
- 정적 리소스 제공
- ex_ nginx , apache

### Web Application Server ( WAS )

- **HTTP** 기반 통신

- 웹 서버 기능 + 애플리케이션 로직 실행

  > HTTP API, 서블릿, JSP, 스프링 MVC 등

- ex_ Tomcat Jetty, Undertow

##### Web Server 는 정적 리소스 제공, WAS 는 애플리케이션 코드 실행에 특화



### 웹 시스템 구성

- WAS + DB

  - WAS 가 너무 많은 역할을 수행
  - 서버 과부하 가능성
  - 애플리케이션 로직의 수행 불가 가능성

- WEB + WAS + DB

  - 웹 서버 : 정적 리소스 처리 ( WAS 오류 시, 오류 화면 제공 등 )
  - WAS : 동적인 애플리케이션 로직 처리 ( 오류 잘 남 )

  - **효율적** 으로 리소스 관리 가능
    - 정적 리소스 증가 시 **WEB 증설**
    - 애플리케이션 리소스 증가 시 **WAS 증설**

---

### 서블릿



#### 서블릿

- HTTP 스펙을 편하게 사용하기 위해 지원하는 기능

- `HttpServletRequest` : Http 요청
- `HttpServletResponse` : Http 응답

> **HTTP 요청**
>
> - WAS : Request / Response 객체 생성 후 서블릿 객체 호출
> - Request 객체 - HTTP 요청 / Response 객체 - HTTP 응답
> - WAS : Response 객체 내용으로 HTTP 응답



#### 서블릿 컨테이너

- 서블릿을 지원하는 WAS
- 서블릿 객체의 생성, 초기화, 호출, 종료 에 대한 생명주기 관리
- 멀티 스레드 ( **동시 요청 처리** )
- **서블릿 객체 = 싱글톤 객체** 
  - 매 요청에 대한 객체 생성 / 삭제는 **비효율**
  - 공유 변수 사용 시 주의할 것



### 멀티 스레드

#### 스레드

- 애플리케이션 코드를 실행하는 주체
- 한번에 하나의 코드 라인만 수행 가능
- ex_ **main** 스레드

> **단일 스레드** 의 문제점
>
> - 동시에 요청이 왔을 경우, 스레드가 휴지기가 아닐 시 다른 요청에 대한 처리를 못함
> - 특히 처리 중인 스레드의 작업이 오류가 발생해 지연될 경우, 이후 요청도 모두 **Timeout**

#### 방법 1. 요청마다 스레드 생성

- 리소스 허용시 최대한의 성능
- 스레드 생성 비용이 비싸서 응답 속도 저하
- 컨텍스트 스위칭 비용 증가
- 요청이 많을 경우, 리소스가 초과되어 서버 다운

#### 방법 2. 스레드 풀

- 스레드를 스레드 풀에 보관
- 스레드의 최대치 관리 ( ex_ 톰캣 : default 200 개 )
- 스레드가 필요하면 풀에서 꺼내서 사용하고 종료 시 반납
- 스레드가 없을 경우 요청에 대한 거절 / 대기 등 처리 설정
- 리소스 절감 및 빠른 응답, 스레드 제한이 있으므로 리소스 초과 우려 X

#### 스레드 풀 실무 TIP

- WAS 의 핵심 튜닝 포인트 :  **최대 스레드 수**
  - CPU 의 사용률 등 고려해 설정
  - **너무 낮음** : 리소스는 여유로운데 응답이 지연되는 경우 발생
  - **너무 높음** : 리소스에 과부화, 서버 다운 발생
- 장애 발생시 
  - 클라우드 : 선 서버 증설 , 이후 튜닝
  - 온 프레미스 : 선 튜닝

#### 스레드 풀 적정 숫자

- 애플리케이션 로직, 리소스 상황에 따라 다름
- 실 운영환경과 비슷하게 **성능 테스트** 진행해 체크 ( ex_ 아파치 ab, 제이미터, nGrinder )

#### WAS 멀티 스레드 지원

- WAS 가 멀티스레드 관리
- **개발자는 스레드 무관하게 소스코드에 집중**

- **싱글톤 객체** ( 서블릿 / 스프링 빈 ) 주의할 것

---



#### 정적 리소스

- HTML, CSS, JS, 이미지 등
- **HTML 페이지**는 동적으로 생성될 수 있음 ( JSP, 타임리프 등 )

#### HTTP API

- 데이터 통신, 주로 **JSON** 타입 활용
- 방식 1. UI 클라이언트 - 서버
- 방식 2.  서버 - 서버 

#### SSR - 서버 사이드 렌더링

- 서버에서 HTML 생성 후 전달
- 화면이 정적이고 가벼움 ( 백엔드는 알아둬야 함 )
- ex_ ~~JSP~~, **타임리프**

#### CSR - 클라이언트 사이드 렌더링

- JS 를 이용해 웹 브라우저에서 동적으로 HTML 생성, 적용
- 작은 부분 단위로 기능 수행 / 변경 가능. **복잡하고 동적인 UI** ( 프론트엔드 영역 )
- ex_ React, Vue.js 

#### CSR + SSR

- **CSR + SSR** 을 지원하는 웹 프레임워크도 있음
- **SSR** 을 베이스로 사용, JS 를 이용해 일부만 **동적** 으로 활용

---



#### 스프링 MVC ( Web Servlet )

- **어노테이션 기반** ( ex_ `@Controller` )

#### 스프링부트 

- 기존 : 서버에 **WAS 설치**, War 파일 작성후 **WAS 에 배포**
- **내장 서버** : **Jar 에 WAS 서버를 포함** > 빌드 배포 단순화

- **스프링** 을 편하게 사용할 수 있게 함



#### 스프링 WebFlux ( Reactive Web )

- **비동기 + 논 블로킹**
- 최소 스레드로 최대 성능 : 스레드 컨텍스트 스위칭 비용 최소화
- 함수형 코드 : 동시처리 코드 효율화 ( like Node.js )

- `단점` : **기술적 난이도 극상, RDB 지원 낮음, 아직 전환률 낮음 **

  > **하드웨어의 발전** - 다수 스레드를 둔 MVC 모델로도 충분히 성능 보장됨



#### 타임리프

- HTML 정적 형태 유지 + 뷰 템플릿 적용
- **Spring MVC 호환성 최고**
- 성능 우선일 시 `타임리프` 보다 `프리마커, 벨로시티` 활용

---



 #### TIP 

- **Preference > Build Tools > Gradle** 의 주체를 `Gradle` 대신 `Intelli J` 로 하면 속도가 좀 더 빠름



---



### Annotations

- `@ServletComponentScan`
  - 패키지 내의 Servlet 을 검색, 등록해줌



---



## Servlet

#### HttpServletRequest

- `getParameter` - 

#### HttpServletResponse

-  

