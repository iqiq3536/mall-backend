package com.example.mallxx.controller;

import com.example.mallxx.entity.Address;
import com.example.mallxx.entity.User;
import com.example.mallxx.mapper.AddressMapper;
import com.example.mallxx.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/api/addresses")
public class AddressController {

    @Autowired
    private AddressMapper addressMapper;

    // 添加地址信息
    @PostMapping("/add")
    public boolean addAddress(@RequestBody Address address) {
        //addressMapper.addAddress(address);
        return addressMapper.addAddress(address); // 或者根据业务逻辑返回更详细的信息
    }

    // 删除地址信息
    @DeleteMapping("/delete/{address_id}")
    public boolean deleteAddress(@PathVariable("address_id") Integer address_id) {
        return addressMapper.deleteAddressByUserId(address_id);
    }

    // 更新地址信息
    @PutMapping("/update")
    public boolean updateAddress(@RequestBody Address address) {
        return addressMapper.updateAddress(address);
    }

    // 查询用户地址信息
    @GetMapping("/find/{userId}")
    public List<Address> findAddresses(@PathVariable("userId") Integer userId) {
        return addressMapper.findAddressesByUserId(userId);
    }
}
