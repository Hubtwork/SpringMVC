package hubtwork.study.springmvc.servlet.mvc

import hubtwork.study.springmvc.repository.MemberRepository
import javax.servlet.annotation.WebServlet
import javax.servlet.http.HttpServlet
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@WebServlet(name = "mvcMemberListServlet", urlPatterns = ["/servlet-mvc/members"])
class MvcMemberListServlet: HttpServlet() {

    private val memberRepository = MemberRepository.instance

    override fun service(request: HttpServletRequest?, response: HttpServletResponse?) {

        if (request != null) {
            val memberList = memberRepository.findAll()

            request.setAttribute("members", memberList)

            val viewPath = "/WEB-INF/views/members.jsp"
            val dispatcher = request.getRequestDispatcher(viewPath)
            dispatcher.forward(request, response)
        }
    }
}