package com.commarket.commarket.service;

import com.commarket.commarket.dto.request.ChangePasswordRequestDTO;
import com.commarket.commarket.dto.request.CreateUserRequestDTO;
import com.commarket.commarket.dto.request.LoginRequestDTO;
import com.commarket.commarket.dto.response.UserDetailResponse;

public interface UserService {
    //TC-signUp-01,02,03

    Long signUp(CreateUserRequestDTO requestDTO);

    //TC-Edit-01

    void updateNickname(Long userId, String newNickname);



    //TC-ChangePw-01,02
    void changePassword(Long userId, ChangePasswordRequestDTO requestDTO);

    //TC-DeleteAccount-01
    void deleteAccount(Long userId);

    //TC-Verify-04
    void completeEmailVerification(String email);

    //TC-Login-01,02
    UserDetailResponse login(LoginRequestDTO requestDTO);

    //TC-FindId-01,02
    void findId(String email);

}
