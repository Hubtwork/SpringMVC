package hubtwork.study.springmvcproject.basic.requestmapping

import org.springframework.web.bind.annotation.*

/**
 * 명세 - 회원 시스템
 *
 * GET "/users" - 회원 리스트 조회
 * POST "/users/register" - 회원 등록
 * GET "/users/{userId}" - 특정 회원 조회
 * PATCH "/users/{userId}" - 해당 회원 정보 수정
 * DELETE "/users/{userId}" - 해당 회원 삭제
 *
 */
@RestController
@RequestMapping("/mapping/users")
class MappingClassController {

    @GetMapping
    fun users() = "USER LIST"

    @PostMapping("/register")
    fun addUser() = "ADD USER"

    @GetMapping("/{userId}")
    fun getUser(@PathVariable("userId") userId: String) = "DETAIL : $userId"

    @PatchMapping("/{userId}")
    fun patchUser(@PathVariable("userId") userId: String) = "UPDATE : $userId"

    @DeleteMapping("/{userId}")
    fun deleteUser(@PathVariable("userId") userId: String) = "DELETE : $userId"

}