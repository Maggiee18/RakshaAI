package com.example.rakshaai.domain.model

/**
 * Domain representation of an incident report.
 * Milestone 2 maps this to and from a Room entity; the UI only ever sees this type.
 */
data class Incident(
    val id: Long,
    val title: String,
    val description: String,
    val category: IncidentCategory,
    val timestampMillis: Long,
    val location: String?,
    val status: IncidentStatus,
    val notes: String?,
)

enum class IncidentCategory {
    HARASSMENT,
    STALKING,
    THEFT,
    ASSAULT,
    ACCIDENT,
    MEDICAL,
    OTHER,
}

enum class IncidentStatus {
    OPEN,
    REPORTED,
    RESOLVED,
}
