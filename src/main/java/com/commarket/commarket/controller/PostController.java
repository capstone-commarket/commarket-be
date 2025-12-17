package com.commarket.commarket.controller;

import com.commarket.commarket.dto.request.PostCreateRequest;
import com.commarket.commarket.dto.response.PostCreateResponse;
import com.commarket.commarket.dto.response.PostListResponse;
import com.commarket.commarket.dto.request.PostUpdateRequest;
import com.commarket.commarket.service.PostService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/posts")
public class PostController {

    private final PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    // TC-Post-01 등록
    @PostMapping
    public PostCreateResponse create(@RequestBody PostCreateRequest request) {
        return postService.create(request);
    }

    // TC-Post-09 목록 조회
    @GetMapping
    public List<PostListResponse> getPosts() {
        return postService.getPosts();
    }

    // TC-Post-07 수정
    @PatchMapping("/{id}")
    public String update(@PathVariable Long id, @RequestBody PostUpdateRequest request) {
        postService.update(id, request);
        return "수정 성공";
    }

    // TC-Post-08 삭제
    @DeleteMapping("/{id}")
    public String delete(@PathVariable Long id) {
        postService.delete(id);
        return "삭제 성공";
    }

    @GetMapping("/{id}")
    public PostListResponse getPost(@PathVariable Long id) {
        return postService.getPost(id);
    }

}
