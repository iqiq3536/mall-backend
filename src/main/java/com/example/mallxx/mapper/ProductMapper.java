package com.example.mallxx.mapper;

import com.example.mallxx.entity.Product;
import com.example.mallxx.entity.Seller;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface ProductMapper {

    @Select("select * from products")
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

    /**
     * 通过商品id列表查找商品
     * @param id
     */
    @Select("<script>" +
            "select * from products where id in " +
            "<foreach collection='list' item='id' open='(' separator=',' close=')'>" +
            "#{id}" +
            "</foreach>" +
            "</script>")
    List<Product> findByIdList(List<Integer> id);


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
    //通过商品名称或描述模糊搜索
    @Select("SELECT * FROM products WHERE name LIKE CONCAT('%', #{keyword}, '%') OR description LIKE CONCAT('%', #{keyword}, '%')")
    List<Product> searchByKeyword(@Param("keyword") String keyword);
















}
