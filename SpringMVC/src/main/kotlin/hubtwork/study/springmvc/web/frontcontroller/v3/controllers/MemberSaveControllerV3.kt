package hubtwork.study.springmvc.web.frontcontroller.v3.controllers

import hubtwork.study.springmvc.data.Member
import hubtwork.study.springmvc.repository.MemberRepository
import hubtwork.study.springmvc.web.frontcontroller.ModelView
import hubtwork.study.springmvc.web.frontcontroller.v3.ControllerV3

class MemberSaveControllerV3: ControllerV3 {
    private val memberRepository = MemberRepository.instance

    override fun process(paramMap: Map<String, String>): ModelView {
        val name = paramMap["username"]!!
        val age = paramMap["age"]!!

        var member = memberRepository.save(Member(userName = name, age = age.toInt()))
        println("[ SYSTEM ] Member $member enrolled")
        val modelView = ModelView("save-result")
        modelView.model["member"] = member
        return modelView
    }
}