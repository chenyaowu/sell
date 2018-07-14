package com.chen.sell.service;

import com.chen.sell.dataobject.SellerInfo;

/**
 * 卖家端service
 */
public interface SellerService {
    //通过openid查询信息
    SellerInfo findSellerInfoByOpenid(String openid);

}
