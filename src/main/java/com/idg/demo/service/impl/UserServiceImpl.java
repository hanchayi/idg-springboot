package com.idg.demo.service.impl;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.idg.demo.domain.User;
import com.idg.demo.mapper.UserMapper;
import com.idg.demo.service.IUserService;

@Service
@Primary
public class UserServiceImpl extends ServiceImpl<UserMapper,User> implements IUserService  {

    @Override
    public Boolean saveOrder(String orderNo) {
        // TODO Auto-generated method stub
        return true;
    }

    
}
