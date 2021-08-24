package hubtwork.study.springmvc.servlets.util

import org.springframework.util.StreamUtils
import java.nio.charset.StandardCharsets
import javax.servlet.http.HttpServletRequest

object HttpRequestUtil {

    /**
     * Headers
     */
    fun utilizedHeader(request : HttpServletRequest) {
        println("--- Header Utils - start ---")
        println("Host ( Port ) : ${request.serverName} ( ${request.serverPort} )\n")
        println("Locale : ${request.locale}")
        print("All Locales : ")
        request.locales.asIterator().forEachRemaining{ print("$it ")}
        println()
        if (request.cookies != null) {
            println("--- Cookies ---")
            println("${request.cookies.asIterable().forEach{ println(" ${it.name} - ${it.value}") }} ")
        }
        println("--- Header Utils - end ---")
    }

    fun extractHeader(request : HttpServletRequest) {
        println("--- Headers - start ---")
        request.headerNames.asIterator()
            .forEachRemaining{ it -> println(it + " = " + request.getHeader(it))}
        println("--- Headers - end ---")
    }

    fun extractRequestInfo(request: HttpServletRequest) {
        println("--- REQUEST-LINE - start ---")
        println("METHOD = " + request.method)
        println("PROTOCOL = " + request.protocol)
        println("SCHEME = " + request.scheme)
        println("URL = " + request.requestURL)
        println("URI = " + request.requestURI)
        println("QUERIES = " + request.queryString)
        println("SECURE = " + request.isSecure)
        println("--- REQUEST-LINE - end ---")
    }


    /**
     * Parsing Query Parameters or Messages
     */

    fun printParameters(request: HttpServletRequest) {
        // Print All Parameters " key : value " format
        println("--- Print Request Parameters ---")
        printAllParam(request)
        println("--- End Request Parameters ---")
    }

    private fun printAllParam(request: HttpServletRequest) =
        request
            .parameterMap
            .asIterable()
            .forEach{
                // if single parameter
                if (request.getParameterValues(it.key).size == 1) println("${it.key} : ${request.getParameter(it.key)}")
                // if multiple parameters for one key, iterating and print
                else {
                    var params = mutableListOf<String>()
                    for (param in request.getParameterValues(it.key)) params.add(param)
                    println("${it.key} : ${params.joinToString(", ")}")
                }
            }

    // using
    fun readMessageBodyWithInputStream(request: HttpServletRequest) {
        val inputStream = request.inputStream
        val msgBody = StreamUtils.copyToString(inputStream, StandardCharsets.UTF_8)
        println("--- Received Message ---")
        println(msgBody)
        println("-".repeat(30))
    }
}