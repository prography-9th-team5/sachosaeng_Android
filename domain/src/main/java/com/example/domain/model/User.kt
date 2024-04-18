import com.example.domain.constant.OAuthType

data class User(
    val id: Int,
    val name: String,
    val email: String,
    val oAuthType: OAuthType
)