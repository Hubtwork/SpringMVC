package hubtwork.study.springmvc.web.frontcontroller.v2

import hubtwork.study.springmvc.web.frontcontroller.MyView
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

/**
 * My View Rendering changed
 */
interface ControllerV2 {

    fun process(request: HttpServletRequest, response: HttpServletResponse): MyView
}