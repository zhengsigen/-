package com.c1801.spring.dzy.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.List;

@Data
public class BookComment {
    //一级评论id
    private Integer id;
    //一级评论内容
    private String comment;
    //用户id
    private Integer userId;
    //用户名
    private String  name;
    //用户微信名
    private String  wxName;
    //用户头像
    private String  cover;
    //是否点赞过这条评论
    private boolean isApplaud;
    //用户是否回复过这条评论
    private boolean  isReplied;
    //一级评论的点赞数量
    private Integer applaudNum;
    //回复一级评论的数量
    private Integer commentNum;
    @JsonFormat(pattern = "YYYY-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createDate;
    //关联二级评论
    private List<BookMessage> bookMessage;
    //书籍详情
    private BookInfo bookInfo;
    //关联用户信息
    private List<Applaud> userInfo;

}
