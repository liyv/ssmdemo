package com.liyv.ssm.dao;

import com.liyv.ssm.entity.Book;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface BookDao {

    /**
     * 根据ID 查询单本图书
     *
     * @param id
     * @return
     */
    Book queryById(long id);

    /**
     * 查询所有图书
     *
     * @param offset
     * @param limit
     * @return
     */
    List<Book> queryAll(@Param("offset") int offset, @Param("limit") int limit);

    /**
     * 减少馆藏数量
     *
     * @param bookId
     * @return
     */
    int reduceNumber(long bookId);
}
