package ru.sber.rdbms

import java.sql.DriverManager

fun main() {

    TransferConstraint().transfer(2, 1, 100)
}
class TransferConstraint {

    val connection = DriverManager.getConnection(
        "jdbc:postgresql://localhost:5432/db",
        "postgres",
        "postgres"
    )
    fun transfer(accountId1: Long, accountId2: Long, amount: Long) {

        connection.use { conn ->
            val prepareStatement = conn.prepareStatement(
                "update account1 set amount = amount - ? where id = ?;" +
                        "update account1 set amount = amount + ? where id = ?;" )

            prepareStatement.use { statement ->
                statement.setLong(1, amount)
                statement.setLong(2, accountId1)
                statement.setLong(3, amount)
                statement.setLong(4, accountId2)

                val result = statement.executeUpdate()
                println(result)
            }
        }
    }
}
