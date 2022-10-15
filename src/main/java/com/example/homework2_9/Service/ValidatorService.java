package com.example.homework2_9.Service;


import com.example.homework2_9.Exception.EmployeeStorageIsFullException;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

@Service
public class ValidatorService {
    public String validator(String param) {
        if (!StringUtils.isAlpha(param)) {
            throw new EmployeeStorageIsFullException();
        }
        return StringUtils.capitalize(param.toLowerCase());
    }

}
