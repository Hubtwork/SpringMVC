package hubtwork.study.springmvc.servlets.request

import javax.servlet.annotation.WebServlet
import javax.servlet.http.HttpServlet

@WebServlet(name = "responseHeaderServlet", urlPatterns = ["/response-header"])
class ResponseHeaderServlet: HttpServlet() {
}