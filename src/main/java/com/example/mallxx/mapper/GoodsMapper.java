package com.example.mallxx.mapper;

import com.example.mallxx.entity.Goods;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Insert;

import java.util.List;

public interface GoodsMapper {

    @Select("select * from goods")
    List<Goods> findAll();

    @Insert("insert into goods(name, img_url) values(#{name}, #{img_url})")
    Void add(@Param("name") String name, @Param("img_url") String img_url);
}

