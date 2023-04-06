package com.idg.demo.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.idg.demo.domain.DemoUser;
import com.idg.demo.service.IDemoUserService;


@RestController
public class DemoUserController {

	@Autowired
	private IDemoUserService demoUserService;


    @PostMapping("/login")
	public String login(@RequestBody @Valid DemoUser user) {
		//过期时间
        Date expireDate = new Date(System.currentTimeMillis() + 100 * 1000);
        Map<String, Object> map = new HashMap<>();
        map.put("alg", "HS256");
        map.put("typ", "JWT");
        String token = JWT.create()
                .withHeader(map)// 添加头部
                //可以将基本信息放到claims中
                .withClaim("id", user.getId())//userId
                .withClaim("username", user.getUsername())//userName
                .withClaim("password", user.getPassword())//password
                .withExpiresAt(expireDate) //超时设置,设置过期的日期
                .withIssuedAt(new Date()) //签发时间
                .sign(Algorithm.HMAC256("SECRET")); //SECRET加密
        return token;
	}

	@PostMapping("/secure/user")
	public Object user(HttpServletRequest request) {
		return request.getAttribute("username");
	}

	@PostMapping("/register")
	public boolean register(@RequestBody @Valid DemoUser user) {
       return this.demoUserService.register(user);
	}

	
}