package com.benetech.demo.drools.service;

import com.benetech.demo.drools.domain.Applicant;

public interface DroolsService {
    String fireRule();
    Applicant verifyApplicantAge();
    void verifyDriverApplication();
    void activatingSprinklerAlarm();
}
