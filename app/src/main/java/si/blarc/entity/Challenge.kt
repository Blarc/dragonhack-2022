package si.blarc.entity

import java.io.Serializable

data class Challenge (
    val title: String,
    val description: String,
    val reward: Int,
    val assignedTo: String?,
    val assignedFrom: String?,
    val color: String

    ) : Serializable {
    constructor() : this("", "", 0, "", "", "")
}