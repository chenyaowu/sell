package com.chen.sell.repository;

import com.chen.sell.dataobject.OrderDetail;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.Assert.*;

@SpringBootTest
@RunWith(SpringRunner.class)
public class OrderDetailRepositoryTest {
    @Autowired
    private OrderDetailRepository orderDetailRepository;

    @Test
    public void findByOrderId() throws Exception {
        List<OrderDetail> result = orderDetailRepository.findByOrderId("123111");
        Assert.assertNotEquals(0,result.size());
    }

    @Test
    public void saveTest(){
        OrderDetail orderDetail = new OrderDetail();
        orderDetail.setDetailId("1234");
        orderDetail.setOrderId("123111");
        orderDetail.setProductIcon("http://xxx.jpg");
        orderDetail.setProductId("1");
        orderDetail.setProductName("冰激凌");
        orderDetail.setProductQuantity(123);
        orderDetail.setProductPrice(new BigDecimal(1.2));

        OrderDetail result = orderDetailRepository.save(orderDetail);
        Assert.assertNotNull(result);

    }
}