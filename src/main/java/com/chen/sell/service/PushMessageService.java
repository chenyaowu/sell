package com.chen.sell.service;

import com.chen.sell.dto.OrderDTO;

/**
 * 消息推送
 */
public interface PushMessageService {

    /**
     * 订单状态变更消息
     * @param orderDTO
     */
    void OrderStatus(OrderDTO orderDTO);
}
