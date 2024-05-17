package com.commitfarm.farm.repository;

import com.commitfarm.farm.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Long> {



}
