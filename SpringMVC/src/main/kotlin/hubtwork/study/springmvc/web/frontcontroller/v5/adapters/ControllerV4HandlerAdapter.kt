package hubtwork.study.springmvc.web.frontcontroller.v5.adapters

import hubtwork.study.springmvc.web.frontcontroller.ModelView
import hubtwork.study.springmvc.web.frontcontroller.v4.ControllerV4
import hubtwork.study.springmvc.web.frontcontroller.v5.MyHandlerAdapter
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

class ControllerV4HandlerAdapter: MyHandlerAdapter {

    override fun supports(handler: Any): Boolean = (handler is ControllerV4)

    override fun handle(request: HttpServletRequest, response: HttpServletResponse, handler: Any): ModelView {
        val controller = handler as ControllerV4

        val paramMap: MutableMap<String, String> = createParamMap(request)
        val model: MutableMap<String, Any> = hashMapOf()

        val viewName = controller.process(paramMap, model)
        val modelView = ModelView(viewName)
        modelView.model = model
        // Adapter return V4 Style
        return modelView
    }

    private fun createParamMap(request: HttpServletRequest): MutableMap<String, String> {
        val map: MutableMap<String, String> = hashMapOf()
        request.parameterNames.asIterator().forEachRemaining{ map[it] = request.getParameter(it) }
        return map
    }
}