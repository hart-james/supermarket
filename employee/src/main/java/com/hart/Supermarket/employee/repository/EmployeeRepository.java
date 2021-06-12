package com.hart.Supermarket.employee.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.UUID;

public interface EmployeeRepository extends MongoRepository<Employee, String> {

    //GET
    Employee findEmployeeByUuid(String uuid);
    Employee findEmployeeByEmail(String email);

    //DELETE
    String deleteEmployeeByUuid(String uuid);
    String deleteEmployeeByEmail(String email);
}
