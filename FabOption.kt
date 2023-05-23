package com.csahmed.laundryapp.recipt_feature.presentation.component.fab

import android.annotation.SuppressLint
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.Stable
import androidx.compose.ui.graphics.Color
import com.csahmed.laundryapp.core.theme.mySecondaryColor
import com.csahmed.laundryapp.core.theme.white

@Immutable
interface FabOption {
    @Stable
    val iconTint: Color
    @Stable val backgroundTint: Color
    @Stable val showLabel: Boolean
}

private class FabOptionImpl(
    override val iconTint: Color,
    override val backgroundTint: Color,
    override val showLabel: Boolean
) : FabOption

@SuppressLint("ComposableNaming")
@Composable
fun FabOption(
    iconTint: Color = white,
    backgroundTint: Color = mySecondaryColor,
    showLabel: Boolean = true
): FabOption = FabOptionImpl(iconTint,backgroundTint, showLabel)