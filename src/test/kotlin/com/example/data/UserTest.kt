package com.example.data

import com.example.domain.model.User
import com.example.testRollbackScope
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe
import io.kotest.matchers.shouldNotBe

internal class UserTest : StringSpec({
    beforeSpec { Migration() }
    "test" {
        testRollbackScope {
            val id = UserRepository.create("test")
            val user = UserRepository.find(id)

            user shouldNotBe null
            user shouldBe User(id, "test")
        }
    }
})