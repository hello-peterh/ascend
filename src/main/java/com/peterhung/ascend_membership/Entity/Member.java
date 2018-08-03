package com.peterhung.ascend_membership.Entity;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor //hiberate requires a noargs constructor
@Table(name = "members")
public class Member implements Serializable{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "member_id")
    private Long id;
	
	@NotNull
	@Email
	@Size(max = 100)
	@Column(unique = true)
	private String email;
	
	@NotNull
	@Size(max = 100)
	private String password;
	
	
	//this  allows connection from memberProfile
	@OneToOne(fetch = FetchType.LAZY,
			cascade = CascadeType.ALL,
			mappedBy = "member")
	private  MemberProfile memberProfile;
    
}