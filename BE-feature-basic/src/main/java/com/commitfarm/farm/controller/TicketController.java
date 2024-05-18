package com.commitfarm.farm.controller;

import com.commitfarm.farm.dto.ticket.CreateTicketReq;
import com.commitfarm.farm.dto.ticket.TicketListRes;
import com.commitfarm.farm.dto.ticket.UpdateTicketReq;
import com.commitfarm.farm.service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class TicketController {

    @Autowired
    private TicketService ticketService;

    // 모든 프로젝트 중 하나 프로젝트 클릭하면 해당 프로젝트의 모든 티켓 조회 API
    @GetMapping("/api/projects/{projectId}/tickets")
    public ResponseEntity<TicketListRes> getProjectTickets(@PathVariable Long projectId) {
        try {
            TicketListRes ticketListRes = ticketService.getProjectTickets(projectId);
            return ResponseEntity.ok(ticketListRes);
        } catch (Exception e) {
            return ResponseEntity.status(400).body(null);
        }
    }


    // 티켓 생성 API
    @PostMapping("/api/projects/{projectId}/tickets/{memberId}")
    public ResponseEntity<String> createTicket(@PathVariable Long projectId, @PathVariable Long memberId, @RequestBody CreateTicketReq createTicketReq) {
        try {
            ticketService.createTicket(projectId, memberId, createTicketReq);
            return ResponseEntity.ok("티켓이 생성되었습니다.");
        } catch (Exception e) {
            return ResponseEntity.status(400).body(e.getMessage());
        }
    }

    // 티켓 삭제 API
    @DeleteMapping("/api/tickets/{ticketId}")
    public ResponseEntity<String> deleteTicket(@PathVariable Long ticketId) {
        try {
            ticketService.deleteTicket(ticketId);
            return ResponseEntity.ok("티켓이 삭제되었습니다.");
        } catch (Exception e) {
            return ResponseEntity.status(400).body(e.getMessage());
        }
    }

    //티켓 수정 API
    @PutMapping("/api/tickets/{ticketId}")
    public ResponseEntity<String> updateTicket(@PathVariable Long ticketId, @RequestBody UpdateTicketReq updateTicketReq) {
        try {
            ticketService.updateTicket(ticketId, updateTicketReq);
            return ResponseEntity.ok("티켓이 수정되었습니다.");
        } catch (Exception e) {
            return ResponseEntity.status(400).body(e.getMessage());
        }
    }

}
