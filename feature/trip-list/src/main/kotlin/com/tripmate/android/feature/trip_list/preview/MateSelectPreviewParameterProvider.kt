package com.tripmate.android.feature.trip_list.preview

import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import com.tripmate.android.domain.entity.TicketEntity
import com.tripmate.android.feature.trip_list.viewmodel.TripListUiState
import kotlinx.collections.immutable.persistentListOf
import kotlinx.collections.immutable.toImmutableList

internal class MateSelectPreviewParameterProvider : PreviewParameterProvider<TripListUiState> {
    override val values = sequenceOf(
        TripListUiState(
            ticket = persistentListOf(
                TicketEntity(
                    characterName = "인생이 즐거운 쇼핑 비버",
                    hashtag1 = "안생사진",
                    hashtag2 = "자유로운",
                    hashtag3 = "쇼핑마니아",
                    characterImgUrl = "https://picsum.photos/48",
                    ticketId = 1,
                ),
                TicketEntity(
                    characterName = "인생이 즐거운 쇼핑 비버",
                    hashtag1 = "안생사진",
                    hashtag2 = "자유로운",
                    hashtag3 = "쇼핑마니아",
                    characterImgUrl = "https://picsum.photos/48",
                    ticketId = 2,
                ),
                TicketEntity(
                    characterName = "인생이 즐거운 쇼핑 비버",
                    hashtag1 = "안생사진",
                    hashtag2 = "자유로운",
                    hashtag3 = "쇼핑마니아",
                    characterImgUrl = "https://picsum.photos/48",
                    ticketId = 3,
                ),
            ),
            selectedTicketIndex = 0,
        ),
    )
}
