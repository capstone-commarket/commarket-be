package com.commarket.commarket.controller;

import com.commarket.commarket.dto.ChangePasswordRequestDTO;
import com.commarket.commarket.dto.CreateUserRequestDTO;
import com.commarket.commarket.dto.LoginRequestDTO;
import com.commarket.commarket.dto.NicknameUpdateRequestDTO;
import com.commarket.commarket.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController //요청에 응답한다.
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {
    private final UserService userService;
   @PostMapping("/signup")
    public ResponseEntity<String > signUp(@RequestBody CreateUserRequestDTO requestDTO){
       Long userid = userService.signUp(requestDTO);

       return new ResponseEntity<>("회원가입 성공(ID: "+userid + ")", HttpStatus.CREATED);
    }
    @PostMapping("/login")
    public ResponseEntity<String > login(@RequestBody LoginRequestDTO requestDTO){
       userService.login(requestDTO);
       return ResponseEntity.ok("로그인 성공 알림");
    }
    @PutMapping("/{userId}/nickname")
    public ResponseEntity<String> updateNickname(@PathVariable Long userid, @RequestBody NicknameUpdateRequestDTO requestDTO){
       userService.updateNickname(userid, requestDTO.getNickname());
       return ResponseEntity.ok("정보가 성공적으로 수정되었다는 알림을 보낸다.");
    }
    @PutMapping("/{userId}/password")
    public ResponseEntity<String> changePassword(@PathVariable Long userid, @RequestBody ChangePasswordRequestDTO requestDTO){
       userService.changePassword(userid, requestDTO);
       return ResponseEntity.ok("비밀번호가 성공적으로 변경되었다는 알림을 보낸다.");
    }
    @DeleteMapping("/{userId}/delete")
    public ResponseEntity<String> deleteAccount(@PathVariable Long userid){
       userService.deleteAccount(userid);
       return ResponseEntity.ok("회원 탈퇴 성공");
    }
    @PostMapping("/findID")
    public ResponseEntity<String> findId(@RequestBody String email){
       userService.findId(email);
       return ResponseEntity.ok("아이디 찾기 알림을 보낸다.");
    }
}
