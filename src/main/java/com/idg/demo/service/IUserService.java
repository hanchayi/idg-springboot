package com.idg.demo.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.idg.demo.domain.User;

public interface IUserService extends IService<User>{
    Boolean saveOrder(String orderNo);
}
