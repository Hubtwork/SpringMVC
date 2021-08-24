package hubtwork.study.springmvc.servlets.request.post

import hubtwork.study.springmvc.servlets.util.HttpRequestUtil.printParameters
import hubtwork.study.springmvc.servlets.util.HttpRequestUtil.readMessageBodyWithInputStream
import javax.servlet.annotation.WebServlet
import javax.servlet.http.HttpServlet
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@WebServlet(name="requestBodyStringServlet", urlPatterns = ["/request-body-string"])
class RequestBodyStringServlet: HttpServlet() {

    override fun service(request: HttpServletRequest?, response: HttpServletResponse?) {
        if (request != null) {
            /**
             * 2 ways for extracting parameters in "POST"
             *
             * 1. use inputStream
             * 2. use getParameter ( parameterMap )
             */
            // readMessageBodyWithInputStream(request)
            printParameters(request)

            response?.writer?.write("Success")
        }
    }
}