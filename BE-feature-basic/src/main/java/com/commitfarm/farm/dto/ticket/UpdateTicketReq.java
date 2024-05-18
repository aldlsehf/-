package com.commitfarm.farm.dto.ticket;

import com.commitfarm.farm.domain.enumClass.Component;
import com.commitfarm.farm.domain.enumClass.Priority;
import com.commitfarm.farm.domain.enumClass.Status;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class UpdateTicketReq {

    @Schema(description = "유저 ID")
    private Long userId;

    @Schema(description = "티켓 제목")
    private String title;

    @Schema(description = "티켓 설명")
    private String description;

    @Schema(description = "티켓 상태")
    private Status status;

    @Schema(description = "티켓 우선순위")
    private Priority priority;

    @Schema(description = "티켓 컴포넌트")
    private Component component;
}