package com.example.data

import com.example.domain.model.User
import org.jetbrains.exposed.dao.id.IntIdTable
import org.jetbrains.exposed.sql.Column
import org.jetbrains.exposed.sql.insertAndGetId
import org.jetbrains.exposed.sql.select
import org.jetbrains.exposed.sql.transactions.transaction

object UserTable : IntIdTable("user") {
    val name: Column<String> = varchar("name", 50)
}

object UserRepository {
    fun create(name: String) = transaction {
        UserTable.insertAndGetId { it[this.name] = name }.value
    }

    fun find(id: Int) = transaction {
        UserTable.select { UserTable.id eq id }.singleOrNull()?.let {
            User(it[UserTable.id].value, it[UserTable.name])
        }
    }
}
