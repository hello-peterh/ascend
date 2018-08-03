package com.peterhung.ascend_membership.Controller;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.peterhung.ascend_membership.Entity.Member;
import com.peterhung.ascend_membership.Exceptions.MemberNotFoundException;
import com.peterhung.ascend_membership.MemberService.MemberService;
import com.peterhung.ascend_membership.persistence.MemberRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
//@RequestMapping("/home")
public class MemberController {

    @Autowired
    private MemberService memberService;
    
    @Autowired
    private MemberRepository memberRepo;

    @RequestMapping(method = RequestMethod.GET, value = "/members" )
    public Collection<Member> getAllMembers() {
    	
        return memberService.getAllMembers();
        
    }
    
    @RequestMapping(method = RequestMethod.GET, value = "/member/{id}")
    public Optional<Member> getMemberById(@PathVariable("id") long id) {
    	    	
    	if(!memberService.getMemberById(id).isPresent())
    		throw new MemberNotFoundException("id-" + id + " not found");
    	
        return memberService.getMemberById(id);
    }
    
    @RequestMapping(method = RequestMethod.GET, value = "/fillDatabase")
    public Collection<Member> fillDb() {
    	
    	List<Member> memberList = new ArrayList<Member>();
    	
    	//memberList.add(new Member(1L, "Elon", "Musk", "123 Test St", "Caucasian", new String[]{"Programming","Computer Science"}));
    	//memberList.add(new Member (2L, "Steve", "Jobs", "123 Test St", "Caucasian", new String[]{"Design","Computers"}));
    	//memberList.add(new Member (3L, "Mark", "Zucc", "123 Test St", "Caucasian", new String[]{"Data Science","Data"}));

    	memberService.addAll(memberList);
    	
        return memberService.getAllMembers();
    }
    
    @RequestMapping(method = RequestMethod.DELETE, value = "member/{id}")
    public String removeMemberById(@PathVariable("id") long id) {
        memberService.removeMemberById(id);
        
        String response = "Successfully deleted member";
        return response;
    }

    @RequestMapping(method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE, value = "member/{id}")
    public Member updateMember(@RequestBody Member member, @PathVariable("id") long id) {
    	log.info("Member data: " + member);
        memberService.updateMember(member, id);
        
        return member; 
    }

    @RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, value = "/member")
    public Member insertMember(@RequestBody Member member) {
    	
    	
    	log.info("Saving member: " + member.toString());
    	
        memberService.insertMember(member);
        return member;
    }
    
    @RequestMapping(value = "member/add", method = RequestMethod.POST)
	public ResponseEntity<?> addMember(@RequestBody Member member) {
		return new ResponseEntity<>(memberRepo.save(member), HttpStatus.CREATED);
	}
    
    @RequestMapping("/events")
    public String event(@RequestParam(value="filter", defaultValue="tech") String filter) {
    	
		String url = "http://api.meetup.com/find/upcoming_events?key=5f10b5d1e4a1a1a267240361d54626&fields=tech"; //" + filter;
		
	    	// sending GET request to meetup API
		RestTemplateBuilder restTemplateBuilder = new RestTemplateBuilder();
		restTemplateBuilder.setConnectTimeout(100000);
		restTemplateBuilder.setReadTimeout(100000);
		restTemplateBuilder.rootUri(url);
	    	
	    	
//	    	ResponseEntity<List<Events>> json = restTemplate.exchange(
//	    			url, 
//	    			HttpMethod.GET,
//	    			null,
//	    			new ParameterizedTypeReference<List<Events>>() {});
	    	
	    	ResponseEntity<String> json = restTemplateBuilder.build().getForEntity(url, String.class);
	
    	//deserializing from string into EventsList object
//    	ObjectMapper objectMapper = new ObjectMapper();
//    	try {
//    		Events[] event = objectMapper.readValue(json.getBody(), Events[].class);
//    		return event;
//    	} catch (IOException e) {
//    		System.out.println("IOException");
//    		return null;
//    	}
    	
    		return json.getBody();
    }

}