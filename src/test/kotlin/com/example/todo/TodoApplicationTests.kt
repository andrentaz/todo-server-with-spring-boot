package com.example.todo

import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.boot.test.web.client.getForEntity
import org.springframework.http.HttpStatus

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class TodoApplicationTests(@Autowired val restTemplate: TestRestTemplate) {

	@Test
	fun `Assert hello world response has default value`() {
		val entity = restTemplate.getForEntity<String>("/hello")
		assert(entity.statusCode == HttpStatus.OK)
		assert(entity.body == "Hello World")
	}

	@Test
	fun `Assert hello world response uses query parameters`() {
		val name = "Gandalf"
		val entity = restTemplate.getForEntity<String>("/hello?name=$name")
		assert(entity.statusCode == HttpStatus.OK)
		assert(entity.body == "Hello $name")
	}
}
