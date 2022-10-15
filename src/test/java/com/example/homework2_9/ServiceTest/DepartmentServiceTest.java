package com.example.homework2_9.ServiceTest;

import com.example.homework2_9.Employee;
import com.example.homework2_9.Service.DepartmentService;
import com.example.homework2_9.Service.EmployeeService;
import org.assertj.core.util.Arrays;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.stream.Stream;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class DepartmentServiceTest {

    @Mock
    private EmployeeService employeeService;

    @InjectMocks
    private DepartmentService departmentService;

    public static Stream<Arguments> maxSalaryTestParams() {

        return Stream.of(
                Arguments.of(2, new Employee("sasha", "ivanov", 2, 129)),
                Arguments.of(2, new Employee("vila", "ivanov", 5, 128))
        );
    }

    @BeforeEach
    public void beforeEach() {
        when(employeeService.getAll()).thenReturn(
                Arrays.asList(
                        new Employee("vova", "ivanov", 7, 122),
                        new Employee("vila", "ivanov", 5, 128),
                        new Employee("pova", "ivanov", 2, 121),
                        new Employee("ira", "ivanov", 4, 125),
                        new Employee("sasha", "ivanov", 2, 129)));

    }

    @ParameterizedTest
    @MethodSource("maxSalaryTestParams")
    public void maxSalaryTest(int department, Employee expected) {
        assertThat(departmentService.findDepartmentMaxSalary(department).isEqualTo(expected);
    }
}
