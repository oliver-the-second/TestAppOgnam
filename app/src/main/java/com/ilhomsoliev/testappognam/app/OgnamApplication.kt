package com.ilhomsoliev.testappognam.app

import android.app.Application
import com.ilhomsoliev.testappognam.core.Constants
import com.ilhomsoliev.testappognam.data.local.DataStoreManager
import com.ilhomsoliev.testappognam.data.network.ServerApi
import com.ilhomsoliev.testappognam.data.repository.LoginRepository
import com.ilhomsoliev.testappognam.features.chat.viewmodel.ChatListViewModel
import com.ilhomsoliev.testappognam.features.chat.viewmodel.ChatViewModel
import com.ilhomsoliev.testappognam.features.login.viewmodel.AuthProfileViewModel
import com.ilhomsoliev.testappognam.features.login.viewmodel.CodeViewModel
import com.ilhomsoliev.testappognam.features.login.viewmodel.CountryBsViewModel
import com.ilhomsoliev.testappognam.features.login.viewmodel.LoginViewModel
import com.ilhomsoliev.testappognam.features.profile.viewmodel.ProfileViewModel
import com.ilhomsoliev.testappognam.shared.country.CountryManager
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.context.startKoin
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory
import java.util.concurrent.TimeUnit

class OgnamApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        val appModule = module {

            single {
                Retrofit.Builder()
                    .baseUrl(Constants.HOST + Constants.PREFIX_URL)
                    .addConverterFactory(ScalarsConverterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(
                        OkHttpClient.Builder()
                            .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
                            .readTimeout(60, TimeUnit.SECONDS)
                            .connectTimeout(60, TimeUnit.SECONDS)
                            .build()
                    )
                    .build()
                    .create(ServerApi::class.java)
            }
            single {
                CountryManager(this@OgnamApplication)
            }
            single {
                LoginRepository(get(), get())
            }
            single {
                DataStoreManager(this@OgnamApplication)
            }
            viewModel {
                LoginViewModel(get(), get())
            }
            viewModel {
                CountryBsViewModel(get())
            }
            viewModel {
                CodeViewModel(get())
            }
            viewModel {
                AuthProfileViewModel(get(), get())
            }
            viewModel {
                ChatListViewModel()
            }
            viewModel {
                ChatViewModel()
            }
            viewModel {
                ProfileViewModel()
            }
        }

        startKoin {

            androidLogger()

            androidContext(this@OgnamApplication)
            modules(appModule)

        }
    }
}