package com.idg.demo.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;

import lombok.Data;

@Data
public class DemoStudent {
    @TableId(type = IdType.AUTO)
    private Integer id;
    private String name;

    public DemoStudent(String name) {
        this.name = name;
    }
}
