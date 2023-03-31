package com.idg.demo.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.idg.demo.domain.DemoUser;

public interface IDemoUserService extends IService<DemoUser>{
    boolean register(DemoUser user);
    DemoUser check(String authorization);
    String login(DemoUser user);
}
