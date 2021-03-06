package dev.jacaro.mc.scriptexecutor.kotlin.automation

import org.bukkit.configuration.ConfigurationSection
import dev.jacaro.mc.scriptexecutor.kotlin.config.ConfigManager
import java.text.SimpleDateFormat
import java.util.*



data class Timing(val delay: String? = null, val period: String? = null, val date: Date? = null)

object Timings {

    val dateFormatter = SimpleDateFormat("yyyy-MM-dd HH:mm:ss")

    init {
        dateFormatter.timeZone = if (ConfigManager.timeZoneOverride == null)
            TimeZone.getDefault()
        else
            TimeZone.getTimeZone(ConfigManager.timeZoneOverride)
    }

    fun factory(file: ConfigurationSection): Timing {
        return if (file.contains("date"))  {
            val rawDate = file.getString("date")
            val period = file.getString("period")

            val date = dateFormatter.parse(rawDate)

            Timing(period = period, date = date)
        } else {
            val delay = file.getString("delay")
            val period = file.getString("period")

            Timing(delay, period)
        }
    }

    fun write(file: ConfigurationSection, timing: Timing) {
        if (timing.date == null)
            file["delay"] = timing.delay ?: "0s"
        else
            file["date"] = dateFormatter.format(timing.date)

        if (timing.period != null)
            file["period"] = timing.period
    }
}