package com.wenjun.entity;

public class CSUEntity {
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Integer getCommentNum() {
        return commentNum;
    }

    public void setCommentNum(Integer commentNum) {
        this.commentNum = commentNum;
    }

    // 标题
    private String title;
    // 作者
    private String author;
    // 评论数
    private Integer commentNum;

    @Override
    public String toString() {
        return "CSUEntity{" +
                "title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", commentNum=" + commentNum +
                '}';
    }
}
