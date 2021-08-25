package hubtwork.study.springmvc.spring.v2

import hubtwork.study.springmvc.data.Member
import hubtwork.study.springmvc.repository.MemberRepository
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.servlet.ModelAndView
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@Controller
@RequestMapping("/springmvc/v2/members")
class SpringMemberControllerV2 {

    /**
     * @RequestMapping
     * - 인자가 없을 경우, 상위 ( 클래스의 대표 URI ) 매핑
     * - 메소드 단위로 적용되므로 한 클래스에 여러 RequestMapping 가능
     * - 클래스 단위에 둘 경우, 중복적용되는 URI 매핑 가능
     * -
     */
    private val memberRepository = MemberRepository.instance

    @RequestMapping
    fun showAllMembers(): ModelAndView {
        val modelAndView = ModelAndView("members")
        modelAndView.addObject("members", memberRepository.findAll())
        return modelAndView
    }

    @RequestMapping("/new-form")
    fun registerMember(): ModelAndView = ModelAndView("new-form")

    @RequestMapping("/save")
    fun saveMember(request: HttpServletRequest, response: HttpServletResponse): ModelAndView {
        val name = request.getParameter("username")
        val age = request.getParameter("age").toInt()
        val member = memberRepository.save(Member(userName = name, age = age))
        val modelAndView = ModelAndView("save-result")
        modelAndView.addObject("member", member)
        return modelAndView
    }



}