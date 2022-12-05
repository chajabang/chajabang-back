package com.ssafy.home.notice.mapper;

import com.ssafy.home.notice.dto.Notice;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface NoticeMapper {
    List<Notice> selectAll();
	long  write(Notice notice);

    Notice getView(int articleNo);

    int updateHit(int articleNo);


    int delete(int articleNo);

    int update(Notice notice);


}
