package com.commitfarm.farm.service;

import com.commitfarm.farm.domain.Member;
import com.commitfarm.farm.domain.Project;
import com.commitfarm.farm.domain.Users;
import com.commitfarm.farm.dto.project.CreateProjectReq;
import com.commitfarm.farm.dto.project.ManageUserAccountReq;
import com.commitfarm.farm.repository.MemberRepository;
import com.commitfarm.farm.repository.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
public class ProjectService {

    @Autowired
    private ProjectRepository projectRepository;

    @Autowired
    private UsersService userService;

    @Autowired
    private MemberRepository memberRepository;


    @Transactional
    public void createProject(CreateProjectReq createProjectReq) throws Exception {
        Project project = new Project();
        project.setName(createProjectReq.getName());
        project.setStartDate(createProjectReq.getStartDate());
        project.setEndDate(createProjectReq.getEndDate());
        project.setDescription(createProjectReq.getDescription());

        projectRepository.save(project);

        for (ManageUserAccountReq manageUserAccountReq : createProjectReq.getManageUserAccounts()) {
            Users user = userService.findUserByEmail(manageUserAccountReq.getUserEmail());
            if (user == null) {
                throw new Exception("User not found: " + manageUserAccountReq.getUserEmail());
            }

            Member member = new Member();
            member.setProject(project);
            member.setUser(user);
            memberRepository.save(member);
        }
    }



}
