package com.peterhung.ascend_membership.Exceptions;

import org.slf4j.LoggerFactory;
import org.slf4j.Logger;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping
public class ExceptionHandlingController {
    
    protected Logger logger;

    public ExceptionHandlingController() {
        logger = LoggerFactory.getLogger(getClass());
    }

    @RequestMapping("/memberNotFound")
    String throwMemberNotFoundException() {
        logger.info("Throw MemberNotFoundException for unknown member");
        throw new MemberNotFoundException("12345");
    }
}