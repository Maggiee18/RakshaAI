package com.example.rakshaai.ui.incidents

import androidx.annotation.StringRes
import com.example.rakshaai.R
import com.example.rakshaai.domain.model.IncidentCategory
import com.example.rakshaai.domain.model.IncidentStatus

// Maps domain enums to localized labels. Kept in the UI layer so the domain stays free of Android resources.

@StringRes
fun IncidentCategory.labelRes(): Int = when (this) {
    IncidentCategory.HARASSMENT -> R.string.incident_category_harassment
    IncidentCategory.STALKING -> R.string.incident_category_stalking
    IncidentCategory.THEFT -> R.string.incident_category_theft
    IncidentCategory.ASSAULT -> R.string.incident_category_assault
    IncidentCategory.ACCIDENT -> R.string.incident_category_accident
    IncidentCategory.MEDICAL -> R.string.incident_category_medical
    IncidentCategory.OTHER -> R.string.incident_category_other
}

@StringRes
fun IncidentStatus.labelRes(): Int = when (this) {
    IncidentStatus.OPEN -> R.string.incident_status_open
    IncidentStatus.REPORTED -> R.string.incident_status_reported
    IncidentStatus.RESOLVED -> R.string.incident_status_resolved
}
