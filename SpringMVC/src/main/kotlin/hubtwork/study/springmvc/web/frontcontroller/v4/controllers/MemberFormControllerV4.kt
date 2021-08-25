package hubtwork.study.springmvc.web.frontcontroller.v4.controllers

import hubtwork.study.springmvc.web.frontcontroller.v4.ControllerV4

class MemberFormControllerV4: ControllerV4 {
    override fun process(paramMap: MutableMap<String, String>, model: MutableMap<String, Any>): String = "new-form"
}