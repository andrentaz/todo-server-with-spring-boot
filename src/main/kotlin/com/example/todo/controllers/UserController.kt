package com.example.todo.controllers

import com.example.todo.models.UserModel
import com.example.todo.repositories.UserRepository
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/users")
class UserController(private val repository: UserRepository) {

    @PostMapping("/signup")
    fun signup(@RequestBody user: UserModel) =
            repository.save(user)

    @GetMapping("/")
    fun getAll() = repository.findAll()
}
