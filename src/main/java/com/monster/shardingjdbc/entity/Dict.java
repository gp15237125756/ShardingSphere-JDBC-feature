package com.monster.shardingjdbc.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("u_dict")
public class Dict {
    private Long dictId;

    private String key;

    private Long value;

    private String status;
}
