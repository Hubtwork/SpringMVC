package hubtwork.study.springmvc.web.frontcontroller.v3

import hubtwork.study.springmvc.web.frontcontroller.ModelView
import hubtwork.study.springmvc.web.frontcontroller.MyView
import hubtwork.study.springmvc.web.frontcontroller.v3.controllers.MemberFormControllerV3
import hubtwork.study.springmvc.web.frontcontroller.v3.controllers.MemberListControllerV3
import hubtwork.study.springmvc.web.frontcontroller.v3.controllers.MemberSaveControllerV3
import javax.servlet.annotation.WebServlet
import javax.servlet.http.HttpServlet
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@WebServlet(name = "frontControllerV3", urlPatterns = ["/front-controller/v3/*"])
class FrontControllerV3: HttpServlet() {

    private val controllerMap: MutableMap<String, ControllerV3> = hashMapOf()
    init {
        controllerMap["/front-controller/v3/members/new-form"] = MemberFormControllerV3()
        controllerMap["/front-controller/v3/members/save"] = MemberSaveControllerV3()
        controllerMap["/front-controller/v3/members"] = MemberListControllerV3()
    }

    override fun service(request: HttpServletRequest?, response: HttpServletResponse?) {

        if (request != null) {
            val uriPath = request.requestURI
            val controller = controllerMap[uriPath]
            if (controller == null) {
                response!!.status = HttpServletResponse.SC_NOT_FOUND
                return
            }

            val paramMap: Map<String, String> = createParamMap(request)
            val modelView = controller.process(paramMap)

            val view = viewResolver(modelView.viewName)
            view.render(modelView.model, request, response!!)
        }
    }

    private fun createParamMap(request: HttpServletRequest): MutableMap<String, String> {
        val map: MutableMap<String, String> = hashMapOf()
        request.parameterNames.asIterator().forEachRemaining{ map[it] = request.getParameter(it) }
        return map
    }

    private fun viewResolver(path: String) = MyView("/WEB-INF/views/$path.jsp")
}