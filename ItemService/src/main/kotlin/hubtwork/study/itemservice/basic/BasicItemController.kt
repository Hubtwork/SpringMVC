package hubtwork.study.itemservice.basic

import hubtwork.study.itemservice.model.Item
import hubtwork.study.itemservice.repository.ItemRepository
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.*
import org.springframework.web.servlet.mvc.support.RedirectAttributes
import javax.annotation.PostConstruct


@Controller
@RequestMapping("/basic/items")
class BasicItemController(
    private val itemRepository: ItemRepository
) {
    /**
     * Spring-Kotlin DI 방법
     *
     * 1. 필드
     *  ## 스프링 프레임워크에 종속 ( Bean 을 컨테이너에서 직접 가져옴 )
     *  > @Autowired
     *  > private lateinit var itemRepository: ItemRepository
     *
     * 2. 단일 생성자 : 클래스에 생성자가 하나일 경우만 가능
     *  ## 제일 안전함. 단일 책임원칙 및 Circular Dependency 파악에 용이
     *  > ( private val itemRepository: ItemRepository )
     *
     * 3. SETTER
     *  > @Autowired
     *  > fun setRepository(itemRepository: ItemRepository)
     *  > { this.itemRepository = itemRepository }
     *
     */

    /**
     * @PostConstruct
     * - DI 주입 후 초기화 하고 싶을 떄 활용 ( lazy init )
     */
    @PostConstruct
    fun init() {
        itemRepository.save(Item(null, "testA", 10000, 10))
        itemRepository.save(Item(null, "testB", 20000, 20))
    }

    @GetMapping
    fun items(model: Model): String {
        val items = itemRepository.findAll()
        model.addAttribute("items", items)
        return "basic/items"
    }

    @GetMapping("/{itemId}")
    fun item(
        @PathVariable itemId: Long,
        model: Model
    ): String {
        val item = itemRepository.findById(itemId)
        model.addAttribute("item", item)
        return "basic/item"
    }

    @GetMapping("/add")
    fun addForm() = "basic/addForm"

    /**
     * V1. @RequestParam 이용
     * V2. @ModelAttribute 이용
     * V3. Model 생략
     * V4. @ModelAttribute 생략
     * V5. Redirect 이용 - 직접 String Resolver 에 변수를 대입할 경우 URL Encoding 아니어서 위험
     * V6. RedirectAttributes 이용 - URL Encoding, PathVariable, QueryParameter 케어
     */
    @PostMapping("/add-v1")
    fun addItemV1(
        @RequestParam itemName: String,
        @RequestParam price: Int,
        @RequestParam quantity: Int,
        model: Model
    ) : String {
        var item = Item(itemName = itemName, price = price, quantity = quantity)
        itemRepository.save(item)
        model.addAttribute("item", item)
        print(itemRepository.findAll().size)
        return "basic/item"
    }

    @PostMapping("/add-v2")
    fun addItemV2(
        @ModelAttribute("item") item: Item,
        model: Model
    ) : String {
        itemRepository.save(item)
        print(itemRepository.findAll().size)
        return "basic/item"
    }

    @PostMapping("/add-v3")
    fun addItemV3(@ModelAttribute("item") item: Item) : String {
        itemRepository.save(item)
        println(itemRepository.findAll().size)
        return "basic/item"
    }

    @PostMapping("/add-v4")
    fun addItemV4(item: Item) : String {
        itemRepository.save(item)
        print(itemRepository.findAll())
        return "basic/item"
    }

    @PostMapping("/add-v5")
    fun addItemV5(item: Item) : String {
        itemRepository.save(item)
        return "redirect:/basic/items/${item.id}"
    }

    @PostMapping("/add")
    fun addItemV6(
        item: Item,
        redirectAttributes: RedirectAttributes
    ) : String {
        val saved = itemRepository.save(item)
        redirectAttributes.addAttribute("itemId", saved.id)
        redirectAttributes.addAttribute("status", true)
        /**
         * RedirectAttributes
         * - redirect UriPath 에 포함되어있는 Attribute 의 경우 PathVariable 로 매핑
         * - 그 외의 Attribute 는 QueryParameter 로 처리
         */
        return "redirect:/basic/items/{itemId}"
    }

    /**
     * 상품 수정 View
     * 상품 수정 Redirection POST
     *  - Spring 리다이렉트 view Resolver : 'redirect:' 키워드 사용, 이용된 @PathVariable 그대로 사용 가능
     *
     * HTML Form 전송 : GET, PUT 만 지원
     */
    @GetMapping("/{itemId}/edit")
    fun editForm(
        @PathVariable itemId: Long,
        model: Model
    ): String {
        val item = itemRepository.findById(itemId)
        model.addAttribute("item", item)
        return "basic/editForm"
    }

    @PostMapping("/{itemId}/edit")
    fun editItem(
        @PathVariable itemId: Long,
        @ModelAttribute item: Item
    ): String {
        itemRepository.save(item)
        return "redirect:/basic/items/{itemid}"
    }
}
