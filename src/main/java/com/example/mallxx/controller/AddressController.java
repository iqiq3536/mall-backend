package com.example.mallxx.controller;

import com.example.mallxx.entity.Address;
import com.example.mallxx.mapper.AddressMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/addresses")
public class AddressController {

    @Autowired
    private AddressMapper addressMapper;

    // 添加地址信息
    @PostMapping("/add")
    public boolean addAddress(@CookieValue(value = "user_id", required = false)String User_id,@RequestBody Address address) {
        //addressMapper.addAddress(address);
        System.out.println(User_id + "--------------------");
        System.out.println(address+"--------------------");
        if (User_id != null && !User_id.isEmpty()) {
            address.setUser_id(Integer.parseInt(User_id));
            System.out.println(User_id+"---*******-----------");
        }
        return addressMapper.addAddress(address);
    }

    /*// 删除地址信息
    @DeleteMapping("/delete/{address_id}")
    public boolean deleteAddress(@PathVariable("address_id") Integer address_id) {
        return addressMapper.deleteAddressByUserId(address_id);
    }*/

    /**
     * 通过RequestBody来获取id来删除地址信息
     * @param address
     * @return
     */
    @PostMapping("/delete")
    public boolean deleteAddress(@RequestBody Address address) {
        return addressMapper.deleteAddressByAddressId(address.getAddress_id());
    }

    // 更新地址信息
    @PostMapping("/update")
    public boolean updateAddress(@CookieValue(value = "user_id", required = false)String User_id,@RequestBody Address address) {
        //System.out.println(User_id + "--------------------");
        //System.out.println(address.getUser_id()+"-++++++++++++++-");

        /*if (User_id != null && !User_id.isEmpty()) {
            address.setUser_id(Integer.parseInt(User_id));
            System.out.println(User_id+"---*******-----------");
        }*/
        System.out.println(address);
        return addressMapper.updateAddress(address);
    }


    @RequestMapping("/find") // 使用POST方法
    public ResponseEntity<List<Address>> find(@CookieValue(value = "user_id", required = false)String User_id ) {
        //System.out.println(User_id +"--------------------");
        List<Address> address = addressMapper.findAddressesByUserId(Integer.parseInt(User_id));
        //System.out.println(address);
        //System.out.println(User_id +"--------------------");

        return ResponseEntity.ok(address);
    }
}
