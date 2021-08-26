package hubtwork.study.springmvcproject.basic.request

import org.slf4j.LoggerFactory
import org.springframework.http.HttpMethod
import org.springframework.util.MultiValueMap
import org.springframework.web.bind.annotation.CookieValue
import org.springframework.web.bind.annotation.RequestHeader
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.util.*
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@RestController
class RequestHeaderController {

    private val logger = LoggerFactory.getLogger(javaClass)

    /**
     * # Tip for Mapping Header Components
     * - 헤더 전체 : @RequestHeader headers: MultiValueMap<String, String>
     *     * MultiValueMap : 1 Key - n Value
     * - 특정 헤더 : @RequestHeader("value") value: String
     * - 특정 쿠키 : @CookieValue("value") value: String
     *
     * --- required : 필수 여부 ( 필수인데 없을 시 404 ) , defaultValue : 없을 시 기본값 지정
     *
     */
    @RequestMapping("/headers")
    fun headers(
        request: HttpServletRequest,
        response: HttpServletResponse,
        httpMethod: HttpMethod,
        locale: Locale,
        @RequestHeader headerMap: MultiValueMap<String, String>,
        @RequestHeader("host") host: String,
        @CookieValue("ck", required = true) ck: String
    ): String {
        logger.info("request={$request}")
        logger.info("response={$response}")
        logger.info("httpMethod={$httpMethod}")
        logger.info("locale={$locale}")
        logger.info("headerMap={$headerMap}")
        logger.info("header host={$host}")
        logger.info("myCookie={$ck}")
        return "Successfully Mapping Header Components"
    }

}