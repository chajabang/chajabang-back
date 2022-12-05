package com.ssafy.home.comment.dto;

public class Comment {

    private int id;
    private String userId;
    private int articleNo;
    private String content;
    private String userName;
    private String registerTime;

    public  Comment(){}


    public Comment(int id, String userId, int articleNo, String content, String userName, String registerTime) {
        setId(id);
        setUserId(userId);
        setArticleNo(articleNo);
        setContent(content);
        setUserName(userName);
        setRegisterTime(registerTime);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        if (id >= 0)
            this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        if (userId != null)
            this.userId = userId;
    }

    public int getArticleNo() {
        return articleNo;
    }

    public void setArticleNo(int articleNo) {
        if (articleNo >= 0)
            this.articleNo = articleNo;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        if (content != null)
            this.content = content;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        if (userName != null)
            this.userName = userName;
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
        return "Comment{" +
                "id=" + id +
                ", userId='" + userId + '\'' +
                ", articleNo='" + articleNo + '\'' +
                ", content='" + content + '\'' +
                ", userName='" + userName + '\'' +
                ", registerTime='" + registerTime + '\'' +
                '}';
    }
}
