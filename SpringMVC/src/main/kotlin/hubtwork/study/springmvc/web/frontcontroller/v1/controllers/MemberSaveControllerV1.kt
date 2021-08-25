package hubtwork.study.springmvc.web.frontcontroller.v1.controllers

import hubtwork.study.springmvc.data.Member
import hubtwork.study.springmvc.repository.MemberRepository
import hubtwork.study.springmvc.web.frontcontroller.v1.ControllerV1
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

class MemberSaveControllerV1: ControllerV1 {

    private val memberRepository = MemberRepository.instance

    override fun process(request: HttpServletRequest, response: HttpServletResponse) {
        val name = request.getParameter("username")
        val age = request.getParameter("age").toInt()

        var member = Member(userName = name, age = age)
        member = memberRepository.save(member)

        print(" Member [ $member ] successfully enrolled")
        request.setAttribute("member", member)
        val viewPath = "/WEB-INF/views/save-result.jsp"
        val dispatcher = request.getRequestDispatcher(viewPath)
        dispatcher.forward(request, response)
    }

}