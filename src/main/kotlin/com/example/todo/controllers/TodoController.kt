package com.example.todo.controllers

import com.example.todo.controllers.exceptions.TodoNotFoundException
import com.example.todo.models.TodoModel
import com.example.todo.repositories.TodoRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/todos")
class TodoController(private val repository: TodoRepository) {

    @GetMapping("/{id}")
    fun getTodo(@PathVariable(value = "id") id: Long) =
            repository.findByIdOrNull(id) ?: throw TodoNotFoundException(id)

    @GetMapping("/")
    fun getAllTodos() =
            repository.findAll()

    @DeleteMapping("/{id}")
    fun deleteTodo(@PathVariable(value = "id") id: Long) {
        if(repository.existsById(id)) {
            repository.deleteById(id)
        } else {
            throw TodoNotFoundException(id)
        }
    }

    @PostMapping("/")
    fun createTodo(@RequestBody todo: TodoModel) =
            repository.save(todo)
}
