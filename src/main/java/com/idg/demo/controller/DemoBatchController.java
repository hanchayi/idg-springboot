package com.idg.demo.controller;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.util.StopWatch;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.idg.demo.service.IDemoScoreService;
import com.idg.demo.domain.DemoScore;
import com.idg.demo.mapper.DemoScoreMapper;
import com.ramostear.captcha.HappyCaptcha;

@RestController
public class DemoBatchController{
    @Autowired
    private IDemoScoreService demoScoreService;
    @Autowired
    private DemoScoreMapper demoScoreMapper;

    @GetMapping("/batch")
    public boolean happyCaptcha(HttpServletRequest request,HttpServletResponse response){
        List<DemoScore> list = new ArrayList<>();
        int num = 100000;
        for (int i = 0; i < num; i++) {
            list.add(new DemoScore(1, 1));
        }

        StopWatch stopWatch = new StopWatch();
        stopWatch.start("mapper save batch");
       boolean res =  this.demoScoreService.saveBatch(list, num);
    //    this.demoScoreMapper.batchInsert(list);
       stopWatch.stop();
       System.out.println("mapper save batch：" + stopWatch.getTotalTimeMillis());
       return true;
    }
}