package com.mek.bootcampgraduationproject.di

import dagger.hilt.android.scopes.ActivityRetainedScoped
import javax.inject.Inject

@ActivityRetainedScoped
class Repository @Inject constructor(remoteDataSource : RemoteDataSource) {
    val remoteData = remoteDataSource
}