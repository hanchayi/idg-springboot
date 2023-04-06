package com.idg.demo.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.idg.demo.domain.DemoStudent;
import com.idg.demo.service.IDemoTransitionService;

@Transactional
@Service
@Primary
public class DemoTransitionServiceImpl implements IDemoTransitionService  {
    @Autowired
    public DemoScoreServiceImpl demoScoreService;
    @Autowired
    public DemoStudentServiceImpl demoStudentService;

    public void test(){
        System.out.println("test");
        this.methodA();
        this.methodB(); 
    }
    public void methodA(){
        this.demoStudentService.save(new DemoStudent("sdfsf"));
        System.out.println("methodA");
    }

    public void methodB(){
        throw new Error();
        // this.demoScoreService.save(new DemoScore(5, 3));
        // System.out.println("methodB");
    }
}
