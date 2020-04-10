package com.example.todo.repositories

import com.example.todo.models.TodoModel
import org.springframework.data.repository.CrudRepository

interface TodoRepository : CrudRepository<TodoModel, Long>
