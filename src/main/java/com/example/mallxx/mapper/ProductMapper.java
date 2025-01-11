package com.example.mallxx.mapper;

import com.example.mallxx.entity.Product;
import com.example.mallxx.entity.Seller;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface ProductMapper {

    @Select("select * from product")
    List<Product> findAll();

    @Update("update products set name = #{name}, category = #{category}, img_url = #{img_url}, " +
            "description = #{description}, price = #{price}, stock = #{stock} where id = #{id}")
    void update(@Param("id") int id,
                @Param("name") String name,
                @Param("category") String category,
                @Param("img_url") String img_url,
                @Param("description") String description,
                @Param("price") double price,
                @Param("stock") int stock);

    @Select("select * from products where id = #{id}")
    Product findById(int id);

    @Delete("delete from products where id = #{id}")
    void deleteById(int id);

    @Insert("insert into products(name, category, img_url, description, price, stock) " +
            "values(#{name}, #{category}, #{img_url}, #{description}, #{price}, #{stock})")
    void add(@Param("name") String name,
             @Param("category") String category,
             @Param("img_url") String img_url,
             @Param("description") String description,
             @Param("price") double price,
             @Param("stock") int stock);

















}
