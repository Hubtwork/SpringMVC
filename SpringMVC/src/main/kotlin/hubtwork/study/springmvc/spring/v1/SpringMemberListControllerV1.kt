package hubtwork.study.springmvc.spring.v1

import hubtwork.study.springmvc.repository.MemberRepository
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.servlet.ModelAndView

@Controller
class SpringMemberListControllerV1 {

    private val memberRepository = MemberRepository.instance

    @RequestMapping("/springmvc/v1/members")
    fun process(): ModelAndView {
        val members = memberRepository.findAll()
        val modelAndView = ModelAndView("members")
        modelAndView.addObject("members", members)
        return modelAndView
    }

}