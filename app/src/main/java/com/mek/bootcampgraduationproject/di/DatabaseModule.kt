package com.mek.bootcampgraduationproject.di

import android.content.Context
import androidx.room.Room
import com.mek.bootcampgraduationproject.room.AppDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Singleton
    @Provides
    fun provideAppDatabase(@ApplicationContext context : Context) = Room.databaseBuilder(
        context,AppDatabase::class.java,"favorites_meal_database"
    ).fallbackToDestructiveMigration().build()

    @Singleton
    @Provides
    fun provideMealDao(database : AppDatabase) = database.mealDao()


}