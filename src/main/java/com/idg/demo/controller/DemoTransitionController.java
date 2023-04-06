package com.idg.demo.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.idg.demo.service.IDemoTransitionService;

@RestController
public class DemoTransitionController {

	@Autowired
	private IDemoTransitionService demoTransitionService;

	@GetMapping("/demo-transition")
	public boolean demoTransition() {
    	demoTransitionService.test();
		return true;
	}

	@PostMapping("upload")
    public String uploadFile(MultipartFile file){
      //todo upload
      String previewPath = "";
      return previewPath;
    }
}