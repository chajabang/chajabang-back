package com.ssafy.home.notice.service;

import com.ssafy.home.notice.dto.Notice;
import com.ssafy.home.notice.mapper.NoticeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NoticeService {

    @Autowired
    NoticeMapper noticeMapper;

    public List<Notice> selectAll() {
        return noticeMapper.selectAll();
    }

    public long write(Notice notice) {
        return noticeMapper.write(notice);
    }

    public Notice getView(int articleNo) {
        return noticeMapper.getView(articleNo);
    }

    public int updateHit(int articleNo) {
        return noticeMapper.updateHit(articleNo);
    }

    public int delete(int articleNo) {
        return noticeMapper.delete(articleNo);
    }

    public int updateNotice(Notice notice) {

        return noticeMapper.update(notice);

    }

}
