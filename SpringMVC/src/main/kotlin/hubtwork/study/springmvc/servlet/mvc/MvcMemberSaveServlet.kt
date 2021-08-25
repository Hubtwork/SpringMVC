package hubtwork.study.springmvc.servlet.mvc

import hubtwork.study.springmvc.data.Member
import hubtwork.study.springmvc.repository.MemberRepository
import javax.servlet.annotation.WebServlet
import javax.servlet.http.HttpServlet
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@WebServlet(name = "mvcMemberSaveServlet", urlPatterns = ["/servlet-mvc/member/save"])
class MvcMemberSaveServlet: HttpServlet() {

    private val memberRepository = MemberRepository.instance

    override fun service(request: HttpServletRequest?, response: HttpServletResponse?) {
        if (request != null) {
            val name = request.getParameter("username")
            val age = request.getParameter("age").toInt()
            // save and get updated ID from system
            var member = Member(userName = name, age = age)
            member = memberRepository.save(member)
            println("[ SYSTEM ] Member : $member")

            // save data in Model temporary
            request.setAttribute("member", member)

            val viewPath = "/WEB-INF/views/save-result.jsp"
            val dispatcher = request.getRequestDispatcher(viewPath)
            dispatcher.forward(request, response)
        }

    }

}