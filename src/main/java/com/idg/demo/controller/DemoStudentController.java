package com.idg.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.idg.demo.domain.DemoStudent;
import com.idg.demo.service.IDemoStudent;

@RestController
public class DemoStudentController {

	@Autowired
	private IDemoStudent userDemoStudent;

	@GetMapping("/demo-students")
	public List<DemoStudent> getDemoStudents() {
       return userDemoStudent.list();
	}

	@GetMapping("/demo-students/{id}")
	public DemoStudent getDemoStudentById(@PathVariable Integer id) {
       return userDemoStudent.getById(id);
	}

    @PostMapping("/demo-students")
	public boolean postDemoStudents(@RequestParam("name") String name) {
        System.out.println("name:" + name);
       return userDemoStudent.save(new DemoStudent(name));
	}
}