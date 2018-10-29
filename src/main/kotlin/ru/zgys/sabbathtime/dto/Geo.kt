package ru.zgys.sabbathtime.dto

import com.fasterxml.jackson.annotation.JsonFormat
import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.annotation.JsonProperty
import java.time.LocalDate

/**
 * @author U.Goryntsev 27.10.2018.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
data class GeoDto (
        val ip : String,
        val city : City? = City.DEFAULT,
        val region: Region? = Region.DEFAULT,
        val country: Country? = Country.DEFAULT,
        val error: String,
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy.MM.dd") val created: LocalDate,
        val timestamp: Long
) {
    companion object {
        val DEFAULT = GeoDto(
                "127.0.0.1",
                City.DEFAULT,
                Region.DEFAULT,
                Country.DEFAULT,
                "",
                LocalDate.now(),
                LocalDate.now().toEpochDay()
        )
    }
}

data class City(
        val id: Long,
        val lat: Double,
        val lon: Double,
        @JsonProperty("name_ru") val nameRu: String,
        @JsonProperty("name_en") val nameEn: String,
        @JsonProperty("name_de") val nameDe: String,
        @JsonProperty("name_fr") val nameFr: String,
        @JsonProperty("name_it") val nameIt: String,
        @JsonProperty("name_es") val nameEs: String,
        @JsonProperty("name_pt") val namePt: String,
        val okato: String,
        val vk: Long,
        val population: Long,
        val tel: String,
        val post: String
) {
    companion object {
        val DEFAULT = City(
                524901,
                55.75222,
                37.61556,
                "Москва",
                "Moscow",
                "Moskau",
                "Moscou",
                "Mosca",
                "Moscú",
                "Moscovo",
                "45",
                1,
                12330126,
                "495,496,498,499",
                "101xxx:135xxx"
                )
    }

}

data class Region(
        val id: Long,
        val lat: Double,
        val lon: Double,
        @JsonProperty("name_ru") val nameRu: String,
        @JsonProperty("name_en") val nameEn: String,
        @JsonProperty("name_de") val nameDe: String,
        @JsonProperty("name_fr") val nameFr: String,
        @JsonProperty("name_it") val nameIt: String,
        @JsonProperty("name_es") val nameEs: String,
        @JsonProperty("name_pt") val namePt: String,
        val iso: String,
        val timezone: String,
        val okato: String,
        val auto: String,
        val vk: Long,
        val utc: Int
) {
    companion object {
        val DEFAULT = Region(
                524894,
                55.76,
                37.61,
                "Москва",
                "Moscow",
                "Moskau",
                "Moscou",
                "Mosca",
                "Moscú",
                "Moscovo",
                "RU-MOW",
                "Europe/Moscow",
                "45",
                "77, 97, 99, 177, 197, 199, 777",
                0,
                3
        )
    }
}

data class Country(
        val id: Long,
        val iso: String,
        val continent: String,
        val lat: Double,
        val lon: Double,
        @JsonProperty("name_ru") val nameRu: String,
        @JsonProperty("name_en") val nameEn: String,
        @JsonProperty("name_de") val nameDe: String,
        @JsonProperty("name_fr") val nameFr: String,
        @JsonProperty("name_it") val nameIt: String,
        @JsonProperty("name_es") val nameEs: String,
        @JsonProperty("name_pt") val namePt: String,
        val timezone: String,
        val area: Long,
        val population: Long,
        @JsonProperty("capital_id") val capitalId: Long,
        @JsonProperty("capital_ru") val capitalRu: String,
        @JsonProperty("capital_en") val capitalEn: String,
        @JsonProperty("cur_code") val curCode: String,
        val phone: String,
        val neighbours: String,
        val vk: Long,
        val utc: Int
) {
    companion object {
        val DEFAULT = Country (
                185,
                "RU",
                "EU",
                60.0,
                100.0,
                "Россия",
                "Russia",
                "Russland",
                "Russie",
                "Russia",
                "Rusia",
                "Rússia",
                "Europe/Moscow",
                17100000,
                140702000,
                524901,
                "Москва",
                "Moscow",
                "RUB",
                "7",
                "GE,CN,BY,UA,KZ,LV,PL,EE,LT,FI,MN,NO,AZ,KP",
                1,
                3
        )
    }
}

data class Coordinates (val lat: Double, val lon: Double, val city: String, val timezone: String)

fun GeoDto.toCoordinates() = Coordinates((this.city?: City.DEFAULT).lat, (this.city?:City.DEFAULT).lon, (this.city?:City.DEFAULT).namePt, (this.region?:Region.DEFAULT).timezone)