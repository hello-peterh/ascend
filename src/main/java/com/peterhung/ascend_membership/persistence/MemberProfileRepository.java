package com.peterhung.ascend_membership.persistence;

import org.springframework.data.repository.CrudRepository;

import com.peterhung.ascend_membership.Entity.MemberProfile;

public interface MemberProfileRepository extends CrudRepository<MemberProfile, Long> {

}
