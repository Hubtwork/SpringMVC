package hubtwork.study.springmvc.web.frontcontroller.v4.controllers

import hubtwork.study.springmvc.repository.MemberRepository
import hubtwork.study.springmvc.web.frontcontroller.v4.ControllerV4

class MemberListControllerV4: ControllerV4 {

    private val memberRepository = MemberRepository.instance

    override fun process(paramMap: MutableMap<String, String>, model: MutableMap<String, Any>): String {
        val members = memberRepository.findAll()
        model["members"] = members
        return "members"
    }

}