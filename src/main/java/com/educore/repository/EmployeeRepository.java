package com.educore.repository;

import com.educore.model.entity.Employee;
import com.educore.model.entity.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends MongoRepository<Employee, String> {

    Employee findByUser(User user);

    Employee findByJobId(String jobId);
}
