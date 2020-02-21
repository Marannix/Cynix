package com.example.cynix.episodes.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.cynix.common.persistence.EntityDao
import com.example.cynix.episodes.entity.EpisodeEntity
import com.example.cynix.remote.EpisodeApiContract
import io.reactivex.Single

@Dao
abstract class EpisodeDao : EntityDao<EpisodeEntity> {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract override fun insert(entity: EpisodeEntity): Long

    @Query("select * from episode")
    abstract fun getAllEpisodes(): Single<List<EpisodeApiContract.CharacterEpisodeResponse>>

}