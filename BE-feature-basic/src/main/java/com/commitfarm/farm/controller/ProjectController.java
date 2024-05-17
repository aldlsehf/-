package com.commitfarm.farm.controller;

import com.commitfarm.farm.dto.project.CreateProjectReq;
import com.commitfarm.farm.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/projects")
public class ProjectController {

    @Autowired
    private ProjectService projectService;

    @PostMapping("/create")
    public ResponseEntity<String> createProject(@RequestBody CreateProjectReq createProjectReq) {
        try {
            projectService.createProject(createProjectReq);
            return ResponseEntity.ok("프로젝트가 생성되었습니다.");
        } catch (Exception e) {
            return ResponseEntity.status(400).body(e.getMessage());
        }
    }
}