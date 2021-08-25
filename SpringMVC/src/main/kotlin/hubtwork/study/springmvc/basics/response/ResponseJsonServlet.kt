package hubtwork.study.springmvc.basics.response

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import hubtwork.study.springmvc.data.User
import javax.servlet.annotation.WebServlet
import javax.servlet.http.HttpServlet
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@WebServlet(name = "responseJsonServlet", urlPatterns = ["/response-json"])
class ResponseJsonServlet: HttpServlet() {

    private val objectMapper = jacksonObjectMapper()

    override fun service(request: HttpServletRequest?, response: HttpServletResponse?) {

        if (response != null) {
            response.setHeader("content-type", "application/json")
            response.characterEncoding = "utf-8"

            var user = User("hubtwork", 28)
            val responseMsg = objectMapper.writeValueAsString(user)

            response.writer.write(responseMsg)
        }
    }
}