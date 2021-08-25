package hubtwork.study.springmvc.repository

import hubtwork.study.springmvc.data.Member


class MemberRepository {

    companion object {
        val instance = MemberRepository()
    }

    /**
     *  동시성 미고려 ( HashMap )
     *
     *  실무 > ConcurrentHashMap , AtomicLong 등 사용 고려
     */
    private var store: MutableMap<Long, Member> = HashMap()
    private var sequence: Long = 0L

    fun save(member: Member): Member {
        member.id = ++sequence
        store[member.id!!] = member
        return member
    }

    fun findById(id: Long): Member? = store[id]

    fun findAll() = ArrayList(store.values)

    fun clearStore() = store.clear()

}