package com.example.todo.models

import javax.persistence.*

@Entity
class UserModel(@Id
                @GeneratedValue(strategy = GenerationType.AUTO)
                var id: Long? = null,
                var name: String,
                var email: String,
                var phone_number: String,
                @OneToMany(mappedBy = "user", targetEntity = TodoModel::class)
                var todos: List<TodoModel> = emptyList())
