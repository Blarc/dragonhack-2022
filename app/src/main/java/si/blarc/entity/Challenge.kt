package si.blarc.entity

import java.io.Serializable

data class Challenge (
    val title: String,
    val description: String,
    val reward: Int,
    val assignedTo: String?,
    val assignedFrom: String?,
    val color: String,
    var completed: Boolean?,
    val dateToDo: String?

    ) : Serializable {
    constructor() : this("", "", 0, "", "", "", false, null)
}
