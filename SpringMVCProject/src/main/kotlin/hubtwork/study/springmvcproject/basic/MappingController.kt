package hubtwork.study.springmvcproject.basic

import org.slf4j.LoggerFactory
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class MappingController {

    private val logger = LoggerFactory.getLogger(javaClass)

    /**
     * @RequestMapping
     * - 지정 uri = /... 일 때, /.../ 도 허용
     * - HTTP 메서드 모두 허용 GET, HEAD, POST, PUT, PATCH, DELETE
     * - PathVariable 허용시, URI Parameter Mapping
     */
    @RequestMapping( "/mapping-test")
    fun mappingTestRouter(): String {
        logger.info("Request Mapping Test")
        return "successful Request"
    }

    @RequestMapping("/mapping-get")
    fun mappingGetTestRouter(): String {
        logger.info("Get Request Mapping Test")
        return "successful Get Request"
    }

    @RequestMapping("/mapping-get-2/{userName}")
    fun mappingGetTestRouterWithPathVariable(@PathVariable("userName") user: String): String {
        logger.info("Get Request with PathVariable $user")
        return "successful Get Request, \n Hi. $user"
    }

    @RequestMapping("/mapping-get-3/{groupName}/{userName}")
    fun mappingGetTestRouterWithPathVariables(
        @PathVariable("groupName") group: String,
        @PathVariable("userName") user: String
    ): String {
        logger.info("Get Request with PathVariable $user in $group")
        return "successful Get Request, \n Hi. $user [ Group : $group ]"
    }
}