package ru.zgys.sabbathtime.service

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.cache.annotation.Cacheable
import org.springframework.stereotype.Service
import org.springframework.web.client.RestTemplate
import ru.zgys.sabbathtime.dto.Coordinates
import ru.zgys.sabbathtime.dto.GeoDto
import ru.zgys.sabbathtime.dto.toCoordinates

@Service
class GeoService(private val client: RestTemplate) {
    companion object {
        val log: Logger = LoggerFactory.getLogger(GeoService::class.java)
        const val URL = "http://api.sypexgeo.net/json/%s"
    }

    fun getCoordinates(ip: String): Coordinates = (
            if (ip == "127.0.0.1")
                GeoDto.DEFAULT
            else
               requestCoordinates(ip)
            ).toCoordinates()

    @Cacheable("ip2geo")
    fun requestCoordinates(ip: String): GeoDto {
        log.info("Requesting data for ip {}", ip)
        return client.getForObject(String.format(URL, ip), GeoDto::class.java) ?: GeoDto.DEFAULT
    }
}

