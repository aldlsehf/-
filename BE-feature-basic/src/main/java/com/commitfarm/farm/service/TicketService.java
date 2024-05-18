package com.commitfarm.farm.service;

import com.commitfarm.farm.domain.Member;
import com.commitfarm.farm.domain.Project;
import com.commitfarm.farm.domain.Ticket;
import com.commitfarm.farm.domain.Users;
import com.commitfarm.farm.domain.enumClass.UserType;
import com.commitfarm.farm.dto.ticket.CreateTicketReq;
import com.commitfarm.farm.dto.ticket.TicketListRes;
import com.commitfarm.farm.dto.ticket.TicketRes;
import com.commitfarm.farm.dto.ticket.UpdateTicketReq;
import com.commitfarm.farm.repository.MemberRepository;
import com.commitfarm.farm.repository.ProjectRepository;
import com.commitfarm.farm.repository.TicketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TicketService {

    @Autowired
    private ProjectRepository projectRepository;

    @Autowired
    private TicketRepository ticketRepository;

    @Autowired
    private MemberRepository memberRepository;

    @Transactional(readOnly = true)
    public TicketListRes getProjectTickets(Long projectId) throws Exception {
        Project project = projectRepository.findById(projectId)
                .orElseThrow(() -> new Exception("프로젝트를 찾을 수 없습니다: " + projectId));

        // 프로젝트에 속한 티켓들을 가져오기
        List<TicketRes> ticketList = project.getTickets().stream()
                .map(ticket -> new TicketRes(
                        ticket.getTitle(),
                        ticket.getCreatedTime(),
                        ticket.getStatus()
                ))
                .collect(Collectors.toList());

        return new TicketListRes(ticketList);
    }

    @Transactional
    public void createTicket(Long projectId, Long memberId, CreateTicketReq createTicketReq) throws Exception {
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new Exception("멤버를 찾을 수 없습니다: " + memberId));

        // 멤버가 Tester 타입인지 확인
        if (member.getUserType() != UserType.Tester) {
            throw new Exception("티켓을 생성할 권한이 없습니다.");
        }

        Project project = projectRepository.findById(projectId)
                .orElseThrow(() -> new Exception("프로젝트를 찾을 수 없습니다: " + projectId));

        Users user = member.getUser();

        Ticket ticket = new Ticket();
        ticket.setTitle(createTicketReq.getTitle());
        ticket.setDescription(createTicketReq.getDescription());
        ticket.setCreatedTime(LocalDateTime.now());
        ticket.setStatus(createTicketReq.getStatus());
        ticket.setPriority(createTicketReq.getPriority());
        ticket.setComponent(createTicketReq.getComponent());
        ticket.setProject(project);
        ticket.setReporter(user);

        ticketRepository.save(ticket);
    }

    @Transactional
    public void deleteTicket(Long ticketId) throws Exception {
        Ticket ticket = ticketRepository.findById(ticketId)
                .orElseThrow(() -> new Exception("티켓을 찾을 수 없습니다: " + ticketId));
        ticketRepository.delete(ticket);
    }

    @Transactional
    public void updateTicket(Long ticketId, UpdateTicketReq updateTicketReq) throws Exception {
        Ticket ticket = ticketRepository.findById(ticketId)
                .orElseThrow(() -> new Exception("티켓을 찾을 수 없습니다: " + ticketId));

        Users user = memberRepository.findById(updateTicketReq.getUserId())
                .orElseThrow(() -> new Exception("유저를 찾을 수 없습니다: " + updateTicketReq.getUserId()))
                .getUser();

        ticket.setTitle(updateTicketReq.getTitle());
        ticket.setDescription(updateTicketReq.getDescription());
        ticket.setStatus(updateTicketReq.getStatus());
        ticket.setPriority(updateTicketReq.getPriority());
        ticket.setComponent(updateTicketReq.getComponent());
        ticket.setReporter(user);

        ticketRepository.save(ticket);
    }
}