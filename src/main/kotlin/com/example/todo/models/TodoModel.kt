package com.example.todo.models

import javax.persistence.*

@Entity
class TodoModel(@Id
                @GeneratedValue(strategy = GenerationType.AUTO)
                var id: Long? = null,
                var title: String,
                var description: String,
                @ManyToOne(fetch = FetchType.LAZY)
                @JoinColumn(name = "fk_user")
                var user: UserModel)
