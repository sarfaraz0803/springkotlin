package com.springkotlin.controller

import com.springkotlin.model.Employee
import com.springkotlin.repository.EmployeeRepository
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.util.*

@RestController
@RequestMapping("/api/v1")
class EmployeeController(private val employeeRepository: EmployeeRepository) {

    @GetMapping("/getEmployees")
    fun getAllEmployees() : List<Employee> = employeeRepository.findAll()

    @PostMapping("/addEmployee")
    fun createNewEmployee(@RequestBody employee: Employee) : Employee = employeeRepository.save(employee)

    @GetMapping("/getEmployeeById/{id}")
    fun getEmployeeById(@PathVariable(value = "id") id:String ): ResponseEntity<Employee> {
      return employeeRepository.findById(id).map{ emp ->  ResponseEntity.ok(emp)}.orElse(ResponseEntity.notFound().build())
    }

    @PutMapping("/updateEmployeeById/{id}")
    fun updateEmployeeById(@PathVariable(value="id") id:String, @RequestBody newEmployee: Employee): ResponseEntity<Employee> {
        return employeeRepository.findById(id).map { existingEmployee ->  val updatedEmployee:Employee =
            existingEmployee.copy(firstName = newEmployee.firstName, lastName = newEmployee.lastName, emailId = newEmployee.emailId)
            ResponseEntity.ok().body(employeeRepository.save(updatedEmployee))
        }.orElse(ResponseEntity.notFound().build())
    }


    @DeleteMapping("/deleteEmployeeById/{id}")
    fun deleteEmployeeById(@PathVariable(value = "id") employeeId: String):
            ResponseEntity<Void> {
        return employeeRepository.findById(employeeId).map { emp ->
            employeeRepository.delete(emp)
            ResponseEntity<Void>(HttpStatus.OK)
        }.orElse(ResponseEntity.notFound().build())

    }


}