import com.example.sachosaeng.domain.constant.OAuthType

data class User(
    val id: Int,
    val name: String,
    val email: String,
    val oAuthType: com.example.sachosaeng.domain.constant.OAuthType
)