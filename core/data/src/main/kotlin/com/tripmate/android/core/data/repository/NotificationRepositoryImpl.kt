package com.tripmate.android.core.data.repository

import com.tripmate.android.core.data.util.runSuspendCatching
import com.tripmate.android.core.datastore.PersonalizationDataSource
import com.tripmate.android.core.network.service.TripmateService
import com.tripmate.android.domain.entity.NotificationEntity
import com.tripmate.android.domain.repository.NotificationRepository
import com.tripmate.android.domain.repository.PersonalizationRepository
import javax.inject.Inject

internal class NotificationRepositoryImpl @Inject constructor(
    private val service: TripmateService,
) : NotificationRepository {
    override suspend fun getAllNotificationList() = runSuspendCatching {
        listOf(
            NotificationEntity(
                id = 1,
                title = "여행은 즐거우셨나요?",
                message = "동행 일정이 완료되었습니다.\n함께 동행한 동행자는 어떠셧나요?",
                receivedDate = "2024-06-24",
                receivedTime = "20:32",
                isRead = false,
            ),
            NotificationEntity(
                id = 2,
                title = "동행 여행이 시작됐어요",
                message = "신청하신 동행 일정이 시작되었습니다.\n즐거운 여행 되세요",
                receivedDate = "2024-06-24",
                receivedTime = "08:23",
                isRead = true,
            ),
            NotificationEntity(
                id = 3,
                title = "동행 여행 하루 전이에요",
                message = "동행 일정 하루전이에요!\n여행 일정을 확인해볼까요?",
                receivedDate = "2024-06-23",
                receivedTime = "08:24",
                isRead = true,
            ),
        )
    }
}
