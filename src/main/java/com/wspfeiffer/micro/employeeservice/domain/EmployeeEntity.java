package com.wspfeiffer.micro.employeeservice.domain;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "Employee")
@Data
public class EmployeeEntity {

    @Id
    @SequenceGenerator(name = "employee_id_seq", sequenceName = "employee_id_seq")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "employee_id_seq")
    private Long id;

    @Basic
    private String email;

    @Basic
    private String firstName;

    @Basic
    private String lastName;

    @Basic
    private String title;
    
}
