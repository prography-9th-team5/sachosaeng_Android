package com.sachosaeng.app.feature.mypage.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sachosaeng.app.core.model.User
import com.sachosaeng.app.core.ui.R.string
import com.sachosaeng.app.core.ui.UserType
import com.sachosaeng.app.core.ui.theme.Gs_Black
import com.sachosaeng.app.core.ui.theme.Gs_G6
import com.sachosaeng.app.core.ui.theme.Gs_White
import com.sachosaeng.app.feature.signup.component.com.example.sachosaeng.core.ui.component.SachosaengIconProgressbar

@Composable
fun UserInfoCard(
    userInfo: User,
    modifier: Modifier = Modifier,
    userInfoModifyButtonClick: () -> Unit = {}
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .background(color = Gs_G6, shape = RoundedCornerShape(8.dp))
    ) {
        Column(
            modifier = modifier.align(Alignment.Center),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Row {
                ScoreTextColumn(
                    modifier = modifier.weight(1f),
                    countText = stringResource(id = string.vote_count, userInfo.voteScore.count),
                    score = userInfo.voteScore.score
                )
                ScoreTextColumn(
                    modifier = modifier.weight(1f),
                    countText = stringResource(
                        id = string.article_count,
                        userInfo.readArticleScore.count
                    ),
                    score = userInfo.readArticleScore.score
                )
                ScoreTextColumn(
                    modifier = modifier.weight(1f),
                    countText = stringResource(
                        id = string.register_vote_count,
                        userInfo.registerVoteScore.count
                    ),
                    score = userInfo.registerVoteScore.score
                )
            }
            UserType.getType(userInfo.userTypeName)?.let { userType ->
                Image(
                    modifier = modifier.padding(
                        start = 44.dp,
                        end = 44.dp,
                        top = 44.dp,
                        bottom = 32.dp
                    ),
                    contentDescription = "",
                    painter = painterResource(
                        id = userType.getLargeImageRes(userInfo.level)
                    ),
                )
            }
            UserScoreCard(
                userType = userInfo.userTypeName,
                userScore = userInfo.score,
                userLevel = userInfo.level,
                maxScore = userInfo.maxScore,
            )
        }
    }
}

@Composable
private fun ScoreTextColumn(
    modifier: Modifier = Modifier,
    countText: String,
    score: Int,
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = modifier.padding(top = 32.dp)
    ) {
        Text(
            text = countText,
            fontSize = 12.sp,
            fontWeight = FontWeight.W500,
            color = Gs_White
        )
        Text(
            text = stringResource(string.score, score),
            color = Gs_White,
            fontSize = 18.sp,
            fontWeight = FontWeight.W700
        )
    }
}

@Composable
private fun UserScoreCard(
    userLevel: Int,
    userType: String,
    userScore: Int,
    maxScore: Int
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(color = Gs_White, shape = RoundedCornerShape(8.dp))
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier.padding(16.dp)
        ) {
            Row {
                UserType.getType(userType)?.let { userType ->
                    Text(
                        text = stringResource(
                            id = string.mypage_level_text,
                            userLevel,
                            stringResource(userType.userTypeLabelRes),
                        ),
                        fontSize = 18.sp,
                        color = Gs_Black,
                        fontWeight = FontWeight.W700,
                    )
                }
                Text(
                    text = stringResource(id = string.mypage_score_by_max_score, userScore, maxScore),
                    fontSize = 18.sp,
                    color = Gs_Black,
                    fontWeight = FontWeight.W700,
                )
            }
            SachosaengIconProgressbar()
        }
    }
}

@Composable
@Preview
fun UserInfoCardPreview() {
    UserInfoCard(
        userInfo = User(
            id = 1,
            name = "홍길동",
            email = "example@example",
            userTypeName = UserType.STUDENT.name,
            level = 1,
        )
    )
}