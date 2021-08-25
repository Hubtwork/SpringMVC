package hubtwork.study.springmvc.web.frontcontroller.v3.controllers

import hubtwork.study.springmvc.web.frontcontroller.ModelView
import hubtwork.study.springmvc.web.frontcontroller.v3.ControllerV3

class MemberFormControllerV3: ControllerV3 {

    override fun process(paramMap: Map<String, String>): ModelView = ModelView("new-form")
}