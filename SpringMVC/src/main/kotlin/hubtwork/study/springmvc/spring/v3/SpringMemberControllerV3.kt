package hubtwork.study.springmvc.spring.v3

import hubtwork.study.springmvc.data.Member
import hubtwork.study.springmvc.repository.MemberRepository
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam


@Controller
@RequestMapping("/springmvc/v3/members")
class SpringMemberControllerV3 {

    /**
     * Model
     * - 파라미터로 모델을 받을 경우, SpringMVC 는 이에 대한 편리한 작업을 가능하게 해줌
     * - ModelAndView 를 반환하거나 View 의 파일명을 반환해도 SpringMVC 가 찾아서 매핑
     *
     * @RequestParam
     * - request.getParameter() 와 같은 역할
     * - 타입 지정시 해당 타입으로 변환해 로드해줌 ( GET / POST form 모두 지원 )
     *
     * @GetMapping, @PostMapping, @PutMapping ...
     * - 각 HTTP Method 별로 Mapping 어노테이션이 제공 ( 가독성 향상 )
     * - 각 어노테이션들은 해당 HTTP Method 에 대한 RequestMapping 을 반환하는 형태로 구현되어있음
     *
     */
    private val memberRepository = MemberRepository.instance

    @GetMapping
    fun showAllMember(model: Model): String {
        model.addAttribute("members", memberRepository.findAll())
        return "members"
    }

    @GetMapping("/new-form")
    fun registerMember(): String = "new-form"

    @PostMapping("/save")
    fun saveMember(
        @RequestParam("username") name: String,
        @RequestParam("age") age: Int,
        model: Model
    ): String {
        val member = memberRepository.save(Member(userName = name, age = age))
        model.addAttribute("member", member)
        return "save-result"
    }


}