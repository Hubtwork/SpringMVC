package hubtwork.study.springmvc.web.frontcontroller.v5

import hubtwork.study.springmvc.web.frontcontroller.ModelView
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

interface MyHandlerAdapter {
    fun supports(handler: Any): Boolean
    fun handle(request: HttpServletRequest, response: HttpServletResponse, handler: Any): ModelView
}