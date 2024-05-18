package com.commitfarm.farm.dto.ticket;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class TicketListRes {

    @Schema(description = "프로젝트 클릭 -> 모든 티켓 리스트 보여줌")
    private List<TicketRes> ticketList;
}
