package com.example.homework2_9.Contoller;


import com.example.homework2_9.Employee;
import com.example.homework2_9.Service.EmployeeService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/employee")

public class EmploeeController {

    private final EmployeeService employeeService;


    public EmploeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping(path = "/add")
    public Employee add(String name, String surname, int department, double salary) {
        return employeeService.add(name, surname, department, salary);
    }

    @GetMapping(path = "/remove")
    public Employee remove(String name, String surname) {
        return employeeService.remove(name, surname);
    }

    @GetMapping(path = "/find")
    public Employee find(String name, String surname) {
        return employeeService.find(name, surname);
    }

    @GetMapping
    public List<Employee> getAll() {
        return employeeService.getAll();
    }


}
