package ru.semina.personservice.service

import ru.semina.personservice.dto.PatchPersonRequest
import ru.semina.personservice.dto.PersonRequest
import ru.semina.personservice.dto.PersonResponse
import ru.semina.personservice.exception.EntityNotFoundException
import ru.semina.personservice.mapper.toPersonEntity
import ru.semina.personservice.mapper.toPersonResponse
import ru.semina.personservice.repository.PersonRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class PersistentPersonService(private val personRepository: PersonRepository) : PersonService {

    // region Create
    @Transactional
    override suspend fun createPerson(person: ru.semina.personservice.dto.PersonRequest): Int {
        return personRepository.save(person.toPersonEntity()).id
    }
    // endregion

    // region Read
    @Transactional(readOnly = true)
    override fun getAllPeople(): Flow<ru.semina.personservice.dto.PersonResponse> = personRepository.findAll().map { it.toPersonResponse() }

    @Transactional(readOnly = true)
    override suspend fun getPerson(id: Int): ru.semina.personservice.dto.PersonResponse = personRepository.findById(id)
        ?.toPersonResponse()
        ?: throw ru.semina.personservice.exception.EntityNotFoundException("Person with id $id not found")
    // endregion

    // region Update
    @Transactional
    override suspend fun updatePerson(id: Int, person: ru.semina.personservice.dto.PatchPersonRequest): ru.semina.personservice.dto.PersonResponse {
        val old = personRepository.findById(id) ?: throw ru.semina.personservice.exception.EntityNotFoundException("Person with id $id not found")
        return personRepository.save(person.toPersonEntity(id, old)).toPersonResponse()
    }
    // endregion

    // region Delete
    @Transactional
    override suspend fun deletePerson(id: Int): Unit = personRepository.deleteById(id)
    // endregion

}
