package hubtwork.study.springmvc.web.frontcontroller.v2.controllers

import hubtwork.study.springmvc.data.Member
import hubtwork.study.springmvc.repository.MemberRepository
import hubtwork.study.springmvc.web.frontcontroller.MyView
import hubtwork.study.springmvc.web.frontcontroller.v2.ControllerV2
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

class MemberSaveControllerV2 : ControllerV2 {

    private val memberRepository = MemberRepository.instance

    override fun process(request: HttpServletRequest, response: HttpServletResponse): MyView {

        val name = request.getParameter("username")
        val age = request.getParameter("age").toInt()
        val member = memberRepository.save(Member(userName = name, age = age))
        request.setAttribute("member", member)
        return MyView("/WEB-INF/views/save-result.jsp")
    }
}