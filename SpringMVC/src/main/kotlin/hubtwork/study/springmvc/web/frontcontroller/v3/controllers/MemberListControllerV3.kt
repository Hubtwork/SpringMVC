package hubtwork.study.springmvc.web.frontcontroller.v3.controllers

import hubtwork.study.springmvc.data.Member
import hubtwork.study.springmvc.repository.MemberRepository
import hubtwork.study.springmvc.web.frontcontroller.ModelView
import hubtwork.study.springmvc.web.frontcontroller.v3.ControllerV3

class MemberListControllerV3: ControllerV3 {

    private val memberRepository = MemberRepository.instance

    override fun process(paramMap: Map<String, String>): ModelView {
        val members = memberRepository.findAll()
        val modelView = ModelView("members")
        modelView.model["members"] = members
        return modelView
    }

}