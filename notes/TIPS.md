

## Preference

- **Preference > Build Tools > Gradle** 의 주체를 `Gradle` 대신 `Intelli J` 로 하면 속도가 좀 더 빠름





---



## application.properties

#### Logging

- `logging.level.org.apache.coyote.http11 = debug`
  - HTTP Request 메세지를 로그로 확인 가능
  - **dev** 에서만 활용. **prod** 에서 전체 메세지에 대한 로그 사용시 성능 저하

 



---

 

## Kotlin 팁

#### Iterator

- `Iterator.forEachRemaining{ ... code }` 
  - 이터레이터를 각각 돌면서 정해진 작업을 수행
  - 반복적 자료형에 대해 `~~.asIterator().forEachRemaining{    }` 와 같이 사용 가능