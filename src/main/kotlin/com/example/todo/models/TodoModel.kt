package com.example.todo.models

import javax.persistence.*

@Entity
@Table(name = "todo", indexes = [
    Index(name = "idx_user_username", columnList = "username")
])
class TodoModel(@Id
                @GeneratedValue(strategy = GenerationType.AUTO)
                var id: Long? = null,
                var title: String,
                var description: String,
                var username: String = "")
