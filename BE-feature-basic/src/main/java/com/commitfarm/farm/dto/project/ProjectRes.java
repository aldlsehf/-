package com.commitfarm.farm.dto.project;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Date;

//projectList의 프로젝트들 in AllProjectRes
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ProjectRes {

    private String name;
    private Date startDate;
    private Date endDate;

}
