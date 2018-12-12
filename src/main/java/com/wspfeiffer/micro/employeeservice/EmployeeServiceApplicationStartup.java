package com.wspfeiffer.micro.employeeservice;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.wspfeiffer.micro.employeeservice.dao.EmployeeRepository;
import com.wspfeiffer.micro.employeeservice.domain.EmployeeEntity;
import com.wspfeiffer.micro.employeeservice.dto.EmployeeDTO;
import lombok.Data;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;
import org.springframework.util.ResourceUtils;


import java.net.URL;
import java.util.List;

@Component
public class EmployeeServiceApplicationStartup implements ApplicationListener<ApplicationReadyEvent> {

    static Logger logger = LoggerFactory.getLogger(EmployeeServiceApplicationStartup.class);

    private final EmployeeRepository employeeRepository;


    public EmployeeServiceApplicationStartup(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @Override
    public void onApplicationEvent(final ApplicationReadyEvent event) {
        try {
            URL mockEmployeeJsonPath = ResourceUtils.getURL("classpath:data/employee.json");
            ObjectMapper objectMapper = new ObjectMapper();
            List<EmployeeDTO> employees = objectMapper.readValue(mockEmployeeJsonPath.openStream(), new TypeReference<List<EmployeeDTO>>() {});
            for (EmployeeDTO employee: employees) {
                EmployeeEntity employeeEntity = new EmployeeEntity();
                employeeEntity.setFirstName(employee.getFirstName());
                employeeEntity.setLastName(employee.getLastName());
                employeeEntity.setEmail(employee.getEmail());
                employeeEntity.setTitle(employee.getTitle());
                this.employeeRepository.save(employeeEntity);
            }
        }
        catch(Exception e) {
            logger.error("Failed to load employee data", e);
        }

    }

}
