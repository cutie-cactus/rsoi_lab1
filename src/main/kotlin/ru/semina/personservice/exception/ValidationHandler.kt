package ru.semina.personservice.exception

import ru.semina.personservice.dto.ErrorResponse
import ru.semina.personservice.dto.ValidationErrorResponse
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestControllerAdvice
import org.springframework.web.bind.support.WebExchangeBindException


@RestControllerAdvice
class ValidationHandler {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(WebExchangeBindException::class)
    fun handleException(e: WebExchangeBindException): ru.semina.personservice.dto.ValidationErrorResponse {
        val errors = e.bindingResult.fieldErrors.associateBy(
            {
                it.field
            }, {
                ru.semina.personservice.dto.ValidationErrorResponse.FieldMessage(
                    rejectedValue = it.rejectedValue,
                    defaultMessage = it.defaultMessage
                )
            }
        )

        return ru.semina.personservice.dto.ValidationErrorResponse("Binding failure", errors)
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(ru.semina.personservice.exception.EntityNotFoundException::class)
    fun handleEntityNotFoundException(e: ru.semina.personservice.exception.EntityNotFoundException) =
        ru.semina.personservice.dto.ErrorResponse(e.message)

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(RuntimeException::class)
    fun handleRuntimeException(e: RuntimeException) = ru.semina.personservice.dto.ErrorResponse(e.message)


}
