package com.example.domain.service

import com.example.data.UserRepository

object UserService {
    fun create(name: String) = UserRepository.create(name)
    fun find(id: Int) = UserRepository.find(id)
}