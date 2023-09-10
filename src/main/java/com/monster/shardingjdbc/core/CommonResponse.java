package com.monster.shardingjdbc.core;

import lombok.Data;

import java.io.Serializable;

@Data
public class CommonResponse implements Serializable {

    private String code;

    private String msg;

    private Object data;

}
