package com.idg.demo.domain;

import java.time.LocalDateTime;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;

import lombok.Data;

@Data
public class DemoScore {
    @TableId(type = IdType.AUTO)
    private Integer id;
    @TableField("demo_student_id")
    private Integer demoStudentId;
    private Integer score;

    @TableField(value="created_at",fill = FieldFill.INSERT)
    private LocalDateTime createdAt;

    public DemoScore(Integer score, Integer demo_student_id) {
        this.score = score;
        this.demoStudentId = demo_student_id;
    }
}
