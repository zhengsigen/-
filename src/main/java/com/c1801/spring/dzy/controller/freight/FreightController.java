package com.c1801.spring.dzy.controller.freight;

import com.c1801.spring.dzy.common.ResData;
import com.c1801.spring.dzy.mapper.FreightMapper;
import com.c1801.spring.dzy.model.Freight;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.xml.ws.Action;

@RestController
@Transactional
@RequestMapping("/dzy/freight")
public class FreightController {
    @Autowired
    private FreightMapper freightMapper;

    @GetMapping()
    public ResData queryUserFreight(){
        Freight freight = freightMapper.queryUser(23);
        System.out.println(freight);
        return ResData.ofSuccess(0,"查询成功",freight);
    }


    @GetMapping("/{province}")
    public ResData queryUserFreight(@PathVariable String province){
        Freight freight = freightMapper.queryByProvince(province);
        System.out.println(freight);
        return ResData.ofSuccess(0,"查询成功",freight);
    }


}
