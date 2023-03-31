package com.idg.demo.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.oauth2.resource.OAuth2ResourceServerProperties.Jwt;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.idg.demo.domain.DemoUser;
import com.idg.demo.mapper.DemoUserMapper;
import com.idg.demo.service.IDemoUserService;

@Service
@Primary
public class DemoUserServiceImpl extends ServiceImpl<DemoUserMapper,DemoUser> implements IDemoUserService  {
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

    public DemoUser check(String token) {
        DecodedJWT jwt = null;
        try {
            JWTVerifier verifier = JWT.require(Algorithm.HMAC256("SECRET")).build();
            jwt = verifier.verify(token);
            //decodedJWT.getClaim("属性").asString()  获取负载中的属性值
 
        } catch (Exception e) {
            throw new Error("token parse error");
        }

        Map<String, Claim>claims = jwt.getClaims();

        DemoUser demoUser = new DemoUser();
        demoUser.setUsername(claims.get("username").asString());
        demoUser.setPassword(claims.get("password").asString());
        return demoUser;

    }


    public String login(DemoUser user) {
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

        Date expireDate = new Date(System.currentTimeMillis() + 100 * 1000);
        Map<String, Object> map = new HashMap<>();
        map.put("alg", "HS256");
        map.put("typ", "JWT");
        String token = JWT.create()
                .withHeader(map)// 添加头部
                //可以将基本信息放到claims中
                .withClaim("id", user.getId())//userId
                .withClaim("username", user.getUsername())//userName
                .withClaim("password", user.getPassword())//password
                .withExpiresAt(expireDate) //超时设置,设置过期的日期
                .withIssuedAt(new Date()) //签发时间
                .sign(Algorithm.HMAC256("SECRET")); //SECRET加密
        return token;
    }
}
