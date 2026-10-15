package com.example.rakshaai.util

import android.content.ActivityNotFoundException
import android.content.Context
import android.content.Intent
import androidx.core.net.toUri

/**
 * Opens the system dialer with [number] pre-filled. ACTION_DIAL never places a call by itself,
 * so no CALL_PHONE permission is needed and the user stays in control.
 *
 * @return true when the dialer opened, false when no dialer exists or the number is invalid.
 */
fun Context.openDialer(number: String): Boolean = try {
    startActivity(Intent(Intent.ACTION_DIAL, EmergencyResources.toDialUri(number).toUri()))
    true
} catch (e: ActivityNotFoundException) {
    false
} catch (e: IllegalArgumentException) {
    false
}
