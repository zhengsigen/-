package com.c1801.spring.dzy.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
@Data
public class Freight {
    private Integer id;
    //省份
    private String  province;
    //运费
    private double  charge;
    //包邮
    private double  pinkage;
    private boolean isShipments;
    @JsonFormat(pattern = "YYYY-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date    createDate;
    @JsonFormat(pattern = "YYYY-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date    updateDate;
}
