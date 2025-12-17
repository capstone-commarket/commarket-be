package com.commarket.commarket.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ChangePasswordRequestDTO {
private String currentPassword;
private String newPassword;
private String newPasswordConfirm;
}
