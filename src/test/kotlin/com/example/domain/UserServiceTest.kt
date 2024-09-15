package com.example.domain

import com.example.data.Migration
import com.example.domain.model.User
import com.example.domain.service.UserService
import com.example.testRollbackScope
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe
import io.kotest.matchers.shouldNotBe

internal class UserServiceTest : StringSpec({
    beforeSpec { Migration() }
    "test" {
        testRollbackScope {
            val id = UserService.create("test2")
            val user = UserService.find(id)

            user shouldNotBe null
            user shouldBe User(id, "test2")
        }
    }
})