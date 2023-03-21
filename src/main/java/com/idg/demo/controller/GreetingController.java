package com.idg.demo.controller;

import java.util.concurrent.atomic.AtomicLong;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.idg.demo.domain.Greeting;
import com.idg.demo.domain.User;
import com.idg.demo.mapper.UserMapper;
import com.idg.demo.service.IUserService;

@RestController
public class GreetingController {

	private static final String template = "Hello, %s!";
	private final AtomicLong counter = new AtomicLong();
	
	@Autowired
	private IUserService userService;

	@GetMapping("/greeting")
	public Greeting greeting(@RequestParam(value = "name", defaultValue = "World") String name) {
		userService.save(new User("sf"));
		return new Greeting(counter.incrementAndGet(), String.format(template, name));
	}

	@PostMapping("/order")
	public Boolean ofder(String orderNo){
		//valid
		return this.userService.saveOrder(orderNo);
	}
}