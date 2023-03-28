package com.idg.demo.domain;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

public class DemoUser {
    @NotEmpty(message = "用户名不能为空！")
	private String username;

    @NotEmpty(message = "密码不能为空！")
	@Size(min = 8, message = "密码长度不能小于8！")
	private String password;
}
