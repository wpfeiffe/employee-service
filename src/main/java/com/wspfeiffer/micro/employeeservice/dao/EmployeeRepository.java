package com.wspfeiffer.micro.employeeservice.dao;

import com.wspfeiffer.micro.employeeservice.domain.EmployeeEntity;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(collectionResourceRel = "employees", path = "employees")
public interface EmployeeRepository extends PagingAndSortingRepository<EmployeeEntity, Long> {
}
