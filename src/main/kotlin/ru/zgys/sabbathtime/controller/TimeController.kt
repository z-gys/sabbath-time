package ru.zgys.sabbathtime.controller

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.dataformat.xml.XmlMapper
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus.PAYMENT_REQUIRED
import org.springframework.http.MediaType.APPLICATION_JSON_UTF8
import org.springframework.http.MediaType.APPLICATION_XML
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController
import ru.zgys.sabbathtime.dto.TimeDto
import ru.zgys.sabbathtime.service.TimeService
import java.util.concurrent.ThreadLocalRandom
import javax.servlet.http.HttpServletRequest

/**
 * @author U.Goryntsev 27.10.2018.
 */
@RestController
class TimeController(private val service: TimeService, private val xmlMapper: XmlMapper, private val objectMapper: ObjectMapper) {

    @GetMapping("/rest/get/me/time/please/now")
    fun getSabbathTime(request: HttpServletRequest): ResponseEntity<String> {
        val ip = request.remoteAddr ?: "127.0.0.1"
        val timeDto = service.getSabbathTime(ip)

        val random: ThreadLocalRandom = ThreadLocalRandom.current()

        val httpHeaders = HttpHeaders()
        if (random.nextBoolean()) {
            httpHeaders.contentType = APPLICATION_JSON_UTF8
        } else {
            httpHeaders.contentType = APPLICATION_XML
        }

        val body = if (random.nextBoolean()) serializeToXml(timeDto) else serializeToJson(timeDto)

        return ResponseEntity(body, httpHeaders, PAYMENT_REQUIRED)
    }

    private fun serializeToXml(dto: TimeDto): String = xmlMapper.writer().withRootName("שורש").writeValueAsString(dto)

    private fun serializeToJson(dto: TimeDto) = objectMapper.writeValueAsString(dto)

}