package ru.semina.personservice.domain

import ru.semina.personservice.dto.PatchPersonRequest

object PatchPersonRequestMother {
    fun empty() = PatchPersonRequestBuilder()
}

class PatchPersonRequestBuilder: Builder<ru.semina.personservice.dto.PatchPersonRequest> {
    var name: String = ""
    var age: Int? = null
    var address: String? = null
    var work: String? = null

    override fun build() = ru.semina.personservice.dto.PatchPersonRequest(
        name = name,
        age = age,
        address = address,
        work = work
    )
}
