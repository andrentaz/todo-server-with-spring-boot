package com.example.todo.models

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id

@Entity
class UserModel(@Id
                @GeneratedValue(strategy = GenerationType.AUTO)
                var id: Long? = null,
                var name: String,
                var email: String,
                var phone_number: String)
