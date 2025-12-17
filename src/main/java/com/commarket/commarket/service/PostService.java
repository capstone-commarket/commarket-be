package com.commarket.commarket.service;

import com.commarket.commarket.domain.Post;
import com.commarket.commarket.domain.PostDetail;
import com.commarket.commarket.dto.request.PostCreateRequest;
import com.commarket.commarket.dto.response.PostCreateResponse;
import com.commarket.commarket.dto.response.PostListResponse;
import com.commarket.commarket.dto.request.PostUpdateRequest;
import com.commarket.commarket.repository.PostRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PostService {

    private final PostRepository postRepository;

    public PostService(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    // TC-Post-01 등록
    public PostCreateResponse create(PostCreateRequest request) {

        if (request.getTitle() == null || request.getTitle().trim().isEmpty()) {
            throw new IllegalArgumentException("제목이 비어있습니다.");
        }
        if (request.getPrice() == null) {
            throw new IllegalArgumentException("가격은 필수 입력값입니다.");
        }
        if (request.getPrice() < 0) {
            throw new IllegalArgumentException("가격은 0원 이상이어야 합니다.");
        }
        if (request.getCategoryId() == null) {
            throw new IllegalArgumentException("카테고리는 필수입니다.");
        }

        // 로그인 연동 전 임시 userId
        Long userId = 1L;

        Post post = new Post(
                request.getCategoryId(),
                userId,
                request.getTitle(),
                request.getPrice()
        );

        if (request.getTradeMethod() != null || request.getPlace() != null || request.getDescription() != null) {
            Integer tradeMethod = (request.getTradeMethod() == null) ? 0 : request.getTradeMethod();
            String place = (request.getPlace() == null) ? "" : request.getPlace();
            String description = request.getDescription();

            PostDetail detail = new PostDetail(tradeMethod, place, description);
            post.setDetail(detail);
        }

        Post saved = postRepository.save(post);
        return new PostCreateResponse(saved.getId(), "게시글 등록 성공");
    }

    // TC-Post-09 목록 조회 (최신순)
    public List<PostListResponse> getPosts() {
        return postRepository.findAllByOrderByCreatedAtDesc()
                .stream()
                .map(p -> new PostListResponse(
                        p.getId(),
                        p.getTitle(),
                        p.getPrice(),
                        p.getCategoryId(),
                        p.getCreatedAt()
                ))
                .collect(Collectors.toList());
    }

    // TC-Post-07 수정
    public void update(Long id, PostUpdateRequest request) {
        Post post = postRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("게시글이 존재하지 않습니다."));

        if (request.getTitle() == null || request.getTitle().trim().isEmpty()) {
            throw new IllegalArgumentException("제목이 비어있습니다.");
        }
        if (request.getPrice() == null) {
            throw new IllegalArgumentException("가격은 필수 입력값입니다.");
        }
        if (request.getPrice() < 0) {
            throw new IllegalArgumentException("가격은 0원 이상이어야 합니다.");
        }
        if (request.getCategoryId() == null) {
            throw new IllegalArgumentException("카테고리는 필수입니다.");
        }

        post.update(request.getTitle(), request.getPrice(), request.getCategoryId());
        postRepository.save(post);
    }

    // TC-Post-08 삭제
    public void delete(Long id) {
        Post post = postRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("게시글이 존재하지 않습니다."));
        postRepository.delete(post);
    }

    public PostListResponse getPost(Long id) {
        Post post = postRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("게시글이 존재하지 않습니다."));

        return new PostListResponse(
                post.getId(),
                post.getTitle(),
                post.getPrice(),
                post.getCategoryId(),
                post.getCreatedAt()
        );
    }
}
