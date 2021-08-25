package hubtwork.study.springmvc.web.frontcontroller.v3

import hubtwork.study.springmvc.web.frontcontroller.ModelView

interface ControllerV3 {

    fun process(paramMap: Map<String, String>): ModelView
}