package com.commarket.commarket.service;

import com.commarket.commarket.dto.ChangePasswordRequestDTO;
import com.commarket.commarket.dto.CreateUserRequestDTO;
import com.commarket.commarket.dto.LoginRequestDTO;
import com.commarket.commarket.dto.UserDetailResponse;
import com.commarket.commarket.entity.User;
import com.commarket.commarket.entity.UserProfile;
import com.commarket.commarket.repository.UserProfileRepository;
import com.commarket.commarket.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;



@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserServiceImpl implements UserService{
    private final UserRepository userRepository;
    private final UserProfileRepository userProfileRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    @Transactional
    public Long signUp(CreateUserRequestDTO requestDTO) {
        if (userRepository.findByEmail(requestDTO.getEmail()).isPresent()) {
            throw new IllegalArgumentException("이미 존재하는 이메일입니다.");
        }
        if (!requestDTO.getPassword().equals(requestDTO.getPasswordConfirm())) {
            throw new IllegalArgumentException("비밀번호와 확인 값이 일치하지 않습니다.");
        }
        User newUser = User.builder()
                .username(requestDTO.getUsername())
                .email(requestDTO.getEmail())
                .password_hash(passwordEncoder.encode(requestDTO.getPassword()))
                .name(requestDTO.getName())
                .build();
        //DB에 저장하고 pk가 체워진 엔티티
        User savedUser = userRepository.save(newUser);

        UserProfile profile = UserProfile.builder()
                .user(savedUser)
                .nickname(requestDTO.getNickname())
                .emailVerified(false)
                .build();
        userProfileRepository.save(profile);
        return savedUser.getUserid();
        }
        @Override
        @Transactional(readOnly = true)
        public UserDetailResponse login(LoginRequestDTO requestDTO){
            User user = userRepository.findByUsername(requestDTO.getUsername())
                    .orElseThrow(() -> new IllegalArgumentException("아이디 또는 비밀번호가 일치하지 않습니다."));
            if(!passwordEncoder.matches(requestDTO.getPassword(), user.getPassword_hash())){
                throw new IllegalArgumentException("아이디 또는 비밀번호가 일치하지 않습니다.");
            }

            UserProfile profile = user.getUserProfile();

            return UserDetailResponse.builder()
                    .userid(user.getUserid())
                    .username(user.getUsername())
                    .email(user.getEmail())
                    .name(user.getName())
                    .nickname(profile != null? profile.getNickname() : null)
                    .emailVerified(profile != null && profile.isEmailVerified())
                    .build();
        }
        @Override
        @Transactional
        public void updateNickname(Long userid, String newNickname){
            if(userProfileRepository.existsByNickname(newNickname)){
                throw new IllegalArgumentException("이미 사용 중인 닉네임입니다.");
            }

            User user = userRepository.findById(userid)
                    .orElseThrow(() -> new IllegalArgumentException("사용자를 찾을 수 없습니다."));

            UserProfile profile = user.getUserProfile();
            if(profile == null){
                throw new IllegalArgumentException("사용자 프로필 정보가 없습니다.");
            }
            profile.updateNickname(newNickname);
        }
        //TC-ChangePw-01,02
        @Override
        @Transactional
        public void changePassword(Long userid, ChangePasswordRequestDTO requestDTO){
        User user = userRepository.findById(userid)
                .orElseThrow(() -> new IllegalArgumentException("사용자를 찾을 수 없습니다."));
        //현재 비밀번호와 불일치
        if(!passwordEncoder.matches(requestDTO.getCurrentPassword(), user.getPassword_hash())){
            throw new IllegalArgumentException("현재 비밀번호와 일치하지 않습니다.");
            }
        //새 비밀번호와 확인 값이 일치 확인
        if(!requestDTO.getNewPassword().equals(requestDTO.getNewPasswordConfirm())){
            throw new IllegalArgumentException("새 비밀번호와 확인 값이 일치하지 않습니다.");
        }
        //비밀번호 변경 해시 및 저장
        user.updatePassword(passwordEncoder.encode(requestDTO.getNewPassword()));
        }
        @Override
        @Transactional
    public void deleteAccount(Long userid){
        userRepository.deleteById(userid);
        }
        public void completeEmailVerification(String email){
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new IllegalArgumentException("유효하지 않은 이메일입니다."));

        UserProfile profile = user.getUserProfile();
        if(profile == null){
            throw new IllegalArgumentException("프로필 정보가 없습니다.");
        }
        profile.verifyEmail();
        }
        @Override
        @Transactional
        public void findId(String email){
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new IllegalArgumentException("유효하지 않은 이메일입니다."));

        }


        }

