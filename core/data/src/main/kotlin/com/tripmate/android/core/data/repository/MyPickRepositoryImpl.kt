package com.tripmate.android.core.data.repository

import com.tripmate.android.core.data.mapper.toDBEntity
import com.tripmate.android.core.data.mapper.toEntity
import com.tripmate.android.core.database.MyPickDao
import com.tripmate.android.domain.entity.SpotEntity
import com.tripmate.android.domain.repository.MyPickRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

internal class MyPickRepositoryImpl @Inject constructor(
    private val myPickDao: MyPickDao,
) : MyPickRepository {
    override fun getMyPickList(): Flow<List<SpotEntity>> {
        return myPickDao.getMyPickList().map { myPickList ->
            myPickList.map { myPick ->
                myPick.toEntity()
            }
        }
    }

    override suspend fun registerMyPick(spot: SpotEntity) {
        myPickDao.insertMyPick(spot.toDBEntity())
    }

    override suspend fun unregisterMyPick(spot: SpotEntity) {
        myPickDao.deleteMyPick(spot.toDBEntity())
    }
}
