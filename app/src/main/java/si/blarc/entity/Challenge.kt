package si.blarc.entity

import java.io.Serializable

data class Challenge (
    val title: String,
    val description: String,
    val reward: Number,
    val assignedTo: String?,
    val assignedFrom: String?,
    val color: String

    ) : Serializable {
    val points = 0
}