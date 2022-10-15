package com.example.homework2_9.Contoller;


import com.example.homework2_9.Employee;
import com.example.homework2_9.Service.DepartmentService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/departments")
public class DepartmentController {
    private final DepartmentService departmentService;

    public DepartmentController(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    @GetMapping("/max-salary")
    public Employee findDepartmentMaxSalary(Integer department) {
        return departmentService.findDepartmentMaxSalary(department);
    }

    @GetMapping("/min-salary")
    public Employee findDepartmentMinSalary(Integer department) {
        return departmentService.findDepartmentMinSalary(department);
    }

    @GetMapping(value = "/all", params = "department")
    public Collection<Employee> findDepartmentCollection(Integer department) {
        return departmentService.findDepartmentCollection(department);
    }

    @GetMapping("/all")
    public Map<Integer, List<Employee>> findDepartmentAll() {
        return departmentService.findDepartmentAll();
    }
}
