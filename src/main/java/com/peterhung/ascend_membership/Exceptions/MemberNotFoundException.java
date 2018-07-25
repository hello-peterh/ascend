package com.peterhung.ascend_membership.Exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/*
@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "No such member")
public class MemberNotFoundException extends RuntimeException {

    private static final long serialVersionUID = -8790211652911971729L;

    public MemberNotFoundException(String memberId) {
        super(memberId + "not found");
    }
}
*/
@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "No such member")
public class MemberNotFoundException extends RuntimeException {
	
	public MemberNotFoundException(String exception) {
		super(exception);
	}
}