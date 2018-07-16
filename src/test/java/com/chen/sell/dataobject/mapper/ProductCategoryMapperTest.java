package com.chen.sell.dataobject.mapper;

import com.chen.sell.dataobject.ProductCategory;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductCategoryMapperTest {

    @Autowired
    private ProductCategoryMapper mapper;

    @Test
    public void insertByMap() throws Exception {
        Map<String,Object> map = new HashMap<>();
        map.put("category_name","师兄最不爱");
        map.put("category_type",101);
        int result = mapper.insertByMap(map);
       // Assert.assertEquals(1,result);
    }

    @Test
    public void insertByObjectTest(){
        ProductCategory productCategory = new ProductCategory();
        productCategory.setCategoryName("师姐最爱");
        productCategory.setCategoryType(102);
        int result =mapper.insertByObject(productCategory);
        Assert.assertEquals(1,result);
    }

    @Test
    public void findByCategoryTypeTest(){
        ProductCategory result = mapper.findByCategoryType(102);
        Assert.assertNotNull(result);
    }

    @Test
    public void findByCategoryNameTest(){
        List<ProductCategory> list = mapper.findByCategoryName("男生最爱");
        Assert.assertNotEquals(0,list.size());
    }

    @Test
    public void updateByCategoryTypeTest(){
        int result = mapper.updateByCategoryType("师姐最不爱",102);
        Assert.assertNotEquals(0,result);
    }

    @Test
    public void updateByObjectTest(){
        ProductCategory productCategory = new ProductCategory();
        productCategory.setCategoryName("师姐最爱");
        productCategory.setCategoryType(102);
        int result = mapper.updateByObject(productCategory);
        Assert.assertNotEquals(0,result);
    }

    @Test
    public void deleteByCategoryTypeTest(){
        int result = mapper.deleteByCategoryType(102);
        Assert.assertNotEquals(0,result);
    }

    @Test
    public void selectByCategoryTypeTest(){
        ProductCategory result = mapper.selectByCategoryType(101);
        Assert.assertNotNull(result);
    }



}