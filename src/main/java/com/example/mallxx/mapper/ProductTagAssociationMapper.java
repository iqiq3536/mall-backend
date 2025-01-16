package com.example.mallxx.mapper;

import com.example.mallxx.entity.ProductTagAssociation;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface ProductTagAssociationMapper {

    @Insert("INSERT INTO product_tag_association (product_id, tag_id) VALUES (#{product_id}, #{tag_id})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    void insert(ProductTagAssociation association);

    @Delete("DELETE FROM product_tag_association WHERE product_id = #{productId} AND tag_id = #{tagId}")
    void delete(int productId, int tagId);

    @Update("UPDATE product_tag_association SET tag_id = #{tagId} WHERE product_id = #{productId}")
    void update(ProductTagAssociation association);

    @Select("SELECT * FROM product_tag_association WHERE product_id = #{product_id}")
    List<ProductTagAssociation> selectByProductId(int product_id);

    @Select("SELECT * FROM product_tag_association WHERE tag_id = #{tag_id}")
    List<ProductTagAssociation> selectByTagId(int tag_id);

    @Select("SELECT * FROM product_tag_association")
    List<ProductTagAssociation> selectAll();
}
