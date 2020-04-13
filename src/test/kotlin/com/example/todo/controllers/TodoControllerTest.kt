package com.example.todo.controllers

import com.example.todo.models.TodoModel
import com.example.todo.repositories.TodoRepository
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.boot.test.web.client.getForEntity
import org.springframework.data.repository.findByIdOrNull
import org.springframework.http.HttpEntity
import org.springframework.http.HttpStatus

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class TodoControllerTest @Autowired constructor(val restTemplate: TestRestTemplate,
                                                val repository: TodoRepository) {

    @BeforeAll
    fun setup() {
        val todo = TodoModel(
                title = "This is my todo",
                description = "This is my todo's description"
        )
        repository.save(todo)
    }

    @Test
    fun `Assert todo endpoint returns a json with todo template`() {
        val entity = restTemplate.getForEntity<TodoModel>("/api/todos/1")
        val todo = TodoModel(
                id = 1,
                title = "This is my todo",
                description = "This is my todo's description"
        )
        val body = entity.body as TodoModel

        assert(entity.statusCode == HttpStatus.OK)
        assert(body.id == todo.id)
        assert(body.title == todo.title)
        assert(body.description == todo.description)
    }

    @Test
    fun `Assert 404 if todo not found`() {
        val id = 123456789
        val entity = restTemplate.getForEntity<Any>("/api/todos/$id")
        val body = entity.body as LinkedHashMap<String, String>
        assert(entity.statusCode == HttpStatus.NOT_FOUND)
        assert(body["type"] == "TodoNotFound")
        assert(body["message"] == "TODO with ID: $id does not exist")
    }

    @Test
    fun `Assert all endpoint returns a list of json`() {
        val entity = restTemplate.getForEntity<List<LinkedHashMap<String, Any>>>("/api/todos/")
        val body = entity.body as List<LinkedHashMap<String, Any>>

        assert(body.isNotEmpty())
        assert(body.size == 1)

        val todo = body[0]
        assert(todo["id"] == 1)
        assert(todo["title"] == "This is my todo")
        assert(todo["description"] == "This is my todo's description")
    }

    @Test
    fun `Assert delete todos exclude if in database`() {
        setup()
        assert(repository.findByIdOrNull(2.toLong()) != null)

        restTemplate.delete("/api/todos/2")
        assert(repository.findByIdOrNull(2.toLong()) == null)
    }

    @Test
    fun `Assert todo endpoint inserts in database`() {
        val todo = TodoModel(
                title = "Test the post",
                description = "I need to test the post api"
        )
        val request: HttpEntity<TodoModel> = HttpEntity(todo)
        val postedTodo = restTemplate
                .postForObject("/api/todos/", request, TodoModel::class.java)

        assert(postedTodo != null)
        assert(postedTodo.id == (2.toLong()))
        assert(todo.title == postedTodo.title)
        assert(todo.description == postedTodo.description)

        // get the second object since the first is inserted in the tests setup
        val entity = restTemplate.getForEntity<TodoModel>("/api/todos/2")
        val body = entity.body as TodoModel

        assert(entity.statusCode == HttpStatus.OK)
        assert(body.id == postedTodo.id)
        assert(body.title == postedTodo.title)
        assert(body.description == postedTodo.description)
    }
}
