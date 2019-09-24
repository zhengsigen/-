package com.c1801.spring.dzy.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;

/**
 * user：黄
 * dateTime: 2019/8/26 11:16
 * 书单
 */
@Data
public class BookCollection{
    private Integer id;
    @NotNull(message = "书单名不能为null")
    @NotEmpty(message = "书单名不能未空")
    private String  name ;
    //简介
    private String  intro;
    private String  backgroundImage;
    private String  backgroundColor;

    private List<BookCollectionList> list;
    //排序的优先级，数字最大的五个会在首页显示
    private Integer sort;
    //状态:0:下架  1：上架
    private Boolean state;
    //截止日期
    @JsonFormat(pattern = "YYYY-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date    expirationDate;

    @JsonFormat(pattern = "YYYY-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date    createDate;

    @JsonFormat(pattern = "YYYY-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date    updateDate;

}
