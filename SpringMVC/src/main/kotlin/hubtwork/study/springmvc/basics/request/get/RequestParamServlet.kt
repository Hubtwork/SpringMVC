package hubtwork.study.springmvc.basics.request.get

import hubtwork.study.springmvc.basics.util.HttpRequestUtil.printParameters
import javax.servlet.annotation.WebServlet
import javax.servlet.http.HttpServlet
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@WebServlet(name = "requestParamServlet", urlPatterns = ["/request-param"])
class RequestParamServlet: HttpServlet() {

    override fun service(request: HttpServletRequest?, response: HttpServletResponse?) {

        if (request != null) {
            printParameters(request)
        } else {
            println("--- Request Error --- ")
        }

    }

}