package ru.semina.personservice.service

import ru.semina.personservice.dto.PatchPersonRequest
import ru.semina.personservice.dto.PersonRequest
import ru.semina.personservice.dto.PersonResponse
import kotlinx.coroutines.flow.Flow

interface PersonService {

    // region Create
    suspend fun createPerson(person: ru.semina.personservice.dto.PersonRequest): Int
    // endregion

    // region Read
    fun getAllPeople(): Flow<ru.semina.personservice.dto.PersonResponse>

    suspend fun getPerson(id: Int): ru.semina.personservice.dto.PersonResponse
    // endregion

    // region Update
    suspend fun updatePerson(id: Int, person: ru.semina.personservice.dto.PatchPersonRequest): ru.semina.personservice.dto.PersonResponse
    // endregion

    // region Delete
    suspend fun deletePerson(id: Int)
    // endregion

}
