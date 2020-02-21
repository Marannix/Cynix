package com.example.cynix.episodes.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.cynix.common.persistence.EntityDao
import com.example.cynix.remote.EpisodeApiContract
import io.reactivex.Single

@Dao
abstract class CharacterEpisodeDao : EntityDao<CharacterEpisodeDao> {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    abstract override fun insert(entity: CharacterEpisodeDao): Long

    @Query("select * from characterEpisodes where characterId = :character_id order by id")
    abstract fun getEpisodes(character_id: String): Single<List<EpisodeApiContract.CharacterEpisodeResponse>>
}