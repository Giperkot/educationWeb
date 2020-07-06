package ru.orgunit.backend.dto;

import org.springframework.http.HttpStatus;
import ru.orgunit.backend.exceptions.SirException;

import java.util.List;

public class BaseDto<T extends DataObject> {
    private ErrorDto error = null;
    private HttpStatus status = HttpStatus.OK;
    private List<T> data;

    public BaseDto() {
    }

    public BaseDto(List<T> data) {
        this.data = data;
    }

    public BaseDto(HttpStatus status, Exception ex) {
        error = new ErrorDto();
        error.setErrorMessage(ex.getMessage());
        this.status = status;

        StringBuilder sb = new StringBuilder();
        for (StackTraceElement elm : ex.getStackTrace()) {
            sb.append(elm);
        }
        error.setStackTrace(sb.toString());

        if (ex instanceof SirException) {
            error.setErrorStatus(((SirException)ex).getStatus().name());
        }
    }

    public List<T> getData() {
        return data;
    }

    public void setData(List<T> data) {
        this.data = data;
    }

    public ErrorDto getError() {
        return error;
    }

    public void setError(ErrorDto error) {
        this.error = error;
    }

    public HttpStatus getStatus() {
        return status;
    }

    public void setStatus(HttpStatus status) {
        this.status = status;
    }
}
