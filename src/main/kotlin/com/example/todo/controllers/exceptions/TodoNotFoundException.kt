package com.example.todo.controllers.exceptions

class TodoNotFoundException(id: Long) : RuntimeException("TODO with ID: $id does not exist")
