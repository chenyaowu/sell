package com.chen.sell.converter;

import com.chen.sell.dataobject.OrderMaster;
import com.chen.sell.dto.OrderDTO;
import org.springframework.beans.BeanUtils;

import java.util.List;
import java.util.stream.Collectors;

public class OrderMaster2OrderDTOConverter {

    public static OrderDTO conver(OrderMaster orderMaster){
        OrderDTO orderDTO = new OrderDTO() ;
        BeanUtils.copyProperties(orderMaster,orderDTO);
        return orderDTO;
    }

    public static List<OrderDTO> conver(List<OrderMaster> orderMasterList){
        return orderMasterList.stream().map(e->
            conver(e)).collect(Collectors.toList());
    }
}
