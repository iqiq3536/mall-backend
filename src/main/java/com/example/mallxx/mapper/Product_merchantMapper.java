package com.example.mallxx.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface Product_merchantMapper {

    @Insert("insert into product_merchant_association(product_id,merchant_id) values(#{product_id},#{merchant_id})")
    public int insertProduct_merchant(int product_id,int merchant_id);
}
