package hubtwork.study.springmvc.servlets.request

import javax.servlet.annotation.WebServlet
import javax.servlet.http.HttpServlet
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@WebServlet(name = "requestParamServlet", urlPatterns = ["/request-param"])
class RequestParamServlet: HttpServlet() {

    override fun service(request: HttpServletRequest?, response: HttpServletResponse?) {

        if (request != null) {
            printParameters(request)
        } else {
            println("--- Request Error --- ")
        }

    }

    private fun printParameters(request: HttpServletRequest) {
        // Print All Parameters " key : value " format
        println("--- Print Request Parameters ---")
        printAllParam(request)
        println("--- End Request Parameters ---")
    }

    private fun printAllParam(request: HttpServletRequest) =
        request
            .parameterMap
            .asIterable()
            .forEach{
                // if single parameter
                if (request.getParameterValues(it.key).size == 1) println("${it.key} : ${request.getParameter(it.key)}")
                // if 
                else {
                    var params = mutableListOf<String>()
                    for (param in request.getParameterValues(it.key)) params.add(param)
                    println("${it.key} : ${params.joinToString(", ")}")
                }
            }
}