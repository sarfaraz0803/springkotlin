package com.springkotlin.model

import lombok.Data
import org.bson.types.ObjectId
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document
import kotlin.properties.Delegates


@Document("employee")

data class Employee(
    @Id
    var id: String? = ObjectId().toHexString(),
    val firstName: String,
    val lastName: String,
    val emailId: String
)