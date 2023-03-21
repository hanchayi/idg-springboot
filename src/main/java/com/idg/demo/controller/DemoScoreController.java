package com.idg.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.idg.demo.domain.DemoScore;
import com.idg.demo.service.IDemoScore;

@RestController
public class DemoScoreController {

	@Autowired
	private IDemoScore demoScore;

	@GetMapping("/demo-scores")
	public List<DemoScore> getDemoStudents() {
       return demoScore.list();
	}

    @PostMapping("/demo-scores")
	public boolean postDemoStudents(@RequestParam("score") Integer score, @RequestParam("demo_student_id") Integer demo_student_id) {
        System.out.println("score:" + score);
       return demoScore.save(new DemoScore(score, demo_student_id));
	}
}