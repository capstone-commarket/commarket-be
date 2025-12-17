package com.commarket.commarket.controller;

import com.commarket.commarket.domain.User;
import com.commarket.commarket.domain.Post;
import com.commarket.commarket.dto.request.UserStatusRequest;
import com.commarket.commarket.dto.request.PostStatusRequest;
import com.commarket.commarket.dto.response.UserStatusResponse;
import com.commarket.commarket.dto.response.PostStatusResponse;
import com.commarket.commarket.service.ManagerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
public class ManagerController {
        private final ManagerService managerService;

    @PutMapping("/api/manager/users/{id}")
    public ResponseEntity<User> updateUserStatus(@PathVariable Long id, @RequestBody UserStatusRequest request) {
        User updatedUser = managerService.updateUser(id, request);
        return ResponseEntity.ok().body(updatedUser);
    }

    @PutMapping("/api/manager/posts/{id}")
    public ResponseEntity<Post> updatePostStatus(@PathVariable Long id, @RequestBody PostStatusRequest request) {
        Post updatedPost = managerService.updatePost(id, request);
        return ResponseEntity.ok().body(updatedPost);
    }

    @DeleteMapping("/api/manager/posts/{id}")
    public ResponseEntity<Void> deletePost(@PathVariable Long id) {
        managerService.deletePost(id);
        return ResponseEntity.ok().build();
    }
}