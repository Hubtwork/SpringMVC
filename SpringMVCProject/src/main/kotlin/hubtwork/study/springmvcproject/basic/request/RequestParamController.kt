package hubtwork.study.springmvcproject.basic.request

import hubtwork.study.springmvcproject.model.HelloData
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.ModelAttribute
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.ResponseBody
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@Controller
class RequestParamController {

    private val logger = LoggerFactory.getLogger(javaClass)

    @RequestMapping("/request-param-v1")
    fun requestParamV1(
        request: HttpServletRequest,
        response: HttpServletResponse
    ) {
        val username = request.getParameter("username")
        val age = request.getParameter("age").toInt()
        logger.info("username={$username} ,age={$age}")
        response.writer.write("success")
    }

    /**
     * @ResponseBody
     * -
     *
     */
    @ResponseBody
    @RequestMapping("/request-param-v2")
    fun requestParamV2(
        @RequestParam("username") username: String,
        @RequestParam("age") age: Int
    ): String {
        logger.info("username={$username} ,age={$age}")
        return "success"
    }

    /**
     * @RequestParam
     * - Parameter 명과 받는 변수명이 같으면 생략해도 매핑
     * - Type 이 String, Int 등 Primitive 할 경우 @RequestParam 도 생략 가능
     * - required = true (default) 일 경우, 400 ( Bad Request )
     * - required 인데 빈 문자열일 경우 통과, 형변환 불가하면 500 ( 엄격하게 처리할 것 )
     */
    @ResponseBody
    @RequestMapping("/request-param-v3")
    fun requestParamV3(
        @RequestParam username: String,
        @RequestParam age: Int
    ): String {
        logger.info("username={$username} ,age={$age}")
        return "success"
    }

    @ResponseBody
    @RequestMapping("/request-param-v4")
    fun requestParamV4(
        username: String,
        age: Int
    ): String {
        logger.info("username={$username} ,age={$age}")
        return "success"
    }

    @ResponseBody
    @RequestMapping("/request-param-required")
    fun requestParamRequired(
        @RequestParam(required = true, defaultValue = "guest") username: String,
        @RequestParam(required = true, defaultValue = "0") age: Int
    ): String {
        logger.info("username={$username} ,age={$age}")
        return "success"
    }

    @ResponseBody
    @RequestMapping("/request-param-map")
    fun requestParamMap(
        @RequestParam paramMap: Map<String, Any>
    ): String {
        logger.info("username={${paramMap["username"]}} ,age={${paramMap["age"]}}")
        return "success"
    }

    /**
     *  @ModelAttribute
     *  - Parameter 에 대한 객체 자동 바인딩, Kotlin : Data class , Java : @lombok.Data 이용
     *  - Parameter 들이 DTO 와 일치할 경우, @ModelAttribute 생략 가능
     *
     *  ## 주의
     *  - @RequestParam, @ModelAttribute 모두 생략 가능
     *  - 스프링은 컴파일 시 이를 구분하기 위해
     *      - PRIMITIVE 의 경우 @RequestParam 으로
     *      - Custom Class 일 경우 @ModelAttribute 로
     */
    @ResponseBody
    @RequestMapping("/model-attribute-v1")
    fun modelAttributeV1(
        @ModelAttribute helloData: HelloData
    ): String {
        logger.info("username={${helloData.username}} ,age={${helloData.age}}")
        return "success"
    }

    @ResponseBody
    @RequestMapping("/model-attribute-v2")
    fun modelAttributeV2(
        helloData: HelloData
    ): String {
        logger.info("username={${helloData.username}} ,age={${helloData.age}}")
        return "success"
    }



}