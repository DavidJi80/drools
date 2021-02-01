package com.benetech.demo.drools.service.impl;

import com.benetech.demo.drools.domain.bank.Account;
import com.benetech.demo.drools.domain.bank.AccountPeriod;
import com.benetech.demo.drools.domain.bank.CashFlow;
import com.benetech.demo.drools.service.AgendaService;
import lombok.extern.slf4j.Slf4j;
import org.kie.api.cdi.KSession;
import org.kie.api.runtime.KieSession;
import org.kie.api.runtime.rule.Agenda;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * @author 季舟
 * @copyright 上海智赢健康科技有限公司
 * @since 2021/2/1 17:49
 */
@Slf4j
@Service
public class AgendaServiceImpl implements AgendaService {

    @Resource
    @KSession("ksession-rules")
    private KieSession ksession;

    @Override
    public void testAgenda() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            Date periodStart = sdf.parse("2020-01-01 00:00:00");

            Date periodEnd=sdf.parse("2020-21-31 23:59:59");
            AccountPeriod accountPeriod=new AccountPeriod(periodStart,periodEnd);

            Account account=new Account("001",1000);

            Date cashFlowDate=sdf.parse("2020-03-31 23:19:59");
            CashFlow cashFlow=new CashFlow("001",cashFlowDate,100);

            ksession.insert(accountPeriod);
            ksession.insert(account);
            ksession.insert(cashFlow);

            Agenda agenda = ksession.getAgenda();
            agenda.getAgendaGroup( "report" ).setFocus();
            agenda.getAgendaGroup( "calculation" ).setFocus();
            ksession.fireAllRules();
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }
}
