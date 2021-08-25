package hubtwork.study.springmvc.spring.v1

import hubtwork.study.springmvc.data.Member
import hubtwork.study.springmvc.repository.MemberRepository
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.servlet.ModelAndView
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@Controller
class SpringMemberSaveControllerV1 {

    private val memberRepository = MemberRepository.instance

    @RequestMapping("/springmvc/v1/members/save")
    fun process(request: HttpServletRequest, response: HttpServletResponse): ModelAndView {
        val name = request.getParameter("username")
        val age = request.getParameter("age").toInt()
        val member = memberRepository.save(Member(userName = name, age = age))

        val modelAndView = ModelAndView("save-result")
        modelAndView.addObject("member", member)
        return modelAndView
    }
}