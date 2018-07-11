package com.chen.sell.service.impl;

import com.chen.sell.dto.OrderDTO;
import com.chen.sell.enums.ResultEnum;
import com.chen.sell.exception.SellException;
import com.chen.sell.service.OrderService;
import com.chen.sell.service.PayService;
import com.chen.sell.utils.JsonUtils;
import com.chen.sell.utils.MathUtil;
import com.lly835.bestpay.enums.BestPayTypeEnum;
import com.lly835.bestpay.model.PayRequest;
import com.lly835.bestpay.model.PayResponse;
import com.lly835.bestpay.model.RefundRequest;
import com.lly835.bestpay.model.RefundResponse;
import com.lly835.bestpay.service.impl.BestPayServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
@Slf4j
public class PayServiceImpl implements PayService {

    private static final String ORDER_NAME="微信点餐";

    @Autowired
    private BestPayServiceImpl bestPayService;
    @Autowired
    private OrderService orderService;

    @Override
    public PayResponse create(OrderDTO orderDTO) {
        PayRequest payRequest = new PayRequest();
        payRequest.setOpenid(orderDTO.getBuyerOpenid());
        payRequest.setOrderAmount(orderDTO.getOrderAmount().doubleValue());
        payRequest.setOrderId(orderDTO.getOrderId());
        payRequest.setOrderName(ORDER_NAME);
        payRequest.setPayTypeEnum(BestPayTypeEnum.WXPAY_H5);
        log.info("[微信支付]发起支付，request={}", JsonUtils.toJson(payRequest));
        PayResponse payResponse =  bestPayService.pay(payRequest);
        log.info("[微信支付]发起支付，response={}",JsonUtils.toJson(payResponse));
        return payResponse;
    }

    @Override
    public PayResponse notify(String notifyDate) {
        //1.验证签名

        //2.支付的状态

        //3.支付金额

        //4.支付人（下单人=支付人）

        PayResponse payResponse = bestPayService.asyncNotify(notifyDate);
        log.info("[微信支付]异步通知：payResponse={}",JsonUtils.toJson(payResponse));

       //查询订单
        OrderDTO orderDTO = orderService.findOne(payResponse.getOrderId());

        //判断订单是否存在
        if(orderDTO == null){
            log.error("[微信支付]异步通知,订单不存在,orderId={}",payResponse.getOrderId());
            throw new SellException(ResultEnum.ORDER_NOT_EXIT);
        }

        //判断金额是否一致
        if (!MathUtil.equals(payResponse.getOrderAmount(),orderDTO.getOrderAmount().doubleValue())) {
            log.info("[微信支付]异步通知,支付金额不一致,orderId={},微信通知金额={},系统金额={}",payResponse.getOrderId(),payResponse.getOrderAmount(),orderDTO.getOrderAmount());
            throw new SellException(ResultEnum.WXPAY_NOTIFY_MONEY_NERIFY_ERROR);
        }


        //修改订单状态
        orderService.paid(orderDTO);
        return payResponse;
    }

    /**
     * 退款
     * @param orderDTO
     * @return
     */
    @Override
    public RefundResponse refund(OrderDTO orderDTO) {
        RefundRequest refundRequest = new RefundRequest();
        refundRequest.setOrderId(orderDTO.getOrderId());
        refundRequest.setOrderAmount(orderDTO.getOrderAmount().doubleValue());
        refundRequest.setPayTypeEnum(BestPayTypeEnum.WXPAY_H5);
        log.info("[微信退款] request={}",JsonUtils.toJson(refundRequest));
        RefundResponse refundResponse= bestPayService.refund(refundRequest);
        log.info("[微信退款] response={}",JsonUtils.toJson(refundResponse));
        return refundResponse;

    }
}
