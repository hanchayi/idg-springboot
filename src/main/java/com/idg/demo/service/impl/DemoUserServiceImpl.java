package com.idg.demo.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;
import org.springframework.util.DigestUtils;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.idg.demo.domain.DemoUser;
import com.idg.demo.mapper.DemoUserMapper;
import com.idg.demo.service.IDemoUserService;

@Service
@Primary
public class DemoUserServiceImpl extends ServiceImpl<DemoUserMapper,DemoUser> implements IDemoUserService  {
    @Autowired
    private PlatformTransactionManager transactionManager;
    @Autowired
    public DemoUserMapper demoUserMapper;

    public boolean register(DemoUser user) {
        QueryWrapper<DemoUser> queryWrapper = new QueryWrapper<>();
        queryWrapper.select("username", "password").eq("username", user.getUsername());
        // DemoUser finded = this.getOne(queryWrapper);
        // this.demoUserMapper.getOne(queryWrapper);
        // this.demoUserMapper.deleteById("1");
        DemoUser finded = this.demoUserMapper.selectOne(queryWrapper);
        // int inserted = this.demoUserMapper.insert(user);
        // int deleted = this.demoUserMapper.deleteById(1);
        // int updated1 = this.demoUserMapper.update(user, queryWrapper);
        // int updated2 = this.demoUserMapper.updateById(user);
        if (finded != null) {
            throw new Error("has registered");
        }

        // md5
        user.setPassword(DigestUtils.md5DigestAsHex(user.getPassword().getBytes()));
        return this.save(user);
    }

    public boolean login(DemoUser user) {
        TransactionStatus transaction = transactionManager.getTransaction(new DefaultTransactionDefinition());
        try{
            //todo 逻辑代码
            transactionManager.commit(transaction);
        }catch(Exception ex){
            ex.printStackTrace();
            transactionManager.rollback(transaction);
        }
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
