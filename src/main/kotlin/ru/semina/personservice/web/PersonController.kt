package ru.semina.personservice.web

import ru.semina.personservice.dto.PatchPersonRequest
import ru.semina.personservice.dto.PersonRequest
import ru.semina.personservice.dto.PersonResponse
import ru.semina.personservice.service.PersonService
import kotlinx.coroutines.flow.Flow
import org.springframework.http.ResponseEntity
import org.springframework.http.server.reactive.ServerHttpRequest
import org.springframework.web.bind.annotation.*
import org.springframework.web.util.UriComponentsBuilder
import javax.validation.Valid

@RestController
@RequestMapping("/persons")
class PersonController(val personService: PersonService) {

    @GetMapping("/{id}")
    suspend fun getPerson(@PathVariable id: Int): ResponseEntity<ru.semina.personservice.dto.PersonResponse> =
        ResponseEntity.ok().body(personService.getPerson(id))

    @GetMapping
    fun getAllPeople(): ResponseEntity<Flow<ru.semina.personservice.dto.PersonResponse>> = ResponseEntity.ok().body(personService.getAllPeople())

    @PostMapping
    suspend fun createPerson(@Valid @RequestBody person: ru.semina.personservice.dto.PersonRequest, request: ServerHttpRequest): ResponseEntity<Unit> {
        val createdPersonId = personService.createPerson(person)

        return ResponseEntity.created(
            UriComponentsBuilder.fromHttpRequest(request)
                .path("/{id}")
                .buildAndExpand(createdPersonId)
                .toUri()
        ).build()
    }

    @PatchMapping("/{id}")
    suspend fun updatePerson(
        @PathVariable id: Int,
        @Valid @RequestBody person: ru.semina.personservice.dto.PatchPersonRequest
    ): ResponseEntity<ru.semina.personservice.dto.PersonResponse> {
        val updatedPerson = personService.updatePerson(id, person)

        return ResponseEntity.ok()
            .body(updatedPerson)
    }

    @DeleteMapping("/{id}")
    suspend fun deletePerson(@PathVariable id: Int): ResponseEntity<Unit> {
        personService.deletePerson(id)
        return ResponseEntity.noContent().build()
    }
}
