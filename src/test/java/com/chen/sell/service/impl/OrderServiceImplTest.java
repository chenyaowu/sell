package com.chen.sell.service.impl;

import com.chen.sell.dataobject.OrderDetail;
import com.chen.sell.dto.CartDTO;
import com.chen.sell.dto.OrderDTO;
import com.chen.sell.enums.OrderStatusEnum;
import com.chen.sell.enums.PayStatusEnum;
import com.chen.sell.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class OrderServiceImplTest {
    @Autowired
    private OrderServiceImpl orderService;

    private final String BUYER_OPENID = "112";

    @Test
    public void create() throws Exception {
        OrderDTO orderDTO = new OrderDTO();
        orderDTO.setBuyerAddress("淡水");
        orderDTO.setBuyerName("Mr chen");
        orderDTO.setBuyerPhone("123123345");
        orderDTO.setBuyerOpenid(BUYER_OPENID);


        //购物车
        List<OrderDetail> cartDTOList = new ArrayList<>();

        OrderDetail orderDetail = new OrderDetail();
        orderDetail.setProductId("123456");
        orderDetail.setProductQuantity(1);
        cartDTOList.add(orderDetail);

        orderDTO.setOrderDetailList(cartDTOList);
        OrderDTO result = orderService.create(orderDTO);
        log.info("[创建订单]result=",result);
        Assert.assertNotNull(result);

    }

    @Test
    public void findOne() throws Exception {
        OrderDTO orderDTO = orderService.findOne("1530975044065223076");
        Assert.assertNotNull(orderDTO);
    }

    @Test
    public void findList() throws Exception {
        PageRequest pageRequest = new PageRequest(0,10);

        Page<OrderDTO> orderDTOS =  orderService.findList("112",pageRequest);

        Assert.assertNotEquals(0,orderDTOS.getTotalElements());
    }

    @Test
    public void cancel() throws Exception {
        OrderDTO orderDTO = orderService.findOne("1530975044065223076");
        OrderDTO result = orderService.cancel(orderDTO);
        Assert.assertEquals(OrderStatusEnum.CANCEL.getCode(),result.getOrderStatus());
    }

    @Test
    public void finish() throws Exception {
        OrderDTO orderDTO = orderService.findOne("1530975044065223076");
        OrderDTO result = orderService.finish(orderDTO);
        Assert.assertEquals(OrderStatusEnum.FINISHED.getCode(),result.getOrderStatus());

    }

    @Test
    public void paid() throws Exception {
        OrderDTO orderDTO = orderService.findOne("1530975044065223076");
        OrderDTO result = orderService.paid(orderDTO);
        Assert.assertEquals(PayStatusEnum.SUCCESS.getCode(),result.getPayStatus());

    }

}