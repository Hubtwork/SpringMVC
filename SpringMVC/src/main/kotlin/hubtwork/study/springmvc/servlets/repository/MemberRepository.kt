package hubtwork.study.springmvc.servlets.repository

import hubtwork.study.springmvc.servlets.data.Member


class MemberRepository {

    companion object {
        val instance = MemberRepository()
    }

    private var store = hashMapOf<Long, Member>()
    private var sequence: Long = 0L

    fun save(member: Member): Member {
        member.id = ++sequence
        store[member.id!!] = member
        return member
    }

    fun findById(id: Long): Member? = store[id]

    fun findAll() = arrayListOf(store.values)

    fun clearStore() = store.clear()

}