package hubtwork.study.itemservice.repository

import hubtwork.study.itemservice.model.Item

class ItemRepository {
    companion object {
        private var store: MutableMap<Long, Item> = HashMap()
        private var sequence : Long = 0L
    }

    fun save(item: Item) : Item {
        item.id = ++sequence
        store[sequence] = item
        return item
    }

    fun findById(id: Long): Item? = store[id]

    fun findAll(): ArrayList<Item> = ArrayList(store.values)

    fun update(id: Long, updateParam: Item) { store[id]?.update(updateParam) }

    fun clearAll() { store.clear() }

    fun clearAllForTest() {
        sequence = 0L
        store.clear()
    }

}