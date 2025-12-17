package com.commarket.commarket.service;

import com.commarket.commarket.domain.Notice;
import com.commarket.commarket.dto.request.AddNoticeRequest;
import com.commarket.commarket.repository.NoticeRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class NoticeService {
    private final NoticeRepository noticeRepository;

    public Notice saveNotice(AddNoticeRequest request) {
        return noticeRepository.save(request.toEntity());
    }

    public List<Notice> getAllNotices() {
        return noticeRepository.findAll();
    }

    public Notice getNotice(String id) {
        return noticeRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Notice not found with id: " + id));
    }

    public void deleteNotice(String id) {
        noticeRepository.deleteById(id);
    }
    
}