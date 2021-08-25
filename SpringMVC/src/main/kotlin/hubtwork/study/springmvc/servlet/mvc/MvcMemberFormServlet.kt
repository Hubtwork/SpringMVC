package hubtwork.study.springmvc.servlet.mvc

import javax.servlet.annotation.WebServlet
import javax.servlet.http.HttpServlet
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@WebServlet(name = "mvcMemberFormServlet", urlPatterns = ["/servlet-mvc/members/new-form"])
class MvcMemberFormServlet: HttpServlet() {

    override fun service(request: HttpServletRequest?, response: HttpServletResponse?) {
        if (request != null) {
            val viewPath = "/WEB-INF/views/new-form.jsp"
            val dispatcher = request.getRequestDispatcher(viewPath)
            // Dispatcher.Forward() :: move to New Servlet / JSP
            /**
             * redirect : 클라이언트가 redirect path 로 재요청하게끔 유도
             * forward : 서버 내부 호출. ( 클라이언트는 인지 X )
             */
            dispatcher.forward(request, response)
        }
    }
}