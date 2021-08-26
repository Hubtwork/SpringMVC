package hubtwork.study.itemservice.model

data class Item(
    var id: Long? = null,
    var itemName: String,
    var price: Int,
    var quantity: Int
) {
    fun update(item: Item) {
        itemName = item.itemName
        price = item.price
        quantity = item.quantity
    }
}