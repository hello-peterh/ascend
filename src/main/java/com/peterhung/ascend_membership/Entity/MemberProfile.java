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
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonBackReference;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "member_profile")
public class MemberProfile implements Serializable {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="member_id", nullable = true)
	private Long id;
	
	//@NotNull
	@Size(max = 65)
	@Column(name="first_name")
	private String firstName;
	
	//@NotNull
	@Size(max=65)
	@Column(name="last_name")
	private String lastName;
	
	//@Temporal(TemporalType.DATE)
	@Column(name = "dob")
	//@JsonFormat(pattern = "YYYY-MM-dd")
	private String dateOfBirth;
	
	@Column(name="primary_location")
	private String primaryLocation;
	
	//@NotNull
	//@Column(name="phone")
	@Size(min=10, max=10)
	private String phone;
	
	//@Size(max=20)
	//@Column(name="ethnicity")
	private String ethnicity;
	
	//@Column(name="student_status")
	@Size(max=20)
	private String studentStatus;
	
	//@Column(name="gender")
	@Size(max=20)
	private String gender;
	
	//@Column(name="school")
	@Size(max=100)
	private String school;
	
	//@Size(min=4, max=4)
	//@Column(name="graduation_year")
	private String graduationYear;
	
	
	//@Column(name="job_title")
	private String jobTitle;
	
	//@Column(name="company")
	private String company;
	
	//@Column(name="industry")
	private String industry;
	
	//this is to join member
	@OneToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "member_id", nullable = false)
	@JsonBackReference
	private Member member;
	
	//this allows interest to join with member profile
	//THIS IS WHERE THE PROBLEM IS COMING FROM
	@OneToMany(fetch = FetchType.LAZY,
			//cascade = CascadeType.ALL,
			mappedBy = "memberProfile")
	private Set<Interest> interest;// = new HashSet<Interest>();
	
}
