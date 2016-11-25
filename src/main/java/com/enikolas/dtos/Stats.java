package com.enikolas.dtos;

import com.enikolas.models.Competition;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class Stats {
    private String course;
    private String courseType;
    private long count;
    private long score;
    private Competition competition;
}
