package hubtwork.study.springmvc.web.frontcontroller.v4

import hubtwork.study.springmvc.web.frontcontroller.MyView
import hubtwork.study.springmvc.web.frontcontroller.v4.controllers.MemberFormControllerV4
import hubtwork.study.springmvc.web.frontcontroller.v4.controllers.MemberListControllerV4
import hubtwork.study.springmvc.web.frontcontroller.v4.controllers.MemberSaveControllerV4
import javax.servlet.annotation.WebServlet
import javax.servlet.http.HttpServlet
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@WebServlet(name = "frontControllerV4", urlPatterns = ["/front-controller/v4/*"])
class FrontControllerV4: HttpServlet() {

    private val controllerMap: MutableMap<String, ControllerV4> = hashMapOf()
    init {
        controllerMap["/front-controller/v4/members/new-form"] = MemberFormControllerV4()
        controllerMap["/front-controller/v4/members/save"] = MemberSaveControllerV4()
        controllerMap["/front-controller/v4/members"] = MemberListControllerV4()
    }

    override fun service(request: HttpServletRequest?, response: HttpServletResponse?) {

        if (request != null) {
            val uriPath = request.requestURI

            val controller = controllerMap[uriPath]
            if (controller == null) {
                response!!.status = HttpServletResponse.SC_NOT_FOUND
                return
            }

            val paramMap: MutableMap<String, String> = createParamMap(request)
            val model: MutableMap<String, Any> = hashMapOf()

            val viewName = controller.process(paramMap, model)

            val view = viewResolver(viewName)
            view.render(model, request, response!!)
        }
    }

    private fun createParamMap(request: HttpServletRequest): MutableMap<String, String> {
        val map: MutableMap<String, String> = hashMapOf()
        request.parameterNames.asIterator().forEachRemaining{ map[it] = request.getParameter(it) }
        return map
    }

    private fun viewResolver(path: String) = MyView("/WEB-INF/views/$path.jsp")
}