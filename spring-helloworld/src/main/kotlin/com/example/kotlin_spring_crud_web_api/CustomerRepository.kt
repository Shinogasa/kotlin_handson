package com.example.kotlin_spring_crud_web_api

import org.springframework.jdbc.core.namedparam.MapSqlParameterSource
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate
import org.springframework.stereotype.Repository

interface CustomerRepository {
    fun add(firstName: String, lastName: String)

    fun find(): List<Customer>

    fun update(id: Int, firstName: String, lastName: String)

    fun delete(id: Int)
}

@Repository
class CustomerRepositoryImpl(val namedParameterJdbcTemplate: NamedParameterJdbcTemplate) : CustomerRepository {
    override fun add(firstName: String, lastName: String) {
        val sql = """
            INSERT INTO 
                customer (
                    first_name
                    , last_name
                )
            VALUES (
                :firstName
                , :lastName
            )
            ;
        """.trimIndent()
        val sqlParams = MapSqlParameterSource()
            .addValue("firstName", firstName)
            .addValue("lastName", lastName)
        namedParameterJdbcTemplate.update(sql, sqlParams)
        return
    }

    override fun find(): List<Customer> {
       val sql = """
           SELECT
                id
                , first_name
                , last_name
           FROM
                customer
           ;
       """.trimIndent()
       val sqlParams = MapSqlParameterSource()
       val customMap = namedParameterJdbcTemplate.queryForList(sql, sqlParams)
       return customMap.map {
              Customer(
                id = it["id"].toString().toInt().toLong(),
                firstName = it["first_name"] as String,
                lastName = it["last_name"] as String
              )
       }
    }

    override fun update(id: Int, firstName: String, lastName: String) {
        val sql = """
            UPDATE
                customer
            SET
                first_name = :firstName
                , last_name = :lastName
            WHERE
                id = :id
            ;
        """.trimIndent()
        val sqlParams = MapSqlParameterSource()
            .addValue("id", id)
            .addValue("firstName", firstName)
            .addValue("lastName", lastName)
        namedParameterJdbcTemplate.update(sql, sqlParams)
        return
    }

    override fun delete(id: Int) {
        val sql = """
            DELETE FROM
                customer
            WHERE
                id = :id
            ;
        """.trimIndent()
        val sqlParams = MapSqlParameterSource()
            .addValue("id", id)
        namedParameterJdbcTemplate.update(sql, sqlParams)
        return
    }
}