package com.springkotlin.repository

import com.springkotlin.model.Employee
import org.springframework.data.mongodb.repository.MongoRepository

interface EmployeeRepository : MongoRepository<Employee, String>