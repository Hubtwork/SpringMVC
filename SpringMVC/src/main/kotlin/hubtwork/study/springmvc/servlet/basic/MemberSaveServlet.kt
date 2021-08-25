package hubtwork.study.springmvc.servlet.basic

import hubtwork.study.springmvc.data.Member
import hubtwork.study.springmvc.repository.MemberRepository
import javax.servlet.annotation.WebServlet
import javax.servlet.http.HttpServlet
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@WebServlet(name = "memberSaveServlet", urlPatterns = ["/servlet/members/save"])
class MemberSaveServlet: HttpServlet() {

    private val memberRepository = MemberRepository.instance

    override fun service(request: HttpServletRequest?, response: HttpServletResponse?) {

        if (request != null) {
            val name = request.getParameter("username")
            val age = request.getParameter("age").toInt()
            // save and get updated ID from system
            var member = Member(userName = name, age = age)
            member = memberRepository.save(member)
            println("[ SYSTEM ] Member : $member")

            if(response != null) {

                response.contentType = "text/html"
                response.characterEncoding = "utf-8"

                val writer = response.writer
                writer.write(
                    "<html>\n" +
                    "<head>\n" +
                    " <meta charset=\"UTF-8\">\n" + "</head>\n" +
                    "<body>\n" +
                    "성공\n" +
                    "<ul>\n" +
                    "   <li>id= " + member.id + "</li>\n" +
                    "   <li>username= " + member.userName + "</li>\n" +
                    "   <li>age= " + member.age + "</li>\n" + "</ul>\n" +
                    "<a href=\"/servlet/members\"> 리스트로 돌아가기 </a>\n" + "</body>\n" +
                    "</html>"
                )
            }
        }

    }
}