package com.chen.sell.handler;


import com.chen.sell.config.ProjectConfigUrl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice
public class SellerExceptionHandler {

    @Autowired
    private ProjectConfigUrl projectConfigUrl;

    //拦截登录异常
    @ExceptionHandler
    public ModelAndView handlerAuthorizeException(){
        return new ModelAndView("redirect:"
                .concat(projectConfigUrl.getWechatOpenAuthorize())
                .concat("/sell/wechat/qrAuthorize")
                .concat("?returnUrl=")
                .concat(projectConfigUrl.getSell()
                .concat("/sell/seller/login")));
    }
}
