package hubtwork.study.springmvcproject.basic.response

import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.servlet.ModelAndView

@Controller
class ResponseViewController {

    /**
     * ModelAndView : 뷰 템플릿을 이용한 리스폰스
     *  뷰 템플릿 경로 : src/main/resources/template
     *
     *  응답
     *      1. String 반환
     *          - @ResponseBody 있는 경우 : http body message 통해 전달
     *          - 없는 경우 : View resolver 통해 해당 경로 / 논리이름을 가진 뷰 템플릿을 렌더링
     *      2. Void 반환 ( 비권장 )
     *          - @Controller + [ HttpServletResponse.Writer , OutputStream, etc .. ] : 해당 처리를 통해 http body message 전달
     *          - 아닐 경우 : URL 을 기준으로 논리 뷰를 반환
     *
     */
    @RequestMapping("/response-view-v1")
    fun modelAndView() = ModelAndView("response/hello").addObject("data", "Test Data")

    @RequestMapping("/response-view-v2")
    fun responseViewV2(model: Model) : String {
        model.addAttribute("data", "hello")
        return "response/hello"
    }

    @RequestMapping("/response/hello")
    fun responseViewV3(model: Model) {
        model.addAttribute("data", "hello")
    }
}