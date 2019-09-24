package com.c1801.spring.dzy.service;

import com.c1801.spring.dzy.common.PageInfo;
import com.c1801.spring.dzy.mapper.BookMapper;
import com.c1801.spring.dzy.mapper.BookSkuMapper;
import com.c1801.spring.dzy.mapper.OrderBookRecordMapper;
import com.c1801.spring.dzy.mapper.SkuMapper;
import com.c1801.spring.dzy.model.Book;
import com.c1801.spring.dzy.model.BookInfo;
import com.c1801.spring.dzy.model.BookSku;
import com.c1801.spring.dzy.model.OrderBookRecord;
import com.c1801.spring.dzy.model.Sku;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * user：少
 * dateTime: 2019/8/8 15:03
 */
@Service
@Transactional
public class BookService {

    @Autowired
    private BookMapper bookMapper;

    @Autowired
    private BookSkuMapper bookSkuMapper;

    @Autowired
    private SkuMapper skuMapper;

    @Autowired
    private OrderBookRecordMapper orderBookRecordMapper;



    /**
     * 查询单本书籍
     * @param isbn
     * @return
     */
    public Book getBook(String isbn){
        return bookMapper.queryBookByIBSN(isbn);
    }


    /**
     * 条件查询书籍信息分页
     * @param name
     * @param author
     * @param minStock
     * @param maxStock
     * @param minSale
     * @param maxSale
     * @param minStatus
     * @param maxStatus
     * @param pageSize
     * @param pageNum
     * @return
     */
    public PageInfo<BookInfo> queryBookInfoOfPage(String name, String author, Integer minStock, Integer maxStock, Integer minSale, Integer maxSale, Integer minStatus, Integer maxStatus, Integer pageSize, Integer pageNum,Integer nameStatus,Integer totalStatus){

        //过滤非法条件
        if((minStock != null && maxStock != null && (maxStock < 0 || maxStock > minStock))
                || (minSale != null && maxSale != null && (maxSale < 0 || maxSale > minSale))
                || (minStatus != null && maxStatus != null && (maxStatus < 0 || maxStatus > minStatus))){

            PageInfo pageInfo = PageInfo.ofEmpty(pageSize);
            return pageInfo;
        }

        PageHelper.startPage(pageNum,pageSize);
        Page<BookInfo> bookInfos = bookMapper.queryBookInfoList(name, author, minStock, maxStock, minSale, maxSale, minStatus, maxStatus,nameStatus,totalStatus);
        return PageInfo.ofPageInfo(bookInfos);
    }



    /**
     * 后台管理条件查询所有有单品信息书籍
     * @return
     */
    public List<BookSku> getBookSkuList(String name, Integer pageSize, Integer pageNum){

        return bookSkuMapper.queryBookSkuList(name);
    }


    /**
     * 首页查询在售书籍信息
     * @param name
     * @param pageSize
     * @param pageNum
     * @return
     */
    public PageInfo queryIsSellBookInfoList(String name,Double rate, Integer pageSize, Integer pageNum){
        PageHelper.startPage(pageNum, pageSize, "douban_rate desc, sale desc");
        Page<BookInfo> bookInfos = bookSkuMapper.queryIsSellBookInfoList(name, rate);
        PageInfo pageInfo = PageInfo.ofPageInfo(bookInfos);
        return pageInfo;
    }


    /**
     *后台管理根据isbn 查询单本书籍 所有单品信息
     */
    public BookSku getBookSku(Integer bookId){
        return bookSkuMapper.getBookSku(bookId);
    }

    public BookInfo getIsSellBookInfo(Integer bookId){
        return bookSkuMapper.getIsSellBookInfo(bookId);
    }

    /**
     * 查询出所有所有isbn属于isbns的书籍信息 订单书籍
     * @param ids
     * @return
     */
    public List<BookSku> getBookSkuList(List<Integer> ids){
        return bookSkuMapper.getBookSkuList(ids);
    }


    /**
     * 根据id修改 书籍sku信息
     * @param sku
     * @return
     */
    public int updateSku(Sku sku){

        int i = skuMapper.updateSku(sku);
        return i;
    }

    /**
     * 根据sku id 删除单品
     * @param id
     * @return
     */
    public int delSku(Integer id){
        return skuMapper.delSku(id);
    }


    /**
     * 书籍id和品相匹配的书籍库存数量加一
     * @param orderBookRecords
     * @return
     */
    public int addStock(List<OrderBookRecord> orderBookRecords){
        int count = 0;
        for (int i =0; i < orderBookRecords.size();i++){

            Sku sku = new Sku();
            sku.setBookId(orderBookRecords.get(i).getBookId());
            sku.setCondition(orderBookRecords.get(i).getCondition());
            //更新
            int result = skuMapper.addStock(sku);
            //更新失败则添加
            if(result == 0){
                count += skuMapper.addSku(sku);
            }else{
                count ++;
            }
        }
        return count;
    }

    /**
     * 通过书籍id查询书籍sku
     * @param bookId
     * @return
     */
    public List<Sku> querySkuByBookId(Integer bookId){
        return bookSkuMapper.querySkuByBookId(bookId);
    }

    /**
     * 添加一条卖书成功记录
     * 记录书籍、品相、订单、卖书用户
     * @return
     */
    public int addOrderBookRecordMapper(List<OrderBookRecord> orderBookRecords) {

        int result = orderBookRecordMapper.addOrderBookRecord(orderBookRecords);
        return result;
    }
}
