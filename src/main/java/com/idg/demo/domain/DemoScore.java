package com.idg.demo.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;

import lombok.Data;

@Data
public class DemoScore {
    @TableId(type = IdType.AUTO)
    private Integer id;
    private Integer demo_student_id;
    private Integer score;

    public DemoScore(Integer score, Integer demo_student_id) {
        this.score = score;
        this.demo_student_id = demo_student_id;
    }
}
