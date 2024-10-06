package com.tripmate.android.feature.trip_list

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.util.Patterns
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat.startActivity
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.tripmate.android.core.common.ObserveAsEvents
import com.tripmate.android.core.designsystem.component.TripmateButton
import com.tripmate.android.core.designsystem.theme.Background02
import com.tripmate.android.core.designsystem.theme.Medium16_SemiBold
import com.tripmate.android.core.designsystem.theme.TripmateTheme
import com.tripmate.android.core.ui.DevicePreview
import com.tripmate.android.feature.trip_list.component.MyTripStyle
import com.tripmate.android.feature.trip_list.viewmodel.TripListUiAction
import com.tripmate.android.feature.trip_list.viewmodel.TripListUiEvent
import com.tripmate.android.feature.trip_list.viewmodel.TripListUiState
import com.tripmate.android.feature.trip_list.viewmodel.TripListViewModel
import com.tripmate.android.feature.triplist.R
import tech.thdev.compose.exteions.system.ui.controller.rememberExSystemUiController
import java.net.MalformedURLException
import java.net.URL

@Composable
internal fun MateOpenChatRoute(
    innerPadding: PaddingValues,
    popBackStack: () -> Unit,
    openChatLink: String,
    selectedKeywords: List<String>,
    tripStyle: String,
    characterId: String,
    viewModel: TripListViewModel = hiltViewModel(),
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val context = LocalContext.current
    val systemUiController = rememberExSystemUiController()
    val isDarkTheme = isSystemInDarkTheme()

    DisposableEffect(systemUiController) {
        systemUiController.setStatusBarColor(
            color = Color.Transparent,
            darkIcons = true,
        )
        onDispose {
            systemUiController.setStatusBarColor(
                color = Color.White,
                darkIcons = !isDarkTheme,
            )
        }
    }

    ObserveAsEvents(flow = viewModel.uiEvent) { event ->
        when (event) {
            is TripListUiEvent.NavigateBack -> popBackStack()
            is TripListUiEvent.NavigateToKakaoOpenChat -> {
                openKakaoOpenChat(context, event.openChatUrl)
            }
            else -> {}
        }
    }

    MateOpenChatScreen(
        innerPadding = innerPadding,
        uiState = uiState,
        openChatLink = openChatLink,
        selectedKeywords = selectedKeywords,
        tripStyle = tripStyle,
        characterId = characterId,
        onAction = viewModel::onAction,
    )
}

private fun openKakaoOpenChat(context: Context, url: String) {
    if (isValidUrl(url)) {
        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
        startActivity(context, intent, null)
    }
}

private fun isValidUrl(urlString: String): Boolean {
    return try {
        // 안드로이드의 Patterns 클래스를 사용하여 URL 형식 검사
        val urlPattern = Patterns.WEB_URL
        if (!urlPattern.matcher(urlString).matches()) {
            return false
        }

        // URL 객체 생성을 통한 추가 검증
        val url = URL(urlString)
        val protocol = url.protocol
        if (protocol != "http" && protocol != "https") {
            return false
        }
        true
    } catch (e: MalformedURLException) {
        false
    }
}

@Composable
internal fun MateOpenChatScreen(
    innerPadding: PaddingValues,
    uiState: TripListUiState,
    openChatLink: String,
    selectedKeywords: List<String>,
    tripStyle: String,
    characterId: String,
    onAction: (TripListUiAction) -> Unit,
    modifier: Modifier = Modifier,
) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .padding(innerPadding),
    ) {
        Column(
            modifier = modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .background(Background02),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            MyTripStyle(
                characterId = characterId,
                characterTypeIntro = getCharacterTypeIntro(characterId),
                tripStyleIntro = getTripStyleIntro(characterId),
                tripStyle = tripStyle,
                selectedKeywords = selectedKeywords,
                modifier = Modifier.fillMaxWidth(),
            )
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                TripmateButton(
                    onClick = {
                        onAction(TripListUiAction.OnMateOpenChatClicked(openChatLink))
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(56.dp),
                ) {
                    Text(
                        text = stringResource(R.string.navigate_to_mate_open_chat),
                        style = Medium16_SemiBold,
                    )
                }
                Spacer(modifier = Modifier.height(56.dp))
            }
        }
    }
}

private fun getCharacterTypeIntro(characterId: String): String {
    return when (characterId) {
        "PENGUIN" -> "펭귄은 내향적인 성향을 가지고 있고, 대부분의 시간을 집단 내에서 조용히 보내며, 개인적인 공간과 안정적인 환경을 선호해요.\n" +
            "\n 혼자보다는 집단과 함께 있는 것을 더 편안해하고, 사회적 상호작용보다 자신의 역할에 집중합니다. 매우 세부적으로 계획을 세우고 조직적인 행동을 하는 유형이에요."

        "HONEYBEE" -> "꿀벌은 조직적이고 체계적인 타입이에요. 목표를 달성하기 위해 모든 것을 철저히 계획하며, 팀워크를 중시하죠, 각자 맡은 역할을 정확히 수행하고, 협력하여 효율적으로 임무를 완수하는 것을 목표로 해요.\n" +
            "\n주어진 시간을 최대한 활용해 목표를 달성하려는 성향이 강한 유형이에요. 단체 활동이나 프로젝트를 이끌 때 특히 강한 리더십을 발휘한답니다."

        "ELEPHANT" -> "코끼리는 주도적이면서도 배려심 깊은 성격을 가지고 있어요. 이들은 무리를 이끌면서도 다른 사람들의 의견을 존중해요. 중요한 결정을 내릴 땐 신중하게 행동한답니다.\n" +
            "\n안정적이고 신뢰할 수 있는 존재로, 큰 그림을 그리고 이를 실현하는데 능숙해요. 계획된 일정 안에서 다른 사람들과의 조화를 이루는 것을 중시하는 유형이에요."

        "DOLPHIN" -> "돌고래는 활발하고 사교적인 성격을 가지고 있으며, 자유로운 삶을 즐기는 타입이에요. 새로운 경험을 찾고, 사람들과의 상호작용에서 에너지를 얻는 유형이죠.\n" +
            "\n즉흥적이고 모험을 즐기며, 일상의 틀에 갇히지 않고 다양한 상황에서 자신을 표현하는 것을 좋아합니다. 순간의 즐거움을 중요시하며, 규칙보다는 자유로운 사고를 선호해요."

        "TURTLE" -> "거북이는 느긋하고 신중한 성격을 가지고 있고, 큰 변화를 싫어하며 안정적이고 예측 가능한 환경에서 여유롭게 지내기를 좋아하는 타입이에요.\n" +
            "\n속도보다는 과정 자체를 즐기며, 차분하고 신중하게 행동하죠. 무리보다 독립적인 활동을 선호하지만, 기본적인 틀 안에서의 활동에 안정을 느낍니다."

        else -> "판다는 느긋하고 편안한 성격을 가지고 있어요. 규칙에 얽매이지 않고, 자연스러운 흐름에 몸을 맡기며, 여유로운 삶을 즐기는 타입이에요.\n" +
            "\n스트레스를 받기보다는 휴식과 편안암을 추구하며, 무리하지 않는 것을 중요하게 생각하죠. 계획에 구애받지 않고, 자신이 좋아하는 환경에서 편안하게 시간을 보내는 것을 선호합니다."
    }
}

private fun getTripStyleIntro(characterId: String): String {
    return when (characterId) {
        "PENGUIN" -> "펭귄은 여행을 떠나기 전에 철저한 계획을 세우는 것을 좋아해요. 여행의 주요 목적지와 일정, 활동을 미리 정해두고, 예상 가능한 상황에 대비해 준비를 철저히 할 때 안정감을 느끼며 편안하게 여행을 즐긴답니다." +
            "\n펭귄은 집단 내에서 협력하여 활동하는 것을 좋아하는데요. 여행 중에도 동행자와 함께 계획을 공유하고, 서로의 역할을 명확히 하여 협력적으로 움직이는 것을 선호하죠."

        "HONEYBEE" -> "꿀벌은 철저한 사전 준비를 통해 여행을 계획해요. 하루의 일정이 시간 단위로 나뉘어 있으며, 각 장소의 방문 시간과 이동 시간을 세밀하게 계산하는 유형이에요." +
            "\n꿀벌은 그룹으로 여행을 떠날 때도 모든 구성원이 자신의 역할을 잘 수행할 수 있도록 체계적으로 준비합니다. 예상치 못한 상황에도 대비책을 마련해두며, 계획된 일정에서 벗어나는 것을 최소화하고자 노력해요."

        "ELEPHANT" -> "코끼리는 여행의 주요 목적지와 활동을 미리 계획하지 않지만, 그 과정에서 유연성을 발휘하는 유형이에요. 예기지 않은 상황에서도 크게 동요하지 않아요." +
            "\n일정 조정이 가능하다는 점에서 여행의 흐름을 자연스럽게 유지하곤 합니다. 코끼리는 여행 중 동행자를 배려하며, 안정하고 편안한 환경에서 여행을 즐겨요."

        "DOLPHIN" -> "돌고래는 계획 없이 즉흥적으로 여행을 떠나는 것을 좋아해요. 여행 중에 새로운 사람들과 쉽게 어울리며, 다양한 액티비티에 참여해 즐거움을 찾아 나선답니다.\n" +
            "\n가이드에 얽매이지 않고, 현지에서 직접 찾아낸 장소나 활동을 통해 여행의 묘미를 느끼는 스타일이에요. 해변이나 수상 스포츠 등 활동적인 체험을 즐기는 편입니다."

        "TURTLE" -> "거북이는 기본적인 여행 계획을 세우되, 여유로운 페이스를 유지해요. 여행 중 갑작스러운 변화보다는, 미리 정해진 일정 안에서 여유롭게 시간을 보낸답니다.\n" +
            "\n일정 사이에 충분한 휴식 시간을 두고, 너무 바쁘게 움직이지 않도록 조절하곤 하죠. 조용하고 평온한 환경에서 차분하게 자연을 즐기는 것을 좋아해요."

        else -> "판다는 특별한 계획 없이 여행을 즐기며, 그때그때의 기분에 따라 움직이곤 해요. 여행 대부분의 시간을 편안한 장소에서 보내곤 해요.\n" +
            "\n자연 속에서 휴식을 취하거나 느긋하게 경치를 감상하기도 한답니다. 새로운 도전보다는 익숙한 환경에서 편안함을 느끼며, 여행의 목적이 휴식과 힐링에 맞춰져 있는 유형이죠."
    }
}

@DevicePreview
@Composable
private fun MyTripCharacterInfoPreview() {
    TripmateTheme {
        MateOpenChatScreen(
            innerPadding = PaddingValues(),
            uiState = TripListUiState(),
            onAction = {},
            openChatLink = "https://open.kakao.com/o/gObLOlQg",
            selectedKeywords = listOf("힐링", "휴식", "자연"),
            tripStyle = "인스타 인생 맛집",
            characterId = "HONEYBEE",
        )
    }
}
