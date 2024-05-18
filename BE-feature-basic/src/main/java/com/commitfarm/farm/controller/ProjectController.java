package com.commitfarm.farm.controller;

import com.commitfarm.farm.dto.project.CreateProjectReq;
import com.commitfarm.farm.dto.project.ProjectListRes;
import com.commitfarm.farm.dto.ticket.CreateTicketReq;
import com.commitfarm.farm.dto.ticket.TicketListRes;
import com.commitfarm.farm.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/projects")
public class ProjectController {

    @Autowired
    private ProjectService projectService;

    //프로젝트 생성 api
    @PostMapping("/create")
    public ResponseEntity<String> createProject(@RequestBody CreateProjectReq createProjectReq) {
        try {
            projectService.createProject(createProjectReq);
            return ResponseEntity.ok("프로젝트가 생성되었습니다.");
        } catch (Exception e) {
            return ResponseEntity.status(400).body(e.getMessage());
        }
    }

    //유저가 했던 모든 프로젝트 조회 API
    @GetMapping("/{userId}")
    public ResponseEntity<ProjectListRes> getUserProjects(@PathVariable Long userId) {
        try {
            ProjectListRes projectListRes = projectService.getUserProjects(userId);
            return ResponseEntity.ok(projectListRes);
        } catch (Exception e) {
            return ResponseEntity.status(400).body(null);
        }
    }


    // 프로젝트 삭제 API
    @DeleteMapping("/{projectId}")
    public ResponseEntity<String> deleteProject(@PathVariable Long projectId) {
        try {
            projectService.deleteProject(projectId);
            return ResponseEntity.ok("프로젝트가 삭제되었습니다.");
        } catch (Exception e) {
            return ResponseEntity.status(400).body(e.getMessage());
        }
    }


}