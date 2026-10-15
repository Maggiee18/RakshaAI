package com.example.rakshaai.util

import androidx.annotation.StringRes
import com.example.rakshaai.R

/** A public helpline the user can dial manually. */
data class EmergencyResource(
    val number: String,
    @StringRes val nameRes: Int,
    @StringRes val descriptionRes: Int,
)

object EmergencyResources {

    /** National helplines in India. 112 is listed first because it reaches police, fire and ambulance. */
    val india: List<EmergencyResource> = listOf(
        EmergencyResource("112", R.string.resource_112_name, R.string.resource_112_description),
        EmergencyResource("1091", R.string.resource_1091_name, R.string.resource_1091_description),
        EmergencyResource("181", R.string.resource_181_name, R.string.resource_181_description),
        EmergencyResource("108", R.string.resource_108_name, R.string.resource_108_description),
        EmergencyResource("1098", R.string.resource_1098_name, R.string.resource_1098_description),
    )

    const val PRIMARY_EMERGENCY_NUMBER = "112"

    /**
     * Builds a `tel:` URI string. Keeps digits and a single leading plus sign; removes spaces,
     * dashes and brackets. Throws [IllegalArgumentException] when no digits remain.
     */
    fun toDialUri(number: String): String {
        val trimmed = number.trim()
        val sanitized = buildString {
            trimmed.forEachIndexed { index, char ->
                if (char.isDigit() || (char == '+' && index == 0)) append(char)
            }
        }
        require(sanitized.any { it.isDigit() }) { "Phone number must contain at least one digit" }
        return "tel:$sanitized"
    }
}
