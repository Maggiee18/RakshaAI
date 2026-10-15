package com.example.rakshaai.ui.components

import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.rakshaai.R
import com.example.rakshaai.ui.theme.OnSosRed
import com.example.rakshaai.ui.theme.SosRed

/**
 * Large circular SOS control with a soft pulsing halo.
 * Tapping it only navigates to the emergency screen; it never sends anything by itself.
 */
@Composable
fun SosButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    diameter: Dp = 148.dp,
) {
    val transition = rememberInfiniteTransition(label = "sosPulse")
    val haloScale by transition.animateFloat(
        initialValue = 0.92f,
        targetValue = 1.08f,
        animationSpec = infiniteRepeatable(
            animation = tween(durationMillis = 1400, easing = FastOutSlowInEasing),
            repeatMode = RepeatMode.Reverse,
        ),
        label = "sosHaloScale",
    )
    val description = stringResource(R.string.sos_button_content_description)

    Box(
        modifier = modifier.size(diameter * 1.25f),
        contentAlignment = Alignment.Center,
    ) {
        Box(
            modifier = Modifier
                .size(diameter * 1.18f)
                .graphicsLayer {
                    scaleX = haloScale
                    scaleY = haloScale
                }
                .clip(CircleShape)
                .background(SosRed.copy(alpha = 0.16f)),
        )
        Surface(
            onClick = onClick,
            modifier = Modifier
                .size(diameter)
                .semantics { contentDescription = description },
            shape = CircleShape,
            color = SosRed,
            contentColor = OnSosRed,
            shadowElevation = 6.dp,
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center,
            ) {
                Text(
                    text = stringResource(R.string.sos_label),
                    style = MaterialTheme.typography.headlineLarge.copy(
                        fontWeight = FontWeight.Bold,
                        letterSpacing = 3.sp,
                    ),
                )
                Text(
                    text = stringResource(R.string.sos_button_hint),
                    style = MaterialTheme.typography.labelMedium,
                )
            }
        }
    }
}
