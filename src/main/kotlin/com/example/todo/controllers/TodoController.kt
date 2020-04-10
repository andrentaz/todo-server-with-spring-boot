package com.example.todo.controllers

import com.example.todo.models.TodoModel
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RestController

@RestController
class TodoController {

    @GetMapping("/todos/{id}")
    fun getTodo(@PathVariable(value = "id") id: String): TodoModel {
        val templateTitle = "This is my todo"
        val templateDescription = "This is my todo's description"
        return TodoModel(id = id.toLong(), title = templateTitle, description = templateDescription)
    }
}