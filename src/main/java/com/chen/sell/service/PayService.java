package com.chen.sell.service;

import com.chen.sell.dto.OrderDTO;
import com.lly835.bestpay.model.PayResponse;
import com.lly835.bestpay.model.RefundResponse;

public interface PayService {

    PayResponse create(OrderDTO orderDTO);

    PayResponse notify(String notifyDate);

    RefundResponse refund(OrderDTO orderDTO);
}
