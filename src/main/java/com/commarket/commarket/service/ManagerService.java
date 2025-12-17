package com.commarket.commarket.service;

import com.commarket.commarket.domain.Post;
import com.commarket.commarket.dto.request.PostStatusRequest;
import com.commarket.commarket.repository.PostRepository;

import com.commarket.commarket.domain.User;
import com.commarket.commarket.dto.request.UserStatusRequest;
import com.commarket.commarket.repository.UserRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class ManagerService {
    public final PostRepository postRepository;
    public final UserRepository userRepository;

    public void deletePost(Long id) {
        postRepository.deleteById(id);
    }

    public Post updatePost(Long id, PostStatusRequest request) {
        Post post = postRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Post not found with id: " + id));

        post.updateActive(request.isActive());

        return post;
    }

    public User updateUser(Long id, UserStatusRequest request) {
        User user = userRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("User not found with id: " + id));

        user.updateActive(request.isActive());

        return user;
    }
}
