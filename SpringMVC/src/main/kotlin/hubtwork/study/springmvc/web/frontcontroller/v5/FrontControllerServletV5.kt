package hubtwork.study.springmvc.web.frontcontroller.v5

import hubtwork.study.springmvc.web.frontcontroller.MyView
import hubtwork.study.springmvc.web.frontcontroller.v3.controllers.MemberFormControllerV3
import hubtwork.study.springmvc.web.frontcontroller.v3.controllers.MemberListControllerV3
import hubtwork.study.springmvc.web.frontcontroller.v3.controllers.MemberSaveControllerV3
import hubtwork.study.springmvc.web.frontcontroller.v4.controllers.MemberFormControllerV4
import hubtwork.study.springmvc.web.frontcontroller.v4.controllers.MemberListControllerV4
import hubtwork.study.springmvc.web.frontcontroller.v4.controllers.MemberSaveControllerV4
import hubtwork.study.springmvc.web.frontcontroller.v5.adapters.ControllerV3HandlerAdapter
import hubtwork.study.springmvc.web.frontcontroller.v5.adapters.ControllerV4HandlerAdapter
import org.springframework.web.servlet.HandlerAdapter
import javax.servlet.annotation.WebServlet
import javax.servlet.http.HttpServlet
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@WebServlet(name = "frontControllerServletV5", urlPatterns = ["/front-controller/v5/*"])
class FrontControllerServletV5: HttpServlet() {

    private val handlerMappingMap: MutableMap<String, Any> = hashMapOf()
    private val handlerAdapters: MutableList<MyHandlerAdapter> = arrayListOf()

    init {
        initHandlerAdapters()
        initHandlerMappingMap()
    }

    private fun initHandlerMappingMap() {
        // V3
        handlerMappingMap["/front-controller/v5/v3/members/new-form"] = MemberFormControllerV3()
        handlerMappingMap["/front-controller/v5/v3/members/save"] = MemberSaveControllerV3()
        handlerMappingMap["/front-controller/v5/v3/members"] = MemberListControllerV3()
        // V4
        handlerMappingMap["/front-controller/v5/v4/members/new-form"] = MemberFormControllerV4()
        handlerMappingMap["/front-controller/v5/v4/members/save"] = MemberSaveControllerV4()
        handlerMappingMap["/front-controller/v5/v4/members"] = MemberListControllerV4()
    }

    private fun initHandlerAdapters() {
        // V3
        handlerAdapters.add(ControllerV3HandlerAdapter())
        // V4
        handlerAdapters.add(ControllerV4HandlerAdapter())
    }

    override fun service(request: HttpServletRequest?, response: HttpServletResponse?) {
        if (request != null && response != null) {
            val handler = getHandler(request)
            if (handler == null) {
                handleNotFoundError(response)
                return
            }
            val adapter = getHandlerAdapter(handler)
            if (adapter == null) {
                handleNotFoundError(response)
                return
            }

            val modelView = adapter.handle(request, response, handler)
            val view = viewResolver(modelView.viewName)
            view.render(modelView.model, request, response)
        }

    }

    private fun handleNotFoundError(response: HttpServletResponse) {
        response.status = HttpServletResponse.SC_NOT_FOUND
    }

    private fun getHandler(request: HttpServletRequest): Any? = handlerMappingMap[request.requestURI]

    // Adapter Picking Function
    private fun getHandlerAdapter(handler: Any): MyHandlerAdapter? {
        for (adapter in handlerAdapters) {
            if (adapter.supports(handler)) return adapter
        }
        // No Adapter for handler
        return null
    }

    private fun viewResolver(viewName: String) = MyView("/WEB-INF/views/$viewName.jsp")
}