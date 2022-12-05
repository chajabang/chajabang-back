package com.ssafy.home.security.mapper;

import com.ssafy.home.security.dto.SecVO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface SecMapper {
    int insertSec(SecVO secVo);

    SecVO selectSecById(String id);
    int delete(String id);

}
