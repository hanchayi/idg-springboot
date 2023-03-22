package com.idg.demo.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.idg.demo.domain.DemoScore;
import com.idg.demo.domain.dto.ScorePageQry;
import com.idg.demo.domain.vo.StudentScoreVO;

public interface IDemoScoreService extends IService<DemoScore>{
    public Page<StudentScoreVO> voPagination(ScorePageQry pageQry);
}
