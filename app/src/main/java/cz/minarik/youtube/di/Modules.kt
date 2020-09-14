package cz.minarik.youtube.di

import androidx.room.Room
import cz.minarik.base.common.prefs.PrefManager
import cz.minarik.base.data.db.AppDatabase
import cz.minarik.base.data.db.repository.YouTubeVideoPagedRepository
import cz.minarik.youtube.data.service.YoutubeApiService
import cz.minarik.youtube.ui.detail.YoutubeDetailViewModel
import cz.minarik.youtube.ui.list.YoutubeListViewModel
import kotlinx.coroutines.CoroutineScope
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidApplication
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.module.Module
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.Executor
import java.util.concurrent.TimeUnit


val appModule = module {

    // view modely

    // preferences
    preferences()

    viewModel { YoutubeListViewModel() }
    viewModel { YoutubeDetailViewModel(get()) }
}
val restModule = module {

    // retrofit
    ws()

    //repository
    single { (networkExecutor: Executor, scope: CoroutineScope) ->
        YouTubeVideoPagedRepository(
            get(),
            networkExecutor,
            scope
        )
    }
}

val dbModule = module {

    // db
    single {
        Room
            .databaseBuilder(
                androidApplication(),
                AppDatabase::class.java,
                AppDatabase.Name
            )
            //.addMigrations(Migration_4_to_5)
            .build()
    }

}

val allModules = listOf(appModule, restModule, dbModule)

private fun Module.preferences() {
    single(createdAtStart = true) {
        PrefManager(
            androidContext()
        )
    }
    //single(named("MySecondPrefs") createdAtStart = true) { PrefManager(androidContext()) }
}


private fun Module.ws() {
    single {
        YoutubeApiService(
            createRetrofit(
                createOkHttpClient(),
                "https://www.googleapis.com/youtube/v3/"
            )
        )
    }
}


fun createOkHttpClient(): OkHttpClient {
    return OkHttpClient.Builder()
        .connectTimeout(10L, TimeUnit.SECONDS)
        .readTimeout(10L, TimeUnit.SECONDS)
        .callTimeout(10L, TimeUnit.SECONDS)
        .writeTimeout(10L, TimeUnit.SECONDS)
        //.dns(getDns()) Problém je v DNS lookupu, který trvá desítky vteřin
        .addInterceptor { chain ->
            val restBuilder = chain.request().newBuilder()
            chain.proceed(restBuilder.build())
        }
        .addInterceptor(HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        })
        .build()
}

fun createRetrofit(okHttpClient: OkHttpClient, baseUrl: String): Retrofit {
    return Retrofit.Builder()
        .baseUrl(baseUrl)
        .client(okHttpClient)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
}
