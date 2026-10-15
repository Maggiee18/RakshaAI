package com.example.rakshaai.domain.model

/**
 * A person the user trusts. Stored only on the device (Milestone 3).
 */
data class TrustedContact(
    val id: Long,
    val name: String,
    val phoneNumber: String,
    val relationship: String?,
    val isEmergencyContact: Boolean,
)
