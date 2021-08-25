package hubtwork.study.springmvc.web.frontcontroller.v1.controllers

import hubtwork.study.springmvc.repository.MemberRepository
import hubtwork.study.springmvc.web.frontcontroller.v1.ControllerV1
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

class MemberListControllerV1: ControllerV1 {

    val memberRepository = MemberRepository.instance

    override fun process(request: HttpServletRequest, response: HttpServletResponse) {

        val memberList = memberRepository.findAll()

        request.setAttribute("members", memberList)
        val viewPath = "/WEB-INF/views/members.jsp"
        val dispatcher = request.getRequestDispatcher(viewPath)
        dispatcher.forward(request, response)
    }
}