package ru.semina.personservice.mapper

import ru.semina.personservice.domain.PersonEntity
import ru.semina.personservice.dto.PatchPersonRequest
import ru.semina.personservice.dto.PersonRequest
import ru.semina.personservice.dto.PersonResponse

fun ru.semina.personservice.domain.PersonEntity.toPersonResponse() = ru.semina.personservice.dto.PersonResponse(
    id = id,
    name = name,
    age = age,
    address = address,
    work = work
)

fun ru.semina.personservice.dto.PersonRequest.toPersonEntity(id: Int = 0) =
    ru.semina.personservice.domain.PersonEntity(
        id = id,
        name = name,
        age = age,
        address = address,
        work = work
    )

fun ru.semina.personservice.dto.PatchPersonRequest.toPersonEntity(id: Int = 0, prevState: ru.semina.personservice.domain.PersonEntity) =
    ru.semina.personservice.domain.PersonEntity(
        id = id,
        name = name ?: prevState.name,
        age = age ?: prevState.age,
        address = address ?: prevState.address,
        work = work ?: prevState.work
    )
