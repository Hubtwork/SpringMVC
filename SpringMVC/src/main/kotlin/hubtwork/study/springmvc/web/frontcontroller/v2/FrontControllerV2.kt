package hubtwork.study.springmvc.web.frontcontroller.v2

import hubtwork.study.springmvc.web.frontcontroller.MyView
import hubtwork.study.springmvc.web.frontcontroller.v2.controllers.MemberFormControllerV2
import hubtwork.study.springmvc.web.frontcontroller.v2.controllers.MemberListControllerV2
import hubtwork.study.springmvc.web.frontcontroller.v2.controllers.MemberSaveControllerV2
import javax.servlet.annotation.WebServlet
import javax.servlet.http.HttpServlet
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@WebServlet(name = "frontControllerV2", urlPatterns = ["/front-controller/v2/*"])
class FrontControllerV2 :HttpServlet() {

    private val controllerMap: MutableMap<String, ControllerV2> = hashMapOf()

    init {
        controllerMap["/front-controller/v2/members/new-form"] = MemberFormControllerV2()
        controllerMap["/front-controller/v2/members/save"] = MemberSaveControllerV2()
        controllerMap["/front-controller/v2/members"] = MemberListControllerV2()
    }

    override fun service(request: HttpServletRequest?, response: HttpServletResponse?) {
        println("Controller V2")
        if (request != null) {
            val requestURI = request.requestURI

            val controller = controllerMap[requestURI]
            if (response != null) {
                if ( controller == null ) {
                    response.status = HttpServletResponse.SC_NOT_FOUND
                    return
                }
                // Get MyView Info & Rendering
                val view = controller.process(request, response)
                view.render(request, response)
            }
        }
    }


}