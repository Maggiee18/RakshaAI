package com.example.rakshaai.util

import java.time.Instant
import java.time.ZoneId
import java.time.format.DateTimeFormatter
import java.time.format.FormatStyle

private val displayFormatter: DateTimeFormatter =
    DateTimeFormatter.ofLocalizedDateTime(FormatStyle.MEDIUM, FormatStyle.SHORT)

/** Formats epoch milliseconds in the device time zone and locale, for display only. */
fun formatTimestamp(epochMillis: Long, zoneId: ZoneId = ZoneId.systemDefault()): String =
    displayFormatter.format(Instant.ofEpochMilli(epochMillis).atZone(zoneId))
