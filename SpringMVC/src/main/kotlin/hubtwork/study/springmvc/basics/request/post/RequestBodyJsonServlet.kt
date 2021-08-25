package hubtwork.study.springmvc.basics.request.post

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import hubtwork.study.springmvc.data.User
import org.springframework.util.StreamUtils
import java.nio.charset.StandardCharsets
import javax.servlet.annotation.WebServlet
import javax.servlet.http.HttpServlet
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse


@WebServlet(name = "requestBodyJsonServlet", urlPatterns = ["/request-body-json"])
class RequestBodyJsonServlet : HttpServlet() {

    private var objectMapper = jacksonObjectMapper()

    override fun service(request: HttpServletRequest?, response: HttpServletResponse?) {

        if (request != null) {
            val inputStream = request.inputStream
            val message = StreamUtils.copyToString(inputStream, StandardCharsets.UTF_8)
            print(message)
            val user:User = objectMapper.readValue(message, User::class.java)
            println(user)

        }
    }
}