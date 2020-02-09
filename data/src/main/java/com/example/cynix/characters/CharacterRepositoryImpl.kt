package com.example.cynix.characters

import android.util.Log
import com.example.cynix.character.Character
import com.example.cynix.character.CharacterRepository
import com.example.cynix.character.Location
import com.example.cynix.characters.dao.CharacterDao
import com.example.cynix.characters.entity.CharactersEntity
import com.example.cynix.remote.CharacterApi
import com.example.cynix.remote.CharacterApiContract
import io.reactivex.Observable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class CharacterRepositoryImpl @Inject constructor(
    private val charactersDao: CharacterDao,
    private val charactersApi: CharacterApi
) : CharacterRepository {

    override fun getCharacters(): Observable<List<Character>> {
        return getCharactersFromDb().flatMapIterable { it }
            .map { it.toDomain(it) }
            .toList()
            .toObservable()

    }

    private fun getCharactersFromDb(): Observable<List<CharactersEntity>> {
        return charactersDao.getCharacters()
            .toObservable()
            .flatMap { list ->
                return@flatMap if (list.isEmpty()) {
                    getCharactersFromApi().toList().toObservable()
                } else {
                    Observable.just(list)
                }
            }
    }

    private fun getCharactersFromApi(): Observable<CharactersEntity> {
        return fetchInitialCharacters()
            .concatMap { listOfCharacters ->
                Observable.fromIterable(listOfCharacters).map { mappingFromApiToDb(it) }
            }
    }

    private fun fetchInitialCharacters(): Observable<List<CharacterApiContract.CharactersResults>> {
        return charactersApi.getCharacters()
            .subscribeOn(Schedulers.io())
            .concatMap { response ->
                if (response.characterPageInfo.next.isEmpty()) {
                    Observable.just(response.charactersResults)
                } else {
                    Observable.just(response.charactersResults)
                        .concatWith(fetchNextCharacters(response.characterPageInfo.next))
                }
            }
            .doOnNext {
                storeCharactersInDb(it)
            }
    }

    private fun fetchNextCharacters(nextUrl: String): Observable<List<CharacterApiContract.CharactersResults>> {
        return charactersApi.getNextCharacters(nextUrl)
            .subscribeOn(Schedulers.io())
            .concatMap { response ->
                if (response.characterPageInfo.next.isEmpty()) {
                    Observable.just(response.charactersResults)
                } else {
                    Observable.just(response.charactersResults)
                        .concatWith(fetchNextCharacters(response.characterPageInfo.next))
                }
            }
            .doOnNext {
                storeCharactersInDb(it)
            }
    }

    private fun storeCharactersInDb(characters: List<CharacterApiContract.CharactersResults>) {
        characters.forEach { charactersResults ->
            val mapped = mappingFromApiToDb(charactersResults)
            charactersDao.insert(mapped)
        }
    }

    private fun mappingFromApiToDb(charactersResults: CharacterApiContract.CharactersResults): CharactersEntity {
        return CharactersEntity(
            charactersResults.id,
            charactersResults.name,
            charactersResults.status,
            charactersResults.species,
            charactersResults.gender,
            charactersResults.image,
            charactersResults.location,
            charactersResults.episode
        )
    }

    private fun mappingFromDbToCharacter(charactersResults: CharactersEntity): Character {
        return Character(
            charactersResults.id,
            charactersResults.name,
            charactersResults.status,
            charactersResults.species,
            charactersResults.gender,
            charactersResults.image,
            Location(charactersResults.location.name),
            charactersResults.episode
        )
    }
}