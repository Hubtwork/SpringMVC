package hubtwork.study.springmvc

import hubtwork.study.springmvc.data.Member
import hubtwork.study.springmvc.repository.MemberRepository
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Test

class MemberRepositoryTest {

    private var memberRepository = MemberRepository()

    @AfterEach
    fun afterEach() = memberRepository.clearStore()

    @Test
    fun save_test() {
        val member = Member(null, userName = "tester", age = 20)
        val savedMember = memberRepository.save(member)
        val findMember = savedMember.id?.let { memberRepository.findById(it) }
        // Null Check added
        assert(findMember != null)
        if (findMember != null) {
            assert(findMember == savedMember)
        }
    }

    @Test
    fun findAll_test() {
        var member1 = Member(null, userName = "tester", age = 20)
        member1 = memberRepository.save(member1)
        var member2 = Member(null, userName = "tester", age = 20)
        member2 = memberRepository.save(member2)

        val saved = memberRepository.findAll()
        assert(saved.size == 2)
        assert(saved.contains(member1))
        assert(saved.contains(member2))
    }
}