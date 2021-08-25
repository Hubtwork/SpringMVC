package hubtwork.study.springmvc.servlet.basic

import hubtwork.study.springmvc.repository.MemberRepository
import javax.servlet.annotation.WebServlet
import javax.servlet.http.HttpServlet
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@WebServlet(name = "memberListServlet", urlPatterns = ["/servlet/members"])
class MemberListServlet: HttpServlet() {

    private val memberRepository = MemberRepository.instance

    override fun service(request: HttpServletRequest?, response: HttpServletResponse?) {

        if (response != null) {

            response.contentType = "text/html"
            response.characterEncoding = "utf-8"

            val writer = response.writer
            writer.write(
                "<html>" +
                        "<head>" +
                        "    <meta charset=\"UTF-8\">" +
                        "    <title>Title</title>" +
                        "</head>" +
                        "<body>" +
                        "<a href=\"/servlet/members/new-form\">등록하기</a>" +
                        "<table>" +
                        "    <thead>" +
                        "    <th>id</th>" +
                        "    <th>username</th>" +
                        "    <th>age</th>" +
                        "    </thead>" +
                        "    <tbody>"
            )
            for( member in memberRepository.findAll()) {
                writer.write(
                    "   <tr>" +
                            "   <td> ${member.id} </td>" +
                            "   <td> ${member.userName} </td>" +
                            "   <td> ${member.age} </td>" +
                            "</tr>"
                )
            }
            writer.write(
                "</tbody>" +
                        "</table>" +
                        "</body>" +
                        "</html>"
            )
        }
    }
}