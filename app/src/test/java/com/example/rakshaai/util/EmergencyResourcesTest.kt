package com.example.rakshaai.util

import org.junit.Assert.assertEquals
import org.junit.Assert.assertThrows
import org.junit.Assert.assertTrue
import org.junit.Test

class EmergencyResourcesTest {

    @Test
    fun toDialUri_plainNumber_returnsTelUri() {
        assertEquals("tel:112", EmergencyResources.toDialUri("112"))
    }

    @Test
    fun toDialUri_removesSpacesDashesAndBrackets() {
        assertEquals("tel:+919876543210", EmergencyResources.toDialUri(" +91 (98765)-43210 "))
    }

    @Test
    fun toDialUri_keepsOnlyLeadingPlus() {
        assertEquals("tel:+9112", EmergencyResources.toDialUri("+91+12"))
    }

    @Test
    fun toDialUri_noDigits_throws() {
        assertThrows(IllegalArgumentException::class.java) {
            EmergencyResources.toDialUri("call me")
        }
    }

    @Test
    fun indiaResources_startWith112_andHaveUniqueNumbers() {
        val numbers = EmergencyResources.india.map { it.number }
        assertEquals(EmergencyResources.PRIMARY_EMERGENCY_NUMBER, numbers.first())
        assertEquals(numbers.size, numbers.toSet().size)
        assertTrue(numbers.all { number -> number.all(Char::isDigit) })
    }
}
