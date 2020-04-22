package com.example.todo.repositories

import com.example.todo.models.TodoModel
import com.example.todo.models.UserModel
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
        val user = UserModel(
                name = "John Doe",
                email = "john@doe.com",
                phone_number = "+5511987654321"
        )
        val todo = TodoModel(
                title = "test my models",
                description = "need to implement tests",
                user = user
        )
        entityManager.persist(user)
        entityManager.persist(todo)
        entityManager.flush()

        val todoId = todo.id ?: 0
        val found = todoRepository.findByIdOrNull(todoId)
        assert(found == todo)
    }
}