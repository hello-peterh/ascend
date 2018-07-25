package com.peterhung.ascend_membership.Entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Member {

	@Id
	@GeneratedValue
	@Column(name = "member_id", nullable = true, unique = true)
    private Long id;
	@Column(name = "first_name")
    private String firstName;
	@Column(name = "last_name")
    private String lastName;
	@Column(name = "address")
    private String address;
	@Column(name = "ethnicity")
    private String ethnicity;
	@Column(name = "interests")
    private String[] interests;

    
}