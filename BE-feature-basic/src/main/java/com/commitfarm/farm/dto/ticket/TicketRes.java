package com.commitfarm.farm.dto.ticket;

import com.commitfarm.farm.domain.enumClass.Status;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Date;

//Ticket in TicketList
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class SimpleTicketRes {
    @Schema(description = "티켓 제목")
    private String title;

    @Schema(description = "티켓 마감일")
    private Date endDate;

    @Schema(description = "티켓 상태")
    private Status status;
}
