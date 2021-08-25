package hubtwork.study.springmvc.web.frontcontroller.v5.adapters

import hubtwork.study.springmvc.web.frontcontroller.ModelView
import hubtwork.study.springmvc.web.frontcontroller.v3.ControllerV3
import hubtwork.study.springmvc.web.frontcontroller.v5.MyHandlerAdapter
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

class ControllerV3HandlerAdapter: MyHandlerAdapter {
    override fun supports(handler: Any): Boolean = (handler is ControllerV3)

    override fun handle(request: HttpServletRequest, response: HttpServletResponse, handler: Any): ModelView {
        val controller = handler as ControllerV3
        val paramMap: MutableMap<String, String> = createParamMap(request)
        // Adapter return V3 Style
        return controller.process(paramMap)
    }

    private fun createParamMap(request: HttpServletRequest): MutableMap<String, String> {
        val map: MutableMap<String, String> = hashMapOf()
        request.parameterNames.asIterator().forEachRemaining{ map[it] = request.getParameter(it) }
        return map
    }

}