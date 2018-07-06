package com.chen.sell.utils;

import com.chen.sell.VO.ResultVO;

public class ResultVOUtil {

    public static ResultVO success(Object object){
        ResultVO resultVO = new ResultVO();
        resultVO.setData(object);
        resultVO.setCode(0);
        resultVO.setMsg("成功");
        return resultVO;
    }

    public static ResultVO success(){
        return success(null);
    }

    private static ResultVO error(Integer code,String msg){
        ResultVO resultVO = new ResultVO() ;
        resultVO.setCode(code);
        resultVO.setMsg(msg);
        return resultVO;
    }
}
