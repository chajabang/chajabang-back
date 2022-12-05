package com.ssafy.home.member.mapper;

import com.ssafy.home.member.dto.Member;
import org.apache.ibatis.annotations.Mapper;

import java.sql.SQLException;
import java.util.Map;

@Mapper
public interface MemberMapper {

    Member login(Member m) throws SQLException;
    int register(Member m)throws SQLException;
    int idCheck(String id)throws SQLException;
    int usernameCheck(String username)throws SQLException;
    int update(Map<String,String> map)throws SQLException;

    int delete(String id)throws SQLException;

}
