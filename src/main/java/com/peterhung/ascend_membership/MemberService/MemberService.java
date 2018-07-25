package com.peterhung.ascend_membership.MemberService;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.peterhung.ascend_membership.Entity.Member;
import com.peterhung.ascend_membership.persistence.MemberRepository;

@Service
public class MemberService{

    @Autowired
    private MemberRepository memberRepo;

    public Collection<Member> getAllMembers() {
    	
    	Iterable<Member> repoList = memberRepo.findAll();
    	
    	List<Member> memberList = new ArrayList<Member>();
    	repoList.forEach(memberList::add);
    	
        return memberList;
    }

    public Optional<Member> getMemberById(long id) {
    	//try:
    	return this.memberRepo.findById(id);
    	//catch:
    }

    public void removeMemberById(long id) {
    	this.memberRepo.deleteById(id);
    }

    public void updateMember(Member member, long id) {
    	member.setId(id);
    	this.memberRepo.save(member);
    }

    public void insertMember(Member member){
    	this.memberRepo.save(member);
    	//this.memberRepo.save(member.setInterests(convertToString(member.getInterests())); //doesn't work because it is a string[]
    	
    }

	public void addAll(List<Member> memberList) {
		this.memberRepo.saveAll(memberList);
		
	}
	
	public String convertToString(String[] interests) {
		
		String str = Arrays.toString(interests);
		
		return str;
	}

}