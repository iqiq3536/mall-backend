package com.example.mallxx.mapper;

import com.example.mallxx.entity.Product;
import com.example.mallxx.entity.Seller;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface ProductMapper {

    @Select("select * from product")
    List<Product> findAll();

    @Update("update product set name = #{name}, img_url = #{img_url} where id = #{id}")
    void update(@Param("id") int id, @Param("name") String name, @Param("img_url") String img_url);

    @Select("select * from product where id = #{id}")
    Product findById(int id);

    @Delete("delete from product where id = #{id}")
    void deleteById(int id);

    @Insert("insert into product(name, img_url) values(#{name}, #{img_url})")
    void add(@Param("name") String name, @Param("img_url") String img_url);


















}
