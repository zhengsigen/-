package com.c1801.spring.dzy.controller.couponRule;

import com.c1801.spring.dzy.common.PageInfo;
import com.c1801.spring.dzy.common.ResData;
import com.c1801.spring.dzy.mapper.CouponMapper;
import com.c1801.spring.dzy.mapper.CouponRuleMappler;
import com.c1801.spring.dzy.model.Coupon;
import com.c1801.spring.dzy.model.CouponRule;
import com.c1801.spring.dzy.model.UserCoupon;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/manage/coupon/rule")
public class ManageCouponRuleController {
    @Autowired
    public CouponRuleMappler couponRuleMapper;

    @GetMapping()
    public ResData queryCouponRule(@RequestParam(value = "page", defaultValue = "1") Integer page,
                               @RequestParam(value = "size", defaultValue = "5") Integer size){
        PageHelper.startPage(page,size);
        Page<CouponRule> couponRules = couponRuleMapper.queryCouponRule();
        PageInfo pageInfo = PageInfo.ofPageInfo(couponRules);
        return ResData.ofSuccess(0,"优惠券查询完毕!",pageInfo);
    }

    @PostMapping()
    public ResData addCouponRule(@RequestBody CouponRule rule){
        boolean b = couponRuleMapper.addCouponRule(rule);
        return ResData.ofSuccess(0,"优惠券创建成功！");
    }

    @PutMapping()
    public ResData updateCouponRule(@RequestBody CouponRule rule){
        boolean b = couponRuleMapper.updateCouponRule(rule);
        return ResData.ofSuccess(0,"优惠券修改成功！");
    }

    @DeleteMapping()
    public ResData deleteCouponRule(@RequestParam("id")Integer id){
        boolean b = couponRuleMapper.deleteCouponRule(id);
        return  ResData.ofSuccess(0,"优惠券删除成功！");
    }
}