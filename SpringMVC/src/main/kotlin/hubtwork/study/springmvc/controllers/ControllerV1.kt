package hubtwork.study.springmvc.controllers

import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

interface ControllerV1 {

    fun process(request: HttpServletRequest, response: HttpServletResponse)
}