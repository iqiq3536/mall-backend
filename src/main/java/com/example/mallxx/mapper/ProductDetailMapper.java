package com.example.mallxx.mapper;

import com.example.mallxx.entity.ProductDetail;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface ProductDetailMapper {

    @Select("SELECT id, product_id, " +
            "CONCAT('http://localhost:8082/api/product_images/file/', REPLACE(SUBSTRING_INDEX(url, '\\\\', -1), '\\\\', '')) AS url, " +
            "`order` " +
            "FROM product_details " +
            "WHERE product_id = #{productId} " +
            "ORDER BY `order` ASC")
    List<ProductDetail> getProductDetailsByProductId(Long productId);
}



