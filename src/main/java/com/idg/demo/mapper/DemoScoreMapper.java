package com.idg.demo.mapper;

import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.idg.demo.domain.DemoScore;
import com.idg.demo.domain.vo.StudentScoreVO;

public interface DemoScoreMapper extends BaseMapper<DemoScore> {
    
    public Page<StudentScoreVO> selectVOPage(Page<DemoScore> page,@Param("searchKey") String searchKey);
}
