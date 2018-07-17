package com.chen.sell.VO;

import lombok.Data;

import java.io.Serializable;

@Data
public class ResultVO<T> implements Serializable{

    private static final long serialVersionUID = -2924253850768473882L;

    private Integer code;

    private String msg;

    private T data;

}
