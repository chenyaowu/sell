package com.chen.sell.repository;

import com.chen.sell.dataobject.SellerInfo;
import com.chen.sell.utils.KeyUtil;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SellerInfoRepositoryTest {
    @Autowired
    private SellerInfoRepository sellerInfoRepository;

    @Test
    public void findByOpenid() throws Exception {
        SellerInfo sellerInfo =sellerInfoRepository.findByOpenid("abc");
        Assert.assertNotNull(sellerInfo);
    }

    @Test
    public void save(){
        SellerInfo sellerInfo = new SellerInfo() ;
        sellerInfo.setSellerId(KeyUtil.genUniqueKey());
        sellerInfo.setOpenid("abc");
        sellerInfo.setUsername("admin");
        sellerInfo.setPassword("admin");
        SellerInfo result = sellerInfoRepository.save(sellerInfo);
        Assert.assertNotNull(result);
    }

}