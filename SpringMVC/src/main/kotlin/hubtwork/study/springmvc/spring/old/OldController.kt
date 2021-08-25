package hubtwork.study.springmvc.spring.old

import org.springframework.stereotype.Component
import org.springframework.web.servlet.ModelAndView
import org.springframework.web.servlet.mvc.Controller
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@Component("/springmvc/old-controller")
class OldController: Controller {

    override fun handleRequest(request: HttpServletRequest, response: HttpServletResponse): ModelAndView? {
        println("Spring Old Style Controller")
        return ModelAndView("new-form")
    }
}