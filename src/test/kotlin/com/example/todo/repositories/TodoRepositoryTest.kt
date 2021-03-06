package com.example.todo.repositories

import com.example.todo.models.TodoModel
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager
import org.springframework.data.repository.findByIdOrNull

@DataJpaTest
class TodoRepositoryTest @Autowired constructor(val entityManager: TestEntityManager,
                                                val todoRepository: TodoRepository) {

    @Test
    fun `When findByIdOrNull then return TODO item`() {
        val todo = TodoModel(
                title = "test my models",
                description = "need to implement tests",
                username = "any-user-name"
        )
        entityManager.persist(todo)
        entityManager.flush()

        val todoId = todo.id ?: 0
        val found = todoRepository.findByIdOrNull(todoId)
        assert(found == todo)
    }
}