package com.example.cynix.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.cynix.data.characters.CharactersResults
import com.example.cynix.data.entity.CharactersEntity
import com.example.cynix.data.persistence.EntityDao
import io.reactivex.Single

@Dao
abstract class CharactersDao : EntityDao<CharactersEntity>{

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract override fun insert(entity: CharactersEntity): Long

//    @Insert(onConflict = OnConflictStrategy.IGNORE)
//    fun insertCharacters(characters: List<CharactersResults>)
//
    @Query("select * from characters")
    abstract fun getCharacters(): Single<List<CharactersEntity>>
}