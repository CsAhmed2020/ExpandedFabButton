package com.csahmed.laundryapp.recipt_feature.presentation.component.fab

import androidx.compose.runtime.Immutable
import androidx.compose.runtime.Stable
import androidx.compose.ui.graphics.vector.ImageVector


/**
 * Using Stable & Immutable annotations let the compose compiler do some optimizations.
 * When a @Stable public property of the type changes, composition will be notified.[when it changes, Compose runtime will be notified.]
 */
/**
 * Immutable refer that all publicly accessible properties and fields will not change after the instance is constructed.[that values read from the type will not change]
 */

@Immutable
interface FabIcon {
    @Stable
    val iconRes: ImageVector
    @Stable val iconRotate: Float?
}

private class FabIconImpl(
    override val iconRes: ImageVector,
    override val iconRotate: Float?
) : FabIcon


//Provide fab icon
fun FabIcon(iconRes: ImageVector, iconRotate: Float? = null): FabIcon =
    FabIconImpl(iconRes, iconRotate)