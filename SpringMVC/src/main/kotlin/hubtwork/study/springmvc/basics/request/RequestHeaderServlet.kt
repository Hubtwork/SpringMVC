package hubtwork.study.springmvc.basics.request

import hubtwork.study.springmvc.basics.util.HttpRequestUtil.extractHeader
import hubtwork.study.springmvc.basics.util.HttpRequestUtil.extractRequestInfo
import hubtwork.study.springmvc.basics.util.HttpRequestUtil.utilizedHeader
import javax.servlet.annotation.WebServlet
import javax.servlet.http.HttpServlet
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse


/**
 *  URL : http://localhost:8080/request-header
 */
@WebServlet(name = "requestHeaderServlet", urlPatterns = ["/request-header"])
class RequestHeaderServlet: HttpServlet() {

    override fun service(request: HttpServletRequest?, response: HttpServletResponse?) {
        if (request != null) {
            extractHeader(request)
            extractRequestInfo(request)
            utilizedHeader(request)
        } else {
            println("### REQUEST-ERROR ###")
        }
    }

}