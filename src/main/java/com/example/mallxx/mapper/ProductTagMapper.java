package com.example.mallxx.mapper;

import com.example.mallxx.entity.ProductTag;
import com.example.mallxx.entity.ProductTagAssociation;
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
    @Insert("INSERT INTO product_tags(tag_name) VALUES(#{tag_name})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    void add(ProductTag productTag);

    @Select("SELECT * FROM product_tags WHERE tag_name = #{tag_name}")
    ProductTag isExist(String name);

    @Select("SELECT pt.* FROM product_tags pt INNER JOIN product_tag_association pta ON pt.id = pta.tagId WHERE pta.productId = #{id}")
    List<ProductTag> selectById2(int id);

    @Delete("DELETE FROM product_tag_association WHERE productId = #{ProductId} AND tagId = #{TagId}")
    void deleteByProductIdANDTagId(int ProductId, int TagId);

    @Insert("INSERT INTO product_tag_association(productId, tagId) VALUES(#{ProductId}, #{TagId})")
    void insertByProductIdANDTagId(int ProductId, int TagId);

    @Select("SELECT * FROM product_tag_association WHERE productId = #{ProductId} AND tagId = #{TagId}")
    ProductTagAssociation isExistByProductIdANDTagId(int ProductId, int TagId);

}