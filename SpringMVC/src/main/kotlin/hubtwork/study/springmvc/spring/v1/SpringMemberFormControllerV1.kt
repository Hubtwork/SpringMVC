package hubtwork.study.springmvc.spring.v1

import hubtwork.study.springmvc.data.Member
import hubtwork.study.springmvc.repository.MemberRepository
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.servlet.ModelAndView
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse


@Controller
class SpringMemberFormControllerV1 {

    @RequestMapping("/springmvc/v1/members/new-form")
    fun process(): ModelAndView = ModelAndView("new-form")

}