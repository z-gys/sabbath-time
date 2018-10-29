package ru.zgys.sabbathtime.service

import com.luckycatlabs.sunrisesunset.SunriseSunsetCalculator
import org.springframework.stereotype.Service
import ru.zgys.sabbathtime.dto.Coordinates
import ru.zgys.sabbathtime.dto.TimeDto
import ru.zgys.sabbathtime.exception.SabbathTimeException
import java.util.*
import java.util.Calendar.*


/**
 * @author U.Goryntsev 27.10.2018.
 */
@Service
class TimeService(private val geoService: GeoService) {
    fun getSabbathTime(ip: String): TimeDto {
        val coordinates = geoService.getCoordinates(ip)
        if (isSabbathTime(coordinates)) {
            throw SabbathTimeException()
        }
        val sabbath = computeSunset(coordinates, nextFriday())
        val timeToSabbath = sabbath.timeInMillis - Calendar.getInstance().timeInMillis
        return TimeDto(
                timeToSabbath.toString(),
                coordinates.lat,
                coordinates.lon,
                coordinates.city
        )
    }

    fun nextFriday(): Calendar {
        val today = Calendar.getInstance()
        val dayOfWeek = today.get(DAY_OF_WEEK)
        var daysUntilNextFriday = FRIDAY - dayOfWeek
        if (daysUntilNextFriday < 0) {
            daysUntilNextFriday += 7
        }
        val nextFriday = today.clone() as Calendar
        nextFriday.add(DAY_OF_WEEK, daysUntilNextFriday)
        if (nextFriday.get(WEEK_OF_YEAR) % 2 == 0) {
            nextFriday.add(DAY_OF_WEEK, 7)
        }
        return nextFriday
    }

    fun isSabbathTime(coordinates: Coordinates): Boolean {
        val now = Calendar.getInstance()
        return (now.get(DAY_OF_WEEK) == FRIDAY && (now.timeInMillis > computeSunset(coordinates, now).timeInMillis)) ||
                (now.get(DAY_OF_WEEK) == SATURDAY && now.timeInMillis < computeSunset(coordinates, now).timeInMillis)
    }

    fun computeSunset(coordinates: Coordinates, date: Calendar) =
            SunriseSunsetCalculator.getSunset(coordinates.lat, coordinates.lon, TimeZone.getTimeZone(coordinates.timezone), date, 6.0)!!
}