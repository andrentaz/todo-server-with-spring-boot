package com.example.todo.controllers

import com.example.todo.models.TodoModel
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.boot.test.web.client.getForEntity
import org.springframework.http.HttpStatus

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class TodoControllerTest(@Autowired val restTemplate: TestRestTemplate) {

    @Test
    fun `Assert todo endpoint returns a json with todo template`() {
        val entity = restTemplate.getForEntity<TodoModel>("/todos/1")
        val todo = TodoModel("1", "This is my todo", "This is my todo's description")
        val body = entity.body as TodoModel
        assert(entity.statusCode == HttpStatus.OK)
        assert(body == todo)
    }
}
