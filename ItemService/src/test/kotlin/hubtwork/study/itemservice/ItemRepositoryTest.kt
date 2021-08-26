package hubtwork.study.itemservice

import hubtwork.study.itemservice.model.Item
import hubtwork.study.itemservice.repository.ItemRepository
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Test

class ItemRepositoryTest {

    private var itemRepository = ItemRepository()

    @AfterEach
    fun afterEach() {
        itemRepository.clearAllForTest()
    }

    @Test
    fun test1_save() {
        var item = Item(itemName = "test", price = 10000, quantity = 5)
        var saved = itemRepository.save(item)
        assert(item.itemName == saved.itemName)
    }

    @Test
    fun test2_findAll() {
        var item1 = Item(itemName = "test1", price = 10000, quantity = 5)
        var item2 = Item(itemName = "test2", price = 5000, quantity = 1)

        itemRepository.save(item1)
        itemRepository.save(item2)

        val result = itemRepository.findAll()
        assert(result.size == 2)
    }

    @Test
    fun test3_findById() {
        var item1 = Item(itemName = "test1", price = 10000, quantity = 5)
        var item2 = Item(itemName = "test2", price = 5000, quantity = 1)
        var item3 = Item(itemName = "test3", price = 2000, quantity = 3)
        var item4 = Item(itemName = "test4", price = 7000, quantity = 2)

        itemRepository.save(item1)
        itemRepository.save(item2)
        itemRepository.save(item3)
        itemRepository.save(item4)

        val result = itemRepository.findById(3)
        assert(itemRepository.findAll().size == 4)
        assert(result!!.itemName == item3.itemName)
    }

    @Test
    fun test4_update() {
        var item1 = Item(itemName = "test1", price = 10000, quantity = 5)
        itemRepository.save(item1)

        var item = Item(itemName = "special", price = 400, quantity = 20)
        itemRepository.update(1, item)

        print(itemRepository.findAll())
        val result = itemRepository.findById(1)
        assert(result!!.itemName == item.itemName)
        assert(result!!.price == item.price)
        assert(result!!.quantity == item.quantity)
    }

}