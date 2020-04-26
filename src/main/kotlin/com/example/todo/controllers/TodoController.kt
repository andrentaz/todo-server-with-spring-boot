package com.example.todo.controllers

import com.example.todo.auth.JwtAuthenticationService
import com.example.todo.controllers.exceptions.TodoNotFoundException
import com.example.todo.models.TodoModel
import com.example.todo.repositories.TodoRepository
import org.springframework.dao.EmptyResultDataAccessException
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/todos")
class TodoController(private val jwtAuthenticationService: JwtAuthenticationService,
                     private val repository: TodoRepository) {

    @GetMapping("/{id}")
    fun getTodo(@PathVariable(value = "id") id: Long): TodoModel {
        try {
            val username = jwtAuthenticationService.getClaims().username
            return repository.findByIdAndUsername(id, username)
        } catch (e: EmptyResultDataAccessException) {
            throw TodoNotFoundException(id)
        }
    }

    @GetMapping("/")
    fun getAllTodos(): Iterable<TodoModel> {
        val username = jwtAuthenticationService.getClaims().username
        return repository.findAllByUsername(username = username)
    }


    @DeleteMapping("/{id}")
    fun deleteTodo(@PathVariable(value = "id") id: Long) {
        val username = jwtAuthenticationService.getClaims().username
        if(repository.existsByIdAndUsername(id, username)) {
            repository.deleteById(id)
        } else {
            throw TodoNotFoundException(id)
        }
    }

    @PostMapping("/")
    @ResponseStatus(HttpStatus.CREATED)
    fun createTodo(@RequestBody todo: TodoModel) {
        todo.username = jwtAuthenticationService.getClaims().username
        repository.save(todo)
    }
}
