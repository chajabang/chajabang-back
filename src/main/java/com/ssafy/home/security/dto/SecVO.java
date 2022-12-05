package com.ssafy.home.security.dto;


public class SecVO {
    private String userId;
    private String salt;
    private String secKey;

    public SecVO() {
    }

    public SecVO(String userId, String salt, String secKey) {
        super();
        setUserId(userId);
        setSalt(salt);
        setSecKey(secKey);
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        if (userId != null) {
            this.userId = userId;
        } else {
            System.out.println("유효하지 않은 아이디입니다.");
        }
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        if (salt != null) {
            this.salt = salt;
        } else {
            System.out.println("유효하지 않은 값입니다.");
        }

    }

    public String getSecKey() {
        return secKey;
    }

    public void setSecKey(String secKey) {
        if (secKey != null) {
            this.secKey = secKey;
        } else {
            System.out.println("유효하지 않은 값입니다.");
        }
    }

}


