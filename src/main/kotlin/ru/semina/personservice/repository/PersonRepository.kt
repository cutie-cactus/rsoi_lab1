package ru.semina.personservice.repository

import ru.semina.personservice.domain.PersonEntity
import org.springframework.data.repository.kotlin.CoroutineCrudRepository
import org.springframework.stereotype.Repository

@Repository
interface PersonRepository : CoroutineCrudRepository<ru.semina.personservice.domain.PersonEntity, Int>
