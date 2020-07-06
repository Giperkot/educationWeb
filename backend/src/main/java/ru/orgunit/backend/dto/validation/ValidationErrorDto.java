package ru.orgunit.backend.dto.validation;


import org.springframework.http.HttpStatus;
import ru.orgunit.backend.dto.BaseDto;

import java.util.List;

public class ValidationErrorDto extends BaseDto {

    private List<ApiFieldError> fieldErrors;

    public ValidationErrorDto(HttpStatus status, Exception ex) {
        super(status, ex);
    }

    public ValidationErrorDto(HttpStatus status, Exception ex, List<ApiFieldError> fieldErrors) {
        super(status, ex);
        this.fieldErrors = fieldErrors;
    }

    public List<ApiFieldError> getFieldErrors() {
        return fieldErrors;
    }

    public void setFieldErrors(List<ApiFieldError> fieldErrors) {
        this.fieldErrors = fieldErrors;
    }
}
