package com.c1801.spring.dzy.mapper;

import com.c1801.spring.dzy.model.Sku;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.omg.PortableInterceptor.INACTIVE;
import org.springframework.web.bind.annotation.PutMapping;

import java.util.List;

/**
 * user：少
 * dateTime: 2019/8/8 20:46
 */
@Mapper
public interface SkuMapper {

    public int addSku(@Param("sku") Sku sku);

    public int updateSku(@Param("sku")Sku sku);

    public int delSku(Integer id);

    /**
     * 库存加一
     * @param sku
     * @return
     */
    public int addStock(@Param("sku") Sku sku);

    public int updateStockAndSale(@Param("skus")List<Sku> skus);

}
