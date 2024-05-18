package com.commitfarm.farm.domain;


import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "Milestone")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Milestone {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long milestoneId;

    private String name;

    private LocalDate startDate;

    private LocalDate dueDate;

    @Column(columnDefinition = "TEXT")
    private String description;

    @OneToMany(mappedBy = "milestone")
    private List<Ticket> tickets;
}
