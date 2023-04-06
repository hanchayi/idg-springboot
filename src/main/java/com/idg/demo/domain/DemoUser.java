package com.idg.demo.domain;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;

import lombok.Data;

@Data
public class DemoUser {
    @TableId(type = IdType.AUTO)
    private Integer id;
    @NotEmpty(message = "用户名不能为空！")
	private String username;

    @NotEmpty(message = "密码不能为空！")
	@Size(min = 8, message = "密码长度不能小于8！")
	private String password;
}
