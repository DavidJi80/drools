package com.benetech.demo.drools.service.impl;

import com.benetech.demo.drools.service.PropagationModeService;
import lombok.extern.slf4j.Slf4j;
import org.drools.core.base.RuleNameEndsWithAgendaFilter;
import org.kie.api.cdi.KSession;
import org.kie.api.runtime.KieSession;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author 季舟
 * @copyright 上海智赢健康科技有限公司
 * @since 2021/2/2 10:21
 */
@Slf4j
@Service
public class PropagationModeServiceImpl implements PropagationModeService {
    @Resource
    @KSession("ksession-rules")
    private KieSession ksession;

    @Override
    public void testLazyMode() {
        ksession.insert("3");
        ksession.insert(3);
        ksession.insert("55");
        ksession.insert(55);
//        ksession.fireAllRules(new RuleNameEndsWithAgendaFilter( "Test" ));
        ksession.fireAllRules();
    }

}
