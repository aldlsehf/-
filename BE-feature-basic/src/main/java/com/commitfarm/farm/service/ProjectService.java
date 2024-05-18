package com.commitfarm.farm.service;

import com.commitfarm.farm.domain.Member;
import com.commitfarm.farm.domain.Project;
import com.commitfarm.farm.domain.Ticket;
import com.commitfarm.farm.domain.Users;
import com.commitfarm.farm.dto.project.CreateProjectReq;
import com.commitfarm.farm.dto.project.ManageUserAccountReq;
import com.commitfarm.farm.dto.project.ProjectListRes;
import com.commitfarm.farm.dto.project.ProjectRes;
import com.commitfarm.farm.dto.ticket.TicketListRes;
import com.commitfarm.farm.dto.ticket.TicketRes;
import com.commitfarm.farm.repository.MemberRepository;
import com.commitfarm.farm.repository.ProjectRepository;
import com.commitfarm.farm.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


@Service
public class ProjectService {

    @Autowired
    private ProjectRepository projectRepository;

    @Autowired
    private UsersService usersService;

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private UserRepository usersRepository;


    @Transactional
    public void createProject(CreateProjectReq createProjectReq) throws Exception {
        Project project = new Project();
        project.setName(createProjectReq.getName());
        project.setStartDate(createProjectReq.getStartDate());
        project.setEndDate(createProjectReq.getEndDate());
        project.setDescription(createProjectReq.getDescription());

        projectRepository.save(project);

        for (ManageUserAccountReq manageUserAccountReq : createProjectReq.getManageUserAccounts()) {
            Users user = usersService.findUserByEmail(manageUserAccountReq.getUserEmail());
            if (user == null) {
                throw new Exception("User not found: " + manageUserAccountReq.getUserEmail());
            }

            Member member = new Member();
            member.setProject(project);
            member.setUser(user);
            member.setUserType(manageUserAccountReq.getUserType());
            memberRepository.save(member);
        }
    }

    @Transactional(readOnly = true)
    public ProjectListRes getUserProjects(Long userId) throws Exception {
        Users user = usersRepository.findById(userId)
                .orElseThrow(() -> new Exception("유저를 찾을 수 없습니다: " + userId));

        List<ProjectRes> projectList = user.getMembers().stream()
                .map(member -> new ProjectRes(
                        member.getProject().getName(),
                        member.getProject().getStartDate(),
                        member.getProject().getEndDate()
                ))
                .collect(Collectors.toList());

        return new ProjectListRes(projectList);
    }

    @Transactional
    public void deleteProject(Long projectId) throws Exception {
        Project project = projectRepository.findById(projectId)
                .orElseThrow(() -> new Exception("프로젝트를 찾을 수 없습니다: " + projectId));

        projectRepository.delete(project);
    }

}
