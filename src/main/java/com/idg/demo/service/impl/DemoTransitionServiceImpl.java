package com.idg.demo.service.impl;

import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.idg.demo.domain.DemoStudent;
import com.idg.demo.mapper.DemoStudentMapper;
import com.idg.demo.service.IDemoStudentService;

@Service
public class DemoTransitionServiceImpl extends ServiceImpl<DemoStudentMapper,DemoStudent> implements IDemoStudentService  {
    public void methodA(){
        System.out.println("methodA");
    }

    public void methodB(){
        System.out.println("methodB");
    }
}
