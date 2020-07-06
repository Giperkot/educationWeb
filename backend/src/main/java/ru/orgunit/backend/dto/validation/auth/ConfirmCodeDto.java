package ru.orgunit.backend.dto.validation.auth;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class ConfirmCodeDto {

    @NotNull
    @NotEmpty
    @Size(min = 5, max =5)
    private String confirmCode;

    public String getConfirmCode() {
        return confirmCode;
    }

    public void setConfirmCode(String confirmCode) {
        this.confirmCode = confirmCode;
    }

}
