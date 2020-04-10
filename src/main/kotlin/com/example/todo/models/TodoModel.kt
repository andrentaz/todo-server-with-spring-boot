package com.example.todo.models

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id

@Entity
class TodoModel(@Id @GeneratedValue var id: Long? = null,
                var title: String,
                var description: String)
