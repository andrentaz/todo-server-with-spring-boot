package com.example.todo.controllers

import com.example.todo.models.TodoModel
import com.example.todo.repositories.TodoRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*
import org.springframework.web.server.ResponseStatusException

@RestController
@RequestMapping("/api/todos")
class TodoController(private val repository: TodoRepository) {

    @GetMapping("/{id}")
    fun getTodo(@PathVariable(value = "id") id: String) =
            repository.findByIdOrNull(id.toLong())
                    ?: ResponseStatusException(HttpStatus.NOT_FOUND, "This TODO does not exist")

    @PostMapping("/")
    fun createTodo(@RequestBody todo: TodoModel) =
            repository.save(todo)
}
