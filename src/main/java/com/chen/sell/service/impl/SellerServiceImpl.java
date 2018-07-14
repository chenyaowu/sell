package com.chen.sell.service.impl;

import com.chen.sell.dataobject.SellerInfo;
import com.chen.sell.repository.SellerInfoRepository;
import com.chen.sell.service.SellerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SellerServiceImpl implements SellerService {
    @Autowired
    private SellerInfoRepository sellerInfoRepository;


    @Override
    public SellerInfo findSellerInfoByOpenid(String openid) {
        return sellerInfoRepository.findByOpenid(openid);
    }
}
