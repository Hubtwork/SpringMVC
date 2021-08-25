package hubtwork.study.springmvc.web.frontcontroller.v2.controllers

import hubtwork.study.springmvc.data.Member
import hubtwork.study.springmvc.repository.MemberRepository
import hubtwork.study.springmvc.web.frontcontroller.MyView
import hubtwork.study.springmvc.web.frontcontroller.v2.ControllerV2
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

class MemberListControllerV2 : ControllerV2 {

    private val memberRepository = MemberRepository.instance

    override fun process(request: HttpServletRequest, response: HttpServletResponse): MyView {

        val members = memberRepository.findAll()
        request.setAttribute("members", members)
        return MyView("/WEB-INF/views/members.jsp")
    }
}