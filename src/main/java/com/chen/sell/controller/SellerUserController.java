package com.chen.sell.controller;

import com.chen.sell.config.ProjectConfigUrl;
import com.chen.sell.constant.CookieConstant;
import com.chen.sell.constant.RedisConstant;
import com.chen.sell.dataobject.SellerInfo;
import com.chen.sell.enums.ResultEnum;
import com.chen.sell.service.SellerService;
import com.chen.sell.utils.CookieUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

@Controller
@RequestMapping("/seller")
public class SellerUserController {
    @Autowired
    private SellerService sellerService;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Autowired
    private ProjectConfigUrl projectConfigUrl;

    @GetMapping("/login")
    public ModelAndView login(@RequestParam("openid") String openid,
                              HttpServletResponse response,
                              Map<String,Object> map){
        //1.openid去和数据库的数据匹配
        SellerInfo sellerInfo = sellerService.findSellerInfoByOpenid(openid);
        if(sellerInfo == null){
            map.put("msg", ResultEnum.LOGIN_FAIL.getMessage());
            map.put("url","/sell/seller/order/list");
            return new ModelAndView("common/error");
        }
        //2.设置token至redis
        String token = UUID.randomUUID().toString();
        Integer expire = RedisConstant.EXPIRE;
        stringRedisTemplate.opsForValue().set(String.format(RedisConstant.TOKEN_PREFIX,token),openid,expire, TimeUnit.SECONDS);

        //3.设置token至cookie
        CookieUtil.set(response, CookieConstant.TOKEN,token,expire);

        return new ModelAndView("redirect:"+projectConfigUrl.getSell()+"/sell/seller/order/list");
    }

    @RequestMapping("/logout")
    public ModelAndView logout(HttpServletRequest request,HttpServletResponse response,
                        Map<String,Object> map){
        //从cookie查询
        Cookie cookie = CookieUtil.get(request,CookieConstant.TOKEN);
        if(cookie != null){
            //清除redis
            stringRedisTemplate.opsForValue().getOperations().delete(String.format(RedisConstant.TOKEN_PREFIX,cookie.getValue()));
            //清除cookie
            CookieUtil.set(response,CookieConstant.TOKEN,null,0);

        }
        map.put("msg",ResultEnum.LOGOUT_SUCCESS.getMessage());
        map.put("url","/sell/seller/order/list");
        return new ModelAndView("common/success",map);
    }

}
