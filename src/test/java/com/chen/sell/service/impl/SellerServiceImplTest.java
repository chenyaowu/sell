package com.chen.sell.service.impl;

import com.chen.sell.dataobject.SellerInfo;
import com.chen.sell.service.SellerService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

@SpringBootTest
@RunWith(SpringRunner.class)
public class SellerServiceImplTest {
    @Autowired
    private SellerService sellerService;

    private static final String OPENID = "abc";

    @Test
    public void findSellerInfoByOpenid() throws Exception {
        SellerInfo result = sellerService.findSellerInfoByOpenid(OPENID);
        Assert.assertNotNull(result);
    }

}