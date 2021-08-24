package hubtwork.study.springmvc.servlets.request

import javax.servlet.annotation.WebServlet
import javax.servlet.http.HttpServlet
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@WebServlet(name = "requestHeaderServlet", urlPatterns = ["/request-header"])
class RequestHeaderServlet: HttpServlet() {

    override fun service(request: HttpServletRequest?, response: HttpServletResponse?) {
        if (request != null) {
            extractHeader(request)
            extractRequestMessage(request)
            utilizedHeader(request)
        } else {
            println("### REQUEST-ERROR ###")
        }
    }

    private fun utilizedHeader(request : HttpServletRequest) {
        println("--- Header Utils - start ---")
        println("Host ( Port ) : ${request.serverName} ( ${request.serverPort} )\n")
        println("Locale : ${request.locale}")
        print("All Locales : ")
        request.locales.asIterator().forEachRemaining{ print("$it ")}
        println()
        if (request.cookies != null) {
            println("--- Cookies ---")
            println("${request.cookies.asIterable().forEach{ println(" ${it.name} - ${it.value}") }} ")
        }
        println("--- Header Utils - end ---")
    }

    private fun extractHeader(request : HttpServletRequest) {

        println("--- Headers - start ---")
        request.headerNames.asIterator()
            .forEachRemaining{ it -> println(it + " = " + request.getHeader(it))}
        println("--- Headers - end ---")
    }

    private fun extractRequestMessage(request: HttpServletRequest) {
        println("--- REQUEST-LINE - start ---")
        println("METHOD = " + request.method)
        println("PROTOCOL = " + request.protocol)
        println("SCHEME = " + request.scheme)
        println("URL = " + request.requestURL)
        println("URI = " + request.requestURI)
        println("QUERIES = " + request.queryString)
        println("SECURE = " + request.isSecure)
        println("--- REQUEST-LINE - end ---")
    }
}