package si.blarc.entity

import java.io.Serializable

data class User(
    val id: String,
    val name: String) : Serializable
{
    constructor() : this("", "")
}
