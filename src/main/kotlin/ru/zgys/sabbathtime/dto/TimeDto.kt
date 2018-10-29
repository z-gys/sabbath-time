package ru.zgys.sabbathtime.dto

import com.fasterxml.jackson.annotation.JsonProperty

class TimeDto() {
        @get:JsonProperty("הזמן")
        lateinit var time: String
        @get:JsonProperty("קו רוחב")
        var lat: Double = 0.0
        @get:JsonProperty("קו אורך")
        var lon: Double = 0.0
        @get:JsonProperty("את העיר")
        lateinit var city: String

        constructor(time: String, lat: Double, lon: Double, city: String) : this() {
                this.time = time
                this.lat = lat
                this.lon = lon
                this.city = city
        }
}
