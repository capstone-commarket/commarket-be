package com.commarket.commarket.controller;

import com.commarket.commarket.domain.Notice;
import com.commarket.commarket.dto.request.AddNoticeRequest;
import com.commarket.commarket.dto.response.NoticeResponse;
import com.commarket.commarket.service.NoticeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@RestController
public class NoticeController {
    private final NoticeService noticeService;

    @PostMapping("/api/notices")
    public ResponseEntity<Notice> addNotice(@RequestBody AddNoticeRequest request) {
        Notice savedNotice = noticeService.saveNotice(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedNotice);
    }

    @GetMapping("/api/notices")
    public ResponseEntity<List<NoticeResponse>> getAllNotices() {
        List<NoticeResponse> notices = noticeService.getAllNotices()
                .stream()
                .map(NoticeResponse::new)
                .toList();

        return ResponseEntity.ok().body(notices);
    }

    @GetMapping("/api/notices/{id}")
    public ResponseEntity<Notice> getNoticeById(@PathVariable String id) {
        Notice notice = noticeService.getNotice(id);
        
        return ResponseEntity.ok().body(notice);
    }


    @DeleteMapping("/api/notices/{id}")
    public ResponseEntity<Void> deleteNotice(@PathVariable String id) {
        noticeService.deleteNotice(id);

        return ResponseEntity.ok().build();
    }
}