package com.example.homework2_9.Service;


import com.example.homework2_9.Employee;
import com.example.homework2_9.Exception.EmployeeAlreadyAddedException;
import com.example.homework2_9.Exception.EmployeeNotFoundException;
import com.example.homework2_9.Exception.EmployeeStorageIsFullException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class EmployeeService {

    private static final int LIMIT = 3;
    public final Map<String, Employee> employees = new HashMap<>();
    private final ValidatorService validatorService;

    public EmployeeService(ValidatorService validatorService) {
        this.validatorService = validatorService;
    }

    private String getKey(String name, String surname) {
        return name + "|" + surname;
    }


    public Employee add(String name, String surname, int department, double salary) {
        Employee employee = new Employee(validatorService.validator(name),
                validatorService.validator(surname),
                department,
                salary);
        String key = getKey(name, surname);

        if (employees.containsKey(key)) {
            throw new EmployeeAlreadyAddedException();
        }

        if (employees.size() < LIMIT) {
            employees.put(key, employee);
            return employee;
        }
        throw new EmployeeStorageIsFullException();

    }

    public Employee remove(String name, String surname) {
        String key = getKey(name, surname);
        if (!employees.containsKey(key)) {
            throw new EmployeeNotFoundException();
        }
        return employees.remove(key);
    }

    public Employee find(String name, String surname) {
        String key = getKey(name, surname);
        if (employees.containsKey(key)) {
            throw new EmployeeNotFoundException();
        }
        return employees.get(key);
    }

    public List<Employee> getAll() {
        return new ArrayList<>(employees.values());
    }


}

