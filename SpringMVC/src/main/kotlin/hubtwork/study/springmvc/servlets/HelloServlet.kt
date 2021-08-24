package hubtwork.study.springmvc.servlets

import javax.servlet.annotation.WebServlet
import javax.servlet.http.HttpServlet
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@WebServlet(name = "HelloServlet", urlPatterns = ["/hello", "/bye"])
class HelloServlet : HttpServlet() {

    // Servlet Request / Response Object for current url request
    override fun service(request: HttpServletRequest?, response: HttpServletResponse?) {
        // get Request Parsing with query params
        var responseMsg: String = ""
        // request Param :: name [ not null, not empty string ]
        responseMsg = if (request?.getParameter("name") != null && request?.getParameter("name").isNotEmpty()) {
            request.getParameter("name") + " 님 하이요"
        } else {
            "이름을 입력해주세요"
        }
        // send Response ( plain Text )
        if (response != null) {
            response.contentType = "text/plain"
            response.characterEncoding = "utf-8"
            response.writer.write(responseMsg)
        }

    }
}