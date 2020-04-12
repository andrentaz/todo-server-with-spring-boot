package com.example.todo.controllers.advices

import com.example.todo.controllers.exceptions.TodoNotFoundException
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseBody
import org.springframework.web.bind.annotation.ResponseStatus

@ControllerAdvice
class TodoControllerAdvice {
    @ResponseBody
    @ExceptionHandler(TodoNotFoundException::class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    fun todoNotFoundHandler(ex: TodoNotFoundException): Any {
        val errorData = LinkedHashMap<String, String>()
        errorData["type"] = "TodoNotFound"
        errorData["message"] = ex.message as String
        return errorData
    }
}
