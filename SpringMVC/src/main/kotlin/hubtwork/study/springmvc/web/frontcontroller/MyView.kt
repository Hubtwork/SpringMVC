package hubtwork.study.springmvc.web.frontcontroller

import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

/**
 * View Forward -- Duplicate Code Separation
 *
 */
class MyView(private val viewPath: String) {

    fun render(request: HttpServletRequest, response: HttpServletResponse) {
        val dispatcher = request.getRequestDispatcher(viewPath)
        dispatcher.forward(request, response)
    }

    fun render(model: MutableMap<String, Any>, request: HttpServletRequest, response: HttpServletResponse) {
        modelToRequestAttribute(model, request)
        val dispatcher = request.getRequestDispatcher(viewPath)
        dispatcher.forward(request, response)
    }

    private fun modelToRequestAttribute(model: MutableMap<String, Any>, request: HttpServletRequest) {
        model.forEach { (k, v) -> request.setAttribute(k, v) }
    }
}