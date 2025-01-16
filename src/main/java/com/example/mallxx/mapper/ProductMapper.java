package com.example.mallxx.mapper;

import com.example.mallxx.entity.Product;
import com.example.mallxx.entity.ProductDetail;
import com.example.mallxx.entity.Seller;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface ProductMapper {

    @Select("select * from products")
    List<Product> findAll();


    @Select("SELECT p.* FROM products p INNER JOIN product_merchant_association pma ON p.id = pma.product_id WHERE pma.merchant_id = #{merchantId}")
    List<Product> findProductsByMerchantId(@Param("merchantId") int merchantId);


    @Update("update products set name = #{name}, category = #{category}, img_url = #{img_url}, " +
            "description = #{description}, price = #{price}, stock = #{stock} where id = #{id}")
    void update(@Param("id") int id,
                @Param("name") String name,
                @Param("category") String category,
                @Param("img_url") String img_url,
                @Param("description") String description,
                @Param("price") double price,
                @Param("stock") int stock);

    @Select("select * from products where id = #{id}")
    Product findById(int id);

    /**
     * 通过商品id列表查找商品
     * @param id
     */
    @Select("<script>" +
            "select * from products where id in " +
            "<foreach collection='list' item='id' open='(' separator=',' close=')'>" +
            "#{id}" +
            "</foreach>" +
            "</script>")
    List<Product> findByIdList(List<Integer> id);


    @Delete("delete from products where id = #{id}")
    void deleteById(int id);

    @Insert("insert into products(name, category, img_url, description, price, stock) " +
            "values(#{name}, #{category}, #{img_url}, #{description}, #{price}, #{stock})")
    void add(@Param("name") String name,
             @Param("category") String category,
             @Param("img_url") String img_url,
             @Param("description") String description,
             @Param("price") double price,
             @Param("stock") int stock);

    @Insert("insert into products(name, category, img_url, description, price, stock) " +
            "values(#{name}, #{category}, #{img_url}, #{description}, #{price}, #{stock})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    void add2(Product product);

    //通过商品名称或描述模糊搜索
    @Select("SELECT * FROM products WHERE name LIKE CONCAT('%', #{keyword}, '%') OR description LIKE CONCAT('%', #{keyword}, '%')")
    List<Product> searchByKeyword(@Param("keyword") String keyword);

    @Select("SELECT id, product_id, " +
            "CONCAT('http://localhost:8082/api/product_images/file/', REPLACE(SUBSTRING_INDEX(url, '\\\\', -1), '\\\\', '')) AS url, " +
            "`order` " +
            "FROM product_details " +
            "WHERE product_id = #{productId} " +
            "ORDER BY `order` ASC")
    List<ProductDetail> getProductDetailsByProductId(Long productId);

    /**
     * 通过id获取商品name和img_url
     */
    @Select("SELECT id, name, img_url FROM products WHERE id = #{id}")
    Product getProductNameAndImgUrlById( int id);












}
