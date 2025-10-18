package ru.semina.personservice.dto

data class ValidationErrorResponse(
    override val message: String?,
    val errors: Map<String, ru.semina.personservice.dto.ValidationErrorResponse.FieldMessage> = emptyMap()
): ru.semina.personservice.dto.ErrorResponse(message) {
    data class FieldMessage(
        val rejectedValue: Any?,
        val defaultMessage: String?
    )
}
