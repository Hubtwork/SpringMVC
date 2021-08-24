package hubtwork.study.springmvc

import hubtwork.study.springmvc.servlets.data.Member
import hubtwork.study.springmvc.servlets.repository.MemberRepository
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Test

class MemberRepositoryTest {

    var memberRepository = MemberRepository()

    @AfterEach
    fun afterEach() = memberRepository.clearStore()

    @Test
    fun save_test() {
        val member = Member(null, userName = "tester", age = 20)
        val savedMember = memberRepository.save(member)
        val findMember = savedMember.id?.let { memberRepository.findById(it) }
        if (findMember != null) {
            assert(findMember == savedMember)
        }
    }
}