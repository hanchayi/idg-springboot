package com.idg.demo.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.idg.demo.domain.DemoUser;
import com.idg.demo.service.IDemoUserService;

@RestController
public class DemoUserController {

	@Autowired
	private IDemoUserService demoUserService;


    @PostMapping("/login")
	public boolean login(@RequestBody @Valid DemoUser user) {
       return this.demoUserService.login(user);
	}

	@PostMapping("/register")
	public boolean register(@RequestBody @Valid DemoUser user) {
       return this.demoUserService.register(user);
	}

	
}