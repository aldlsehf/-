package com.commitfarm.farm.dto.ticket;

import com.commitfarm.farm.domain.enumClass.Status;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Date;

//Ticket in TicketList
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class SimpleTicketRes {
    private String title;
    private Date endDate;
    private Status status;
}
