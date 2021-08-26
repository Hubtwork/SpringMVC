package hubtwork.study.springmvcproject.basic.response

import hubtwork.study.springmvcproject.model.HelloData
import org.slf4j.LoggerFactory
import org.springframework.http.HttpEntity
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.ResponseBody
import org.springframework.web.bind.annotation.ResponseStatus
import javax.servlet.http.HttpServletResponse

@Controller
class ResponseBodyController {

    private val logger = LoggerFactory.getLogger(javaClass)

    // HTTP Body Message
    @GetMapping("/response-body-string-v1")
    fun responseBodyV1(response: HttpServletResponse) {
        response.writer.write("success")
    }

    // ResponseEntity = HttpEntity 상속을 통해 Body Message , StatusCode 를 지정해 반환 가능
    @GetMapping("/response-body-string-v2")
    fun responseBodyV2() : ResponseEntity<String> = ResponseEntity("success", HttpStatus.OK)

    // @ResponseBody : View 가 아니라 Http Message Converter 를 사용
    @ResponseBody
    @GetMapping("/response-body-string-v3")
    fun responseBodyV3() : String = "success"

    // ResponseEntity 에 String 이 아닌 객체 반환시, Json 으로 변환되어 반환됨
    @GetMapping("/response-body-json-v1")
    fun responseBodyJsonV1() : ResponseEntity<HelloData> {
        val helloData = HelloData("user A", 20)
        return ResponseEntity(helloData, HttpStatus.OK)
    }

    /**
     * @ResponseStatus
     *  - 상태코드 지정 가능
     *  - ResponseBody 이용시 바로 Body Message 에 접근하기 떄문에 상태코드 지정 등이 까다로운데 이를 해소
     */
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    @GetMapping("/response-body-json-v2")
    fun responseBodyJsonV2() : HelloData = HelloData("user A", 20)


}