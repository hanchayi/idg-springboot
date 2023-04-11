package com.idg.demo.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.idg.demo.domain.DemoStudent;

public interface IDemoStudentService extends IService<DemoStudent>{
    public Page<DemoStudent> pageList(String name);
}
