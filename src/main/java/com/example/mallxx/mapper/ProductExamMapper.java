package com.example.mallxx.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface ProductExamMapper {

    @Select("select exam from product_exam where id = #{id}")
    int testProduct(@Param("id") int id);

    @Update("update product_exam set exam = 1 where id = #{id}")
    int UpProduct(@Param("id") int id);

    @Update("update product_exam set exam = 0 where id = #{id}")
    int DownProduct(@Param("id") int id);


}
