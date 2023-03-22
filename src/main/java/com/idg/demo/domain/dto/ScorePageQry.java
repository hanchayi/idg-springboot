package com.idg.demo.domain.dto;

import lombok.Data;

@Data
public class ScorePageQry {
    private Long current;

    private Long size;

    private String searchKey;
}
