package hubtwork.study.springmvcproject.basic.request

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import hubtwork.study.springmvcproject.model.HelloData
import org.slf4j.LoggerFactory
import org.springframework.http.HttpEntity
import org.springframework.stereotype.Controller
import org.springframework.util.StreamUtils
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.ResponseBody
import java.nio.charset.StandardCharsets
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@Controller
class RequestBodyJsonController {

    private val objectMapper = jacksonObjectMapper()
    private val logger = LoggerFactory.getLogger(javaClass)

    /**
     *  Spring 에서 Http Request - JSON 가져오는 법
     *      1. HttpServletRequest.InputStream 이용해 읽어들인 후 jacksonObjectMapper() 이용해 파싱
     *      2. @RequestBody body: String 이용해 body Message 를 String 으로 받아온 후 jacksonObjectMapper() 이용해 파싱
     *      3. @RequestBody value: CLASS 이용해 CLASS 로 바로 받아옴
     *      4. @HttpEntity<CLASS>: HttpEntity 로 Body 를 매핑해서 가져오는 방법
     *      5. @RequestBody, @ResponseBody 이용
     *          - @RequestBody : Json Request -> Http Message Converter -> Object Binding
     *          - @ResponseBody : Object Binding -> Http Message Converter -> Json Request
     *
     *  주의할 점
     *      1. @RequestBody 으로 바로 CLASS 로 가져올 때 이를 생략시 @ModelAttribute 로 받음 ( Request Parameter 로 처리되기 때문에 Json Parsing 안됨 )
     *      2. 요청의 'content-type = application/json' 이어야 JSON 처리 가능한 HTTP message converter 가 실행됨
     */
    @PostMapping("/request-body-json-v1")
    fun requestBodyJsonV1(
        request: HttpServletRequest,
        response: HttpServletResponse
    ) {
        val inpStream = request.inputStream
        val bodyMsg = StreamUtils.copyToString(inpStream, StandardCharsets.UTF_8)
        val helloData = objectMapper.readValue(bodyMsg, HelloData::class.java)
        logger.info("username = {${helloData.username}} , age = {${helloData.age}}")

        response.writer.write("success")
    }

    @ResponseBody
    @PostMapping("/request-body-json-v2")
    fun requestBodyJsonV2(
        @RequestBody body: String
    ): String {
        val helloData = objectMapper.readValue(body, HelloData::class.java)
        logger.info("username = {${helloData.username}} , age = {${helloData.age}}")

        return "success"
    }

    @ResponseBody
    @PostMapping("/request-body-json-v3")
    fun requestBodyJsonV3(
        @RequestBody helloData: HelloData
    ): String {
        logger.info("username = {${helloData.username}} , age = {${helloData.age}}")

        return "success"
    }

    @ResponseBody
    @PostMapping("/request-body-json-v4")
    fun requestBodyJsonV4(
        helloData: HttpEntity<HelloData>
    ): String {
        val body = helloData.body!!
        logger.info("username = {${body.username}} , age = {${body.age}}")

        return "success"
    }

    @ResponseBody
    @PostMapping("/request-body-json-v5")
    fun requestBodyJsonV5(
        @RequestBody helloData: HelloData
    ): HelloData {
        logger.info("username = {${helloData.username}} , age = {${helloData.age}}")

        return helloData
    }

}