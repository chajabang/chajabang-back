package com.ssafy.home.member.dto;


public class Member {
    private String id, pw, username, email, reg_date;


    public Member() {
    }

    public Member(String id, String pw) {
        setId(id);
        setPw(pw);
    }
    public Member(String id, String username, String email) {
        setId(id);
        setUsername(username);
        setEmail(email);
    }
    public Member(String id, String pw, String username, String email) {
        setId(id);
        setPw(pw);
        setUsername(username);
        setEmail(email);
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        if (id != null && id != "")
            this.id = id;
    }

    public String getPw() {
        return pw;
    }

    public void setPw(String pw) {
        if (pw != null && pw != "" && pw.length() >= 8 && pw.length() <= 100)
            this.pw = pw;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        if (username != null && username != "")
            this.username = username;
    }


    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        if (email != null && email != "" && email.contains("@"))
            this.email = email;
    }

    public String getReg_date() {
        return reg_date;
    }

    public void setReg_date(String reg_date) {
        // reg_date는 자동으로 현재 시각으로 할당됨
        this.reg_date = reg_date;
    }

    @Override
    public String toString() {
        return "Member{" +
                "id='" + id + '\'' +
                ", pw='" + pw + '\'' +
                ", username='" + username + '\'' +
                ", email='" + email + '\'' +
                ", reg_date='" + reg_date + '\'' +
                '}';
    }
}
