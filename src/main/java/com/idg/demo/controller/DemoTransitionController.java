package com.idg.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import com.idg.demo.domain.DemoScore;
import com.idg.demo.service.IDemoScoreService;

@RestController
public class DemoTransitionController {

	@Autowired
	private IDemoScoreService demoScore;

	@GetMapping("/demo-transition")
	public List<DemoScore> demoTransition() {
       return demoScore.list();
	}
}