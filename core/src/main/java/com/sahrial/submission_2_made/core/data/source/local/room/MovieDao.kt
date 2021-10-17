package com.sahrial.submission_2_made.core.data.source.local.room

import androidx.room.*
import com.sahrial.submission_2_made.core.data.source.local.entity.MovEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface MovieDao {

    @Query("SELECT * FROM tb_movie")
    fun getAllMovie(): Flow<List<MovEntity>>

    @Query("SELECT * FROM tb_movie where isFavorite = 1")
    fun getFavoriteMovie(): Flow<List<MovEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMovie(mov: List<MovEntity>)

    @Update
    fun updateFavoriteMovie(mov: MovEntity)
}
