package com.idg.demo.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.idg.demo.domain.DemoUser;

public interface IDemoUserService extends IService<DemoUser>{
    boolean register(DemoUser user);
    boolean login(DemoUser user);
}
