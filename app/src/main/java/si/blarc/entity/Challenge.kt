package si.blarc.entity

import java.io.Serializable
import java.time.LocalDate
import java.util.*

data class Challenge (
    val title: String,
    val description: String,
    val reward: Int,
    val assignedTo: String?,
    val assignedFrom: String?,
    val color: String,
    val completed: Boolean?,
    val dateToDo: String?

    ) : Serializable {
    constructor() : this("", "", 0, "", "", "", false, null)
}
