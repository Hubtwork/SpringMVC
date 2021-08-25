package hubtwork.study.springmvc.spring.old

import org.springframework.stereotype.Component
import org.springframework.web.HttpRequestHandler
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@Component("/springmvc/request-handler")
class MyHttpRequestHandler: HttpRequestHandler {

    override fun handleRequest(request: HttpServletRequest, response: HttpServletResponse) {
        println("MyHttpRequestHandler now Handling.. ")
    }
}