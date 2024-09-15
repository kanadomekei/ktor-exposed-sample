package com.example

import org.jetbrains.exposed.sql.transactions.transaction

fun testRollbackScope(test: () -> Unit) {
    transaction {
        test()
        rollback()
    }
}