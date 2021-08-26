package hubtwork.study.springmvcproject.basic.request

import org.slf4j.LoggerFactory
import org.springframework.http.HttpEntity
import org.springframework.stereotype.Controller
import org.springframework.util.StreamUtils
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.ResponseBody
import java.io.InputStream
import java.io.PrintWriter
import java.nio.charset.StandardCharsets
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@Controller
class RequestBodyStringController {

    private val logger = LoggerFactory.getLogger(javaClass)

    /**
     *  Spring 에서 Http message Body 가져오는 법
     *      1. HttpServletRequest.inputStream / HttpServletResponse.writer 이용
     *      2. InputStream / PrintWriter 만 사용
     *      3. HttpEntity<String> 이용해 Http Message Body 활용
     */
    @PostMapping("/request-body-string-v1")
    fun requestBodyStringV1(
        request: HttpServletRequest,
        response: HttpServletResponse
    ) {
        val inpStream = request.inputStream
        val message = StreamUtils.copyToString(inpStream, StandardCharsets.UTF_8)
        logger.info("body = {$message}")

        response.writer.write("success")
    }

    @PostMapping("/request-body-string-v2")
    fun requestBodyStringV2(
        inpStream: InputStream,
        writer: PrintWriter
    ) {
        val message = StreamUtils.copyToString(inpStream, StandardCharsets.UTF_8)
        logger.info("body = {$message}")

        writer.write("success")
    }

    @PostMapping("/request-body-string-v3")
    fun requestBodyStringV3(
        httpEntity: HttpEntity<String>
    ): HttpEntity<String> {
        val message = httpEntity.body
        logger.info("body = {$message}")

        return HttpEntity<String>("success")
    }

    /**
     *  Spring 에서 Http message Body 가져오는 법
     *      4. @RequestBody, @ResponseBody 이용
     *          - 각각 요청 / 응답의 Http Message Body 에 직접 접근
     */
    @ResponseBody
    @PostMapping("/request-body-string-v4")
    fun requestBodyStringV4(
        @RequestBody msgBody: String
    ): String {
        logger.info("body = {$msgBody}")

        return "success"
    }

}