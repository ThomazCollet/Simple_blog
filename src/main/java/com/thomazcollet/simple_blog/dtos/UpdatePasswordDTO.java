package com.thomazcollet.simple_blog.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class UpdatePasswordDTO {

    @NotBlank
    @Size(min = 6, max = 30)
    private String oldPassword;

    @NotBlank
    @Size(min = 6, max = 30)
    private String newPassword;

    public UpdatePasswordDTO() {}

    public UpdatePasswordDTO(String oldPassword, String newPassword) {
        this.oldPassword = oldPassword;
        this.newPassword = newPassword;
    }

    public String getOldPassword() {
        return oldPassword;
    }

    public void setOldPassword(String oldPassword) {
        this.oldPassword = oldPassword;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }
}
