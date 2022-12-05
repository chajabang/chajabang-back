package com.ssafy.home.notice.dto;


public class Notice {
    private int articleNo;
    private String userId;
    private String userName;
    private String subject;
    private String content;
    private int hit;
    private int likes;
    private String registerTime;


    public Notice() {
    }

    public Notice(int articleNo, String userId, String userName, String subject, String content, int hit, int likes, String registerTime) {
        setArticleNo(articleNo);
        setUserId(userId);
        setUserName(userName);
        setSubject(subject);
        setContent(content);
        setHit(hit);
        setLikes(likes);
        setRegisterTime(registerTime);

    }

    public int getArticleNo() {
        return articleNo;
    }

    public void setArticleNo(int articleNo) {
        if (articleNo >= 0)
            this.articleNo = articleNo;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        if (userId != null)
            this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        if (userName != null)
            this.userName = userName;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        if (subject != null)
            this.subject = subject;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        if (content != null)
            this.content = content;
    }

    public int getHit() {
        return hit;
    }

    public void setHit(int hit) {
        if (hit >= 0)
            this.hit = hit;
    }

    public int getLikes() {
        return likes;
    }

    public void setLikes(int likes) {
        if (likes >= 0)
            this.likes = likes;
    }

    public String getRegisterTime() {
        return registerTime;
    }

    public void setRegisterTime(String registerTime) {
        if (registerTime != null)
            this.registerTime = registerTime;
    }



    @Override
    public String toString() {
        return "Board{" +
                "articleNo=" + articleNo +
                ", userId='" + userId + '\'' +
                ", userName='" + userName + '\'' +
                ", subject='" + subject + '\'' +
                ", content='" + content + '\'' +
                ", hit=" + hit +
                ", likes="+ likes +
                ", registerTime='" + registerTime + '\'' +
                '}';
    }
}