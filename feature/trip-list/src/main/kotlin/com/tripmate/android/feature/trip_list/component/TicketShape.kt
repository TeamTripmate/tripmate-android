package com.tripmate.android.feature.trip_list.component

import androidx.compose.ui.geometry.*
import androidx.compose.ui.graphics.*
import androidx.compose.ui.unit.*

class TicketShape(private val holeRadius: Float, private val cornerRadius: Float) : Shape {
    override fun createOutline(
        size: Size,
        layoutDirection: LayoutDirection,
        density: Density
    ): Outline {
        // Create a rounded rectangle for the ticket's body
        val ticketPath = Path().apply {
            addRoundRect(
                RoundRect(
                    rect = Rect(0f, 0f, size.width, size.height),
                    cornerRadius = CornerRadius(cornerRadius, cornerRadius)
                )
            )
        }

        // Left hole path (circle)
        val leftHolePath = Path().apply {
            addOval(
                Rect(
                    left = -holeRadius,
                    top = size.height / 2 - holeRadius,
                    right = holeRadius,
                    bottom = size.height / 2 + holeRadius
                )
            )
        }

        // Right hole path (circle)
        val rightHolePath = Path().apply {
            addOval(
                Rect(
                    left = size.width - holeRadius,
                    top = size.height / 2 - holeRadius,
                    right = size.width + holeRadius,
                    bottom = size.height / 2 + holeRadius
                )
            )
        }

        // Subtract the holes from the ticket path
        val ticketWithoutLeftHole = Path.combine(
            PathOperation.Difference,
            ticketPath,
            leftHolePath
        )

        val finalTicketPath = Path.combine(
            PathOperation.Difference,
            ticketWithoutLeftHole,
            rightHolePath
        )

        return Outline.Generic(finalTicketPath)
    }
}
