package com.lordsam.virtualcloset.di

import android.content.Context
import androidx.room.Room
import com.lordsam.virtualcloset.database.ClosetDatabase
import com.lordsam.virtualcloset.database.ClosetDatabaseDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object AppModule {
    @Singleton
    @Provides
    fun provideClosetDao(closetDatabase: ClosetDatabase): ClosetDatabaseDao = closetDatabase.closetDao()

    @Singleton
    @Provides
    fun provideAppDatabase(@ApplicationContext context: Context): ClosetDatabase
            = Room.databaseBuilder(
        context,
        ClosetDatabase::class.java,
        "closet_db"
    ).fallbackToDestructiveMigration().build()
}