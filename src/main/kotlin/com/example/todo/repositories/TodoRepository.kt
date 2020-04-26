package com.example.todo.repositories

import com.example.todo.models.TodoModel
import org.springframework.dao.EmptyResultDataAccessException
import org.springframework.data.repository.CrudRepository

interface TodoRepository : CrudRepository<TodoModel, Long> {
    fun findAllByUsername(username: String): Iterable<TodoModel>

    @Throws(EmptyResultDataAccessException::class)
    fun findByIdAndUsername(id: Long, username: String): TodoModel

    fun existsByIdAndUsername(id: Long, username: String): Boolean
}
