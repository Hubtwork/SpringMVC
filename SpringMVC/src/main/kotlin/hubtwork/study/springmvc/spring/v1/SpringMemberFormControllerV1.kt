package hubtwork.study.springmvc.spring.v1

import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.servlet.ModelAndView

/**
 * @Controller
 * - 스프링 빈으로 자동 등록.
 * - @Component 애너테이션을 포함하고 있어 컴포넌트 스캔 대상임
 * - SpringMVC 의 애너테이션 기반 컨트롤러 정의
 */
@Controller
class SpringMemberFormControllerV1 {

    /**
     * @RequestMapping
     * - 요청 정보를 매핑
     * - base Parameter : url / addon : method, header 등
     * - 해당 URI 스펙으로 들어온 요청을 매핑해줌
     *
     * ModelAndView
     * - 모델, 뷰 정보를 담은 SpringMVC 의 객체
     * - 뷰에 이용할 모델 추가 : .addObject(name: String, object: Object)
     */
    @RequestMapping("/springmvc/v1/members/new-form")
    fun process(): ModelAndView = ModelAndView("new-form")

}