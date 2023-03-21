package com.idg.demo.service.impl;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.idg.demo.domain.DemoStudent;
import com.idg.demo.mapper.DemoStudentMapper;
import com.idg.demo.service.IUserDemoStudent;

@Service
@Primary
public class DemoStudentServiceImpl extends ServiceImpl<DemoStudentMapper,DemoStudent> implements IUserDemoStudent  {
}
