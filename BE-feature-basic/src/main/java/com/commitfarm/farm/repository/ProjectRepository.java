package com.commitfarm.farm.repository;

import com.commitfarm.farm.domain.Project;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProjectRepository extends JpaRepository<Project, Long> {
}
