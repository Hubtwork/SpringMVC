package hubtwork.study.springmvcproject.basic

import org.slf4j.LoggerFactory
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

/**
 * @Controller
 * - String 반환시 View-Name 으로 인식해 해당 뷰를 찾고 반환함
 *
 * @RestController
 * - View 가 아닌 String 을 HTTP body message 로서 인식함
 * - 반환시 View 가 아닌 바디로 해당 내용을 보내줌
 */
@RestController
class LogTestController {

    /**
     * Logger -> SLF4J ( Interface for Logback, Log4J, Log4J2, etc .. )
     * - representative Spring logging library : Logback
     * - logging 시엔 String 에 + 는 사칙연산으로 실행되므로 사용시 주의할 것
     *  - logger.debug("log = {$data}")   [ O ]
     *  - logger.debug("log = " + data)   [ X ]
     * - 하지만 info level 의 로그는 위와 같은 사칙연산이 발생 X
     *
     * # 장점
     * - 현 스레드, 클래스 등의 정보를 볼 수 있음
     * - 콘솔에 로깅하는 것 뿐 아니라 로그를 별도 저장 가능
     * - 내부 버퍼링, 멀티스레드 같은 기능이 지원되므로 일반 print 보다 성능이 뛰어남
     */
    private val logger = LoggerFactory.getLogger(javaClass)

    @RequestMapping("/log-test")
    fun loggingTestAPI(): String {
        val test = "Spring Logger"

        logger.trace("trace = {$test}")
        logger.debug("debug = {$test}")
        logger.info("info = {$test}")
        logger.error("error = {$test}")
        logger.warn("warn = {$test}")

        return "Logging Test Successful"
    }

}