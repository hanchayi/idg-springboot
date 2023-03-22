package com.idg.demo.service.impl;

import java.util.Objects;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.idg.demo.domain.DemoScore;
import com.idg.demo.domain.dto.ScorePageQry;
import com.idg.demo.domain.vo.StudentScoreVO;
import com.idg.demo.mapper.DemoScoreMapper;
import com.idg.demo.service.IDemoScore;

@Service
@Primary
public class DemoScoreServiceImpl extends ServiceImpl<DemoScoreMapper,DemoScore> implements IDemoScore  {

    public Page<DemoScore> paginationScore(ScorePageQry pageQry){
        return this.baseMapper.selectPage(new Page<>(pageQry.getCurrent(),pageQry.getSize()), 
            Wrappers.lambdaQuery(DemoScore.class)
                .like(Objects.nonNull(pageQry.getSearchKey()),DemoScore::getScore, pageQry.getSearchKey())
        );
    }

    @Override
    public Page<StudentScoreVO> voPagination(ScorePageQry pageQry){
        return this.baseMapper.selectVOPage(new Page<>(pageQry.getCurrent(),pageQry.getSize()),pageQry.getSearchKey());
    }
}
