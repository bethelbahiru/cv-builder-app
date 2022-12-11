import java.io.Serializable

data class Contact(
    val type: String,
    val link: String,
    val image: Int
) : Serializable