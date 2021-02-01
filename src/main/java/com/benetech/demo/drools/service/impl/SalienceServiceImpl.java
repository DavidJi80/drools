package com.benetech.demo.drools.service.impl;

import com.benetech.demo.drools.domain.testquery.Person;
import com.benetech.demo.drools.service.SalienceService;
import lombok.extern.slf4j.Slf4j;
import org.kie.api.cdi.KSession;
import org.kie.api.runtime.KieSession;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author 季舟
 * @copyright 上海智赢健康科技有限公司
 * @since 2021/2/1 16:37
 */
@Slf4j
@Service
public class SalienceServiceImpl implements SalienceService {
    @Resource
    @KSession("ksession-rules")
    private KieSession ksession;

    @Override
    public void testSalience() {
        Person person=new Person("Tom",18);
        ksession.insert(person);
        ksession.fireAllRules();
    }
}
