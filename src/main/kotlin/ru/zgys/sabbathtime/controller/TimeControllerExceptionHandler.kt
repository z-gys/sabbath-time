package ru.zgys.sabbathtime.controller

import org.springframework.http.HttpStatus.GONE
import org.springframework.http.HttpStatus.I_AM_A_TEAPOT
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import ru.zgys.sabbathtime.exception.SabbathTimeException

/**
 * @author U.Goryntsev 28.10.2018.
 */
@ControllerAdvice
class TimeControllerExceptionHandler {

    @ExceptionHandler(SabbathTimeException::class)
    fun handleSabbathTimeException(exception: SabbathTimeException)= ResponseEntity("Sabbath Time!", GONE)

    @ExceptionHandler(Exception::class)
    fun handleAllException(ex: Exception): ResponseEntity<Any> = ResponseEntity(I_AM_A_TEAPOT)
}