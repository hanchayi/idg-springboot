package com.idg.demo.service.impl;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.idg.demo.domain.DemoScore;
import com.idg.demo.mapper.DemoScoreMapper;
import com.idg.demo.service.IDemoScore;

@Service
@Primary
public class DemoScoreServiceImpl extends ServiceImpl<DemoScoreMapper,DemoScore> implements IDemoScore  {
}
