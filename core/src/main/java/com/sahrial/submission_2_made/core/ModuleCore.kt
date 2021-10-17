package com.sahrial.submission_2_made.core

import androidx.room.Room
import com.sahrial.submission_2_made.core.data.RepoMovie
import com.sahrial.submission_2_made.core.data.source.local.LocalDataSource
import com.sahrial.submission_2_made.core.data.source.local.room.DbMovie
import com.sahrial.submission_2_made.core.data.source.remote.RemoteDataSource
import com.sahrial.submission_2_made.core.data.source.remote.network.ServiceRetrofit
import com.sahrial.submission_2_made.core.domain.repository.IMovieRepository
import com.sahrial.submission_2_made.core.helper.AppExecutors
import com.sahrial.submission_2_made.core.helper.Constants.Companion.URL
import net.sqlcipher.database.SQLiteDatabase
import net.sqlcipher.database.SupportFactory
import okhttp3.CertificatePinner
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

val databaseModule = module {
    factory { get<DbMovie>().movieDao() }
    single {
        val passphrase: ByteArray = SQLiteDatabase.getBytes("movie".toCharArray())
        val factory = SupportFactory(passphrase)
        Room.databaseBuilder(
            androidContext(),
            DbMovie::class.java, "db_movie.db"
        ).fallbackToDestructiveMigration()
            .openHelperFactory(factory)
            .build()
    }
}

val networkModule = module {
    single {
        val hostname = "api.themoviedb.org"
        val certificatePinner = CertificatePinner.Builder()
            .add(hostname, "sha256/+vqZVAzTqUP8BGkfl88yU7SQ3C8J2uNEa55B7RZjEg0=")
            .add(hostname, "sha256/JSMzqOOrtyOT1kmau6zKhgT676hGgczD5VMdRMyJZFA=")
            .add(hostname, "sha256/++MBgDH5WGvL9Bcn5Be30cRcL0f5O+NyoXuWtQdX1aI=")
            .build()
        OkHttpClient.Builder()
            .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
            .connectTimeout(120, TimeUnit.SECONDS)
            .readTimeout(120, TimeUnit.SECONDS)
            .certificatePinner(certificatePinner)
            .build()
    }
    single {
        val retrofit = Retrofit.Builder()
            .baseUrl(URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(get())
            .build()
        retrofit.create(ServiceRetrofit::class.java)
    }
}

val repositoryModule = module {
    single { LocalDataSource(get()) }
    single { RemoteDataSource(get()) }
    factory { AppExecutors() }
    single<IMovieRepository> {
        RepoMovie(
            get(),
            get(),
            get()
        )
    }
}