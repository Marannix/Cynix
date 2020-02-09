package com.example.cynix.repository

import android.util.Log
import com.example.cynix.api.CharactersApi
import com.example.cynix.data.dao.CharactersDao
import com.example.cynix.data.characters.CharactersResults
import com.example.cynix.data.entity.CharactersEntity
import io.reactivex.Observable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

//
////TODO: To be deleted, Should be handled in the data level
//class CharactersRepository @Inject constructor(
//    private val charactersDao: CharactersDao,
//    private val charactersApi: CharactersApi
//) {
//
//    fun getCharacters(): Observable<List<CharactersEntity>> {
//        return getCharactersFromDb()
//    }
//
//    private fun getCharactersFromDb(): Observable<List<CharactersEntity>> {
//        return charactersDao.getCharacters()
//            .toObservable()
//            .flatMap { list ->
//                return@flatMap if (list.isEmpty()) {
//                    getCharactersFromApi().toList().toObservable()
//                } else {
//                    Log.d("charactersDao", list.toString())
//                    Observable.just(list)
//                }
//            }
//    }
//
//    private fun getCharactersFromApi(): Observable<CharactersEntity> {
//        return fetchInitialCharacters()
//            .concatMap { listOfCharacters ->
//                Observable.fromIterable(listOfCharacters).map { mappingFromApiToDb(it) }
//            }
//    }
//
//    private fun fetchInitialCharacters(): Observable<List<CharactersResults>> {
//        return charactersApi.getCharacters()
//            .subscribeOn(Schedulers.io())
//            .concatMap { response ->
//                if (response.characterPageInfo.next.isEmpty()) {
//                    Observable.just(response.charactersResults)
//                } else {
//                    Observable.just(response.charactersResults)
//                        .concatWith(fetchNextCharacters(response.characterPageInfo.next))
//                }
//            }
//            .doOnNext {
//                storeCharactersInDb(it)
//            }
//    }
//
//    private fun fetchNextCharacters(nextUrl: String): Observable<List<CharactersResults>> {
//        return charactersApi.getNextCharacters(nextUrl)
//            .subscribeOn(Schedulers.io())
//            .concatMap { response ->
//                if (response.characterPageInfo.next.isEmpty()) {
//                    Observable.just(response.charactersResults)
//                } else {
//                    Observable.just(response.charactersResults)
//                        .concatWith(fetchNextCharacters(response.characterPageInfo.next))
//                }
//            }
//            .doOnNext {
//                storeCharactersInDb(it)
//            }
//    }
//
//    private fun storeCharactersInDb(characters: List<CharactersResults>) {
//        characters.forEach { charactersResults ->
//            val mapped = mappingFromApiToDb(charactersResults)
//            charactersDao.insert(mapped)
//        }
//    }
//
//    private fun mappingFromApiToDb(charactersResults: CharactersResults): CharactersEntity {
//        return CharactersEntity(
//            charactersResults.id,
//            charactersResults.name,
//            charactersResults.status,
//            charactersResults.species,
//            charactersResults.gender,
//            charactersResults.image,
//            charactersResults.location,
//            charactersResults.episode
//        )
//    }
//}