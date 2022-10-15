package com.example.homework2_9.ServiceTest;


import com.example.homework2_9.Employee;
import com.example.homework2_9.Exception.EmployeeAlreadyAddedException;
import com.example.homework2_9.Exception.EmployeeNotFoundException;
import com.example.homework2_9.Exception.EmployeeStorageIsFullException;
import com.example.homework2_9.Exception.IncorrectNameException;
import com.example.homework2_9.Service.EmployeeService;
import com.example.homework2_9.Service.ValidatorService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

public class EmployeeServiceTest {

    private final EmployeeService employeeService = new EmployeeService(new ValidatorService());

    public static Stream<Arguments> addNegativ1Params() {
        return Stream.of(
                Arguments.of("Ivan1", "Ivanov", IncorrectNameException.class),
                Arguments.of("Ivan", "Ivanov", IncorrectNameException.class),
                Arguments.of("Ivan!", "Ivanov", IncorrectNameException.class),
                Arguments.of("Ivan1", "Ivanov", IncorrectNameException.class),
                Arguments.of("Ivan1", "Ivanov,ivanov", IncorrectNameException.class),
                Arguments.of("Ivan1", "Ivanov-petrov", IncorrectNameException.class));
    }

    @AfterEach
    public void afterEach() {
        employeeService.getAll()
                .forEach(employee -> employeeService.remove(employee.getName(), employee.getSurname()));
    }

    @Test
    public void addPozitivTest() {
        addOneWithCheck();

    }

    private Employee addOneWithCheck(String name, String surname) {
        Employee expected = new Employee(name, surname, 1, 20_500);
        int sizeBefore = employeeService.getAll().size();
        employeeService.add(expected.getName(), expected.getSurname(), expected.getDepartment(), expected.getSalary());
        assertThat(employeeService.getAll())
                .isNotEmpty()
                .hasSize(sizeBefore + 1)
                .contains(expected);
        assertThat(employeeService.find(expected.getName(), expected.getSurname())).isEqualTo(expected);
        return expected;

    }

    private Employee addOneWithCheck() {
        return addOneWithCheck("name", "surname");
    }

    @ParameterizedTest
    @MethodSource("addNegativ1Params")
    @Test
    public void addNegativ1Test(String name,
                                String surname,
                                Class<Throwable> expectedExeptionType) {
        assertThatExceptionOfType(expectedExeptionType)
                .isThrownBy(() -> employeeService.add(name, surname, 1, 20_500));

    }

    @Test
    public void addNegativ2Test() {
        Employee employee = addOneWithCheck();
        assertThatExceptionOfType(EmployeeAlreadyAddedException.class)
                .isThrownBy(() -> employeeService.add(employee.getName(), employee.getSurname(), employee.getDepartment(), employee.getSalary()));
    }

    @Test
    public void addNegativ3Test() {
        for (int i = 0; i < 10; i++) {
            addOneWithCheck("Name" + (char) ('a' + i), "SurName" + (char) ('a' + i));
        }
        assertThatExceptionOfType(EmployeeStorageIsFullException.class)
                .isThrownBy(() -> employeeService.add("Name", "SurName", 1, 123));
    }

    @Test
    public void findPositive() {
        Employee employee1 = addOneWithCheck("Name", "SurName");
        Employee employee2 = addOneWithCheck("имя", "Фамилия");
        assertThat(employeeService.find(employee1.getName(), employee1.getSurname()))
                .isEqualTo(employee1);
        assertThat(employeeService.find(employee2.getName(), employee2.getSurname()))
                .isEqualTo(employee2);
    }

    @Test
    public void findNegative() {
        assertThatExceptionOfType(EmployeeNotFoundException.class)
                .isThrownBy(() -> employeeService.find("test", "test"));
        addOneWithCheck("Name", "SurName");
        addOneWithCheck("имя", "Фамилия");
        assertThatExceptionOfType(EmployeeNotFoundException.class)
                .isThrownBy(() -> employeeService.find("test", "test"));
    }

    @Test
    public void removePositive() {
        assertThatExceptionOfType(EmployeeNotFoundException.class)
                .isThrownBy(() -> employeeService.find("test", "test"));
        addOneWithCheck("Name", "SurName");
        addOneWithCheck("имя", "Фамилия");
        assertThatExceptionOfType(EmployeeNotFoundException.class)
                .isThrownBy(() -> employeeService.find("test", "test"));


    }

    @Test
    public void removeNegative() {
        Employee employee1 = addOneWithCheck("Name", "SurName");
        Employee employee2 = addOneWithCheck("имя", "Фамилия");
        employeeService.remove(employee1.getName(), employee1.getSurname());
        assertThat(employeeService.getAll())
                .isNotEmpty()
                .hasSize(+1)
                .containsExactly(employee2);
        employeeService.remove(employee2.getName(), employee2.getSurname());
        assertThat(employeeService.getAll().isEmpty());


    }


}
