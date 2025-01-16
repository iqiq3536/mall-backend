package com.example.mallxx.mapper;

import com.example.mallxx.entity.Address;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface AddressMapper {

    @Insert("INSERT INTO addresses (province, city, county, detailed_address, user_id) " +
            "VALUES (#{province}, #{city}, #{county}, #{detailedAddress}, #{userId})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    Boolean addAddress(Address address);

    @Delete("DELETE FROM addresses WHERE user_id = #{user_id}")
    Boolean deleteAddressByUserId(@Param("user_id") Integer userId);

    /*@Update("UPDATE addresses SET province=#{province}, city=#{city}, county=#{county}, detailed_address=#{detailedAddress} " +
            "WHERE address_id=#{address_id}")
    Boolean updateAddress(@Param("address") Address address);*/
    @Update("UPDATE addresses SET province=#{province}, city=#{city}, county=#{county}, detailed_address=#{detailedAddress} " +
            "WHERE address_id=#{address_id}")
    Boolean updateAddress(Address address);

    @Select("SELECT * FROM addresses WHERE user_id=#{user_id}")
    List<Address> findAddressesByUserId(@Param("user_id") int userId);

    /**
     * 根据address_id来删除数据，返回Boolean
     */
    @Delete("DELETE FROM addresses WHERE address_id=#{address_id}")
    Boolean deleteAddressByAddressId(@Param("address_id") Integer addressId);
}