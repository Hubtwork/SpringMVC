package hubtwork.study.springmvc.web.frontcontroller.v2.controllers

import hubtwork.study.springmvc.web.frontcontroller.MyView
import hubtwork.study.springmvc.web.frontcontroller.v2.ControllerV2
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

class MemberFormControllerV2 : ControllerV2 {

    override fun process(request: HttpServletRequest, response: HttpServletResponse) = MyView("/WEB-INF/views/new-form.jsp")

}