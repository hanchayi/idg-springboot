package com.idg.demo.service.impl;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.idg.demo.domain.DemoStudent;
import com.idg.demo.mapper.DemoStudentMapper;
import com.idg.demo.service.IDemoStudentService;

@Service
@Primary
public class DemoStudentServiceImpl extends ServiceImpl<DemoStudentMapper,DemoStudent> implements IDemoStudentService  {
    public Page<DemoStudent> pageList(String name) {
        return this.baseMapper.selectPage(
            new Page<>(1, 1),
            Wrappers.lambdaQuery(DemoStudent.class)
                .like(!name.equals(""), DemoStudent::getName, name)
        );
    }
}
