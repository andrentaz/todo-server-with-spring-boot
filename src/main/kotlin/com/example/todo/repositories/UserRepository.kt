package com.example.todo.repositories

import com.example.todo.models.UserModel
import org.springframework.data.repository.CrudRepository

interface UserRepository : CrudRepository<UserModel, Long>
