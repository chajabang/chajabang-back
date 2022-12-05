package com.ssafy.home.member.service;

import com.ssafy.home.member.dto.Member;
import com.ssafy.home.member.mapper.MemberMapper;
import com.ssafy.home.security.dto.SecVO;
import com.ssafy.home.security.mapper.SecMapper;
import com.ssafy.home.util.OpenCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;
import java.util.Map;
import java.util.UUID;

@Service
public class MemberService {

    @Autowired
    MemberMapper memberMapper;

    @Autowired
    SecMapper secMapper;

    public Member login(Member m) throws SQLException {
        SecVO sec = secMapper.selectSecById(m.getId());
        if (sec != null) {
            String pw = OpenCrypt.byteArrayToHex(OpenCrypt.getSHA256(m.getPw(), sec.getSalt()));
            m.setPw(pw);
        }
        return memberMapper.login(m);
    }

    public int idCheck(String id) throws Exception {
        return memberMapper.idCheck(id);
    }

    public int usernameCheck(String username) throws Exception {
        return memberMapper.usernameCheck(username);
    }

    @Transactional
    public int register(Member m) throws Exception {
        byte[] key = OpenCrypt.generateKey("AES", 128);
        SecVO secVO = new SecVO(m.getId(), UUID.randomUUID().toString(), OpenCrypt.byteArrayToHex(key));
        int cnt = secMapper.insertSec(secVO);
        if (cnt > 0) {
            m.setPw(OpenCrypt.byteArrayToHex(OpenCrypt.getSHA256(m.getPw(), secVO.getSalt())));
            return memberMapper.register(m);
        } else {
            return 0;
        }
    }

    public int update(Map<String, String> map) throws Exception {
        SecVO sec = secMapper.selectSecById(map.get("id"));
        if (map.get("pw") != null) {
            map.put("pw", new String(OpenCrypt.byteArrayToHex(OpenCrypt.getSHA256(map.get("pw"), sec.getSalt()))));
        }
        return memberMapper.update(map);
    }

    @Transactional
    public int delete(String id) throws Exception {
        memberMapper.delete(id);
        return secMapper.delete(id);
    }

}
