package com.peterhung.ascend_membership.persistence;

import com.peterhung.ascend_membership.Entity.Member;
//
//import java.util.Arrays;
//import java.util.Collection;
//
//import org.springframework.stereotype.Repository;
import org.springframework.data.repository.CrudRepository;

public interface MemberRepository extends CrudRepository<Member, Long> {}