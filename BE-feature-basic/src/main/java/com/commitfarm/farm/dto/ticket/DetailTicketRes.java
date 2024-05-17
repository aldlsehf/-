package com.commitfarm.farm.dto.ticket;

import com.commitfarm.farm.domain.Milestone;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class DetailTicketRes {

    @Schema(description = "티켓 클릭 -> 마일스톤 보여줌")
    private List<Milestone> milestoneList;
}
