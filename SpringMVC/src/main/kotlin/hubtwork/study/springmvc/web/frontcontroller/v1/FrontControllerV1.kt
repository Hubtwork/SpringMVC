package hubtwork.study.springmvc.web.frontcontroller.v1

import hubtwork.study.springmvc.web.frontcontroller.v1.controllers.MemberFormControllerV1
import hubtwork.study.springmvc.web.frontcontroller.v1.controllers.MemberListControllerV1
import hubtwork.study.springmvc.web.frontcontroller.v1.controllers.MemberSaveControllerV1
import javax.servlet.annotation.WebServlet
import javax.servlet.http.HttpServlet
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

// * in URL : means 'ALL'
@WebServlet(name = "frontControllerV1", urlPatterns = ["/front-controller/v1/*"])
class FrontControllerV1
    : HttpServlet() {

    private var controllerMap: MutableMap<String, ControllerV1> = HashMap()

    init {
        controllerMap["/front-controller/v1/members/new-form"] = MemberFormControllerV1()
        controllerMap["/front-controller/v1/members/save"] = MemberSaveControllerV1()
        controllerMap["/front-controller/v1/members"] = MemberListControllerV1()
    }

    override fun service(request: HttpServletRequest?, response: HttpServletResponse?) {
        println("Controller V1")
        if (request != null) {
            val requestURI = request.requestURI

            val controller = controllerMap[requestURI]
            if (response != null) {
                if ( controller == null ) {
                    response.status = HttpServletResponse.SC_NOT_FOUND
                    return
                }
                controller.process(request, response)
            }
        }
    }


}