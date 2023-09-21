package com.example.newsappforandroid.product.di

import android.content.Context
import androidx.room.Room
import com.example.newsappforandroid.core.base.parser.BaseParser
import com.example.newsappforandroid.feature.favorites.repository.FavoritesDao
import com.example.newsappforandroid.product.constants.database.DatabaseConstants.NEWS_DATABASE
import com.example.newsappforandroid.product.init.parser.GsonParser
import com.example.newsappforandroid.product.init.storage.AppDatabase
import com.example.newsappforandroid.product.utils.DbModelConverter
import com.google.gson.Gson
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DatabaseModule {

    @Provides
    @Singleton
    fun provideRoomInstance(
        @ApplicationContext context: Context,
        converter: DbModelConverter
    ): AppDatabase {
        return Room.databaseBuilder(context, AppDatabase::class.java, NEWS_DATABASE)
            .addTypeConverter(converter).build()
    }

    @Provides
    @Singleton
    fun provideNewsDao(newsDatabase: AppDatabase): FavoritesDao {
        return newsDatabase.newsDao()
    }

    @Provides
    @Singleton
    fun provideGsonParser(): BaseParser {
        return GsonParser(Gson())
    }

    @Provides
    @Singleton
    fun provideDbModelConverter(jsonParser: BaseParser): DbModelConverter {
        return DbModelConverter(jsonParser)
    }
}