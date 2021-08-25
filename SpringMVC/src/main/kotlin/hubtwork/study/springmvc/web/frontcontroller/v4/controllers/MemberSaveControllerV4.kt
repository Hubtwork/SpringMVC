package hubtwork.study.springmvc.web.frontcontroller.v4.controllers

import hubtwork.study.springmvc.data.Member
import hubtwork.study.springmvc.repository.MemberRepository
import hubtwork.study.springmvc.web.frontcontroller.v4.ControllerV4

class MemberSaveControllerV4: ControllerV4 {

    private val memberRepository = MemberRepository.instance

    override fun process(paramMap: MutableMap<String, String>, model: MutableMap<String, Any>): String {
        val name = paramMap["username"]!!
        val age = paramMap["age"]!!.toInt()
        val member = memberRepository.save(Member(userName = name, age = age))
        model["member"] = member
        return "save-result"
    }
}