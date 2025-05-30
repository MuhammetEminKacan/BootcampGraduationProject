package com.mek.bootcampgraduationproject.di

import dagger.hilt.android.scopes.ActivityRetainedScoped
import javax.inject.Inject

@ActivityRetainedScoped
class RoomRepository @Inject constructor(localDataSource: LocalDataSource) {
    val localDataSource = localDataSource

    suspend fun isMealFavorited(id: Int): Boolean {
        return localDataSource.getMealById(id) != null
    }
}