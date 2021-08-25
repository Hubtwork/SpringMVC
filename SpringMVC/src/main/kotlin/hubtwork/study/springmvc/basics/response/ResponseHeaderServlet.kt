package hubtwork.study.springmvc.basics.response

import javax.servlet.annotation.WebServlet
import javax.servlet.http.Cookie
import javax.servlet.http.HttpServlet
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

/**
 *  URL : http://localhost:8080/response-header
 *
 */
@WebServlet(name = "responseHeaderServlet", urlPatterns = ["/response-header"])
class ResponseHeaderServlet: HttpServlet() {

    override fun service(request: HttpServletRequest?, response: HttpServletResponse?) {

        // HttpServletResponse.SC_OK == 200
        if (response != null) {
            // Status Code
            response.status = HttpServletResponse.SC_OK
            // Header set
            response.setHeader("Content-Type", "text/plain; charset=utf-8")
            response.setHeader("Cache-Control", "no-cache, no-store, must-validate")
            response.setHeader("Pragma", "no-cache")
            response.setHeader("my-header", "hello")
            //
            setContent(response)
            setCookie(response)
            redirectHtml(response)

            val responseWriter = response.writer
            responseWriter.println("Success")
        }
    }

    private fun setContent(response: HttpServletResponse) {
        /**
         *  Content-Type : text/plain
         *  Content-Length : 2
         *  response.contentLength = 2 ( 생략시 자동 생성 )
         */
        response.contentType = "text/plain"
        response.characterEncoding = "utf-8"
    }

    private fun setCookie(response: HttpServletResponse) {
        // response.setHeader("Set-Cookie", "myCookie=good; Max-Age=600")
        var cookie = Cookie("cookie", "")
        cookie.maxAge = 600
        response.addCookie(cookie)
    }

    private fun redirectHtml(response: HttpServletResponse) {
        // HttpServletResponse.SC_FOUND : 302
        response.sendRedirect("/basic/hello-form.html")
    }

}