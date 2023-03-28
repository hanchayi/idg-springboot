package com.idg.demo.service.impl;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.idg.demo.domain.DemoUser;
import com.idg.demo.mapper.DemoUserMapper;
import com.idg.demo.service.IDemoUserService;

@Service
@Primary
public class DemoUserServiceImpl extends ServiceImpl<DemoUserMapper,DemoUser> implements IDemoUserService  {
    public boolean register(DemoUser user) {
        QueryWrapper<DemoUser> queryWrapper = new QueryWrapper<>();
        queryWrapper.select("username", "password").eq("username", user.getUsername());
        DemoUser finded = this.getOne(queryWrapper);

        if (finded != null) {
            throw new Error("has registered");
        }

        // md5
        user.setPassword(DigestUtils.md5DigestAsHex(user.getPassword().getBytes()));
        return this.save(user);
    }

    public boolean login(DemoUser user) {
        QueryWrapper<DemoUser> queryWrapper = new QueryWrapper<>();
        queryWrapper.select("username", "password").eq("username", user.getUsername());
        DemoUser finded = this.getOne(queryWrapper);

        if (finded == null) {
            throw new Error("not registered");
        }

        // password 校验
        if (!DigestUtils.md5DigestAsHex(user.getPassword().getBytes()).equals(finded.getPassword())) {
            throw new Error("invalid password");
        }

        // todo return jwt token
        return true;
    }
}
