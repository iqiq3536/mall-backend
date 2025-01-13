package com.example.mallxx.mapper;

import com.example.mallxx.entity.ProductTag;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface ProductTagMapper {

    // 插入新的产品标签
    @Insert("INSERT INTO product_tags(id, name, category) VALUES(#{id}, #{name}, #{category.name})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    void insert(ProductTag tag);

    // 根据ID删除产品标签
    @Delete("DELETE FROM product_tags WHERE id = #{id}")
    void deleteById(int id);

    // 更新产品标签信息
    @Update("UPDATE product_tags SET name = #{name}, category = #{category.name} WHERE id = #{id}")
    void update(ProductTag tag);

    // 根据ID查询单个产品标签
    @Select("SELECT * FROM product_tags WHERE id = #{id}")
    ProductTag selectById(int id);

    // 根据名称查询单个产品标签
    @Select("SELECT * FROM product_tags WHERE name = #{name}")
    ProductTag selectByName(int name);

    // 查询所有产品标签
    @Select("SELECT * FROM product_tags")
    List<ProductTag> selectAll();
}