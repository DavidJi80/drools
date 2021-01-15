package com.benetech.demo.drools.controller;

import com.benetech.demo.drools.domain.Applicant;
import com.benetech.demo.drools.service.DroolsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
public class TestController {
    @Autowired
    private DroolsService droolsService;

    @GetMapping("/showRults")
    public String showRults(){
        return droolsService.fireRule();
    }

    @GetMapping("/testBoot")
    public String testBoot(){
        return "123456";
    }

    @GetMapping("/verifyApplicantAge")
    public String verifyApplicantAge(){
        Applicant applicant=droolsService.verifyApplicantAge();
        return applicant.toString();
    }

    @GetMapping("/verifyDriverApplication")
    public String verifyDriverApplication(){
        droolsService.verifyDriverApplication();
        return "success";
    }

    @GetMapping("/activatingSprinklerAlarm")
    public String activatingSprinklerAlarm(){
        droolsService.activatingSprinklerAlarm();
        return "success";
    }

}
