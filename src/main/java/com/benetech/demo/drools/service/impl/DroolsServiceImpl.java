package com.benetech.demo.drools.service.impl;

import com.benetech.demo.drools.domain.Applicant;
import com.benetech.demo.drools.domain.Message;
import com.benetech.demo.drools.domain.driver.Application;
import com.benetech.demo.drools.domain.driver.DriverApplicant;
import com.benetech.demo.drools.domain.firealarm.Fire;
import com.benetech.demo.drools.domain.firealarm.Room;
import com.benetech.demo.drools.domain.firealarm.Sprinkler;
import com.benetech.demo.drools.service.DroolsService;
import org.kie.api.KieServices;
import org.kie.api.command.Command;
import org.kie.api.runtime.ExecutionResults;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.kie.api.runtime.StatelessKieSession;
import org.kie.api.runtime.rule.FactHandle;
import org.kie.internal.command.CommandFactory;
import org.springframework.stereotype.Service;

import java.util.*;

@Service("droolsService")
public class DroolsServiceImpl implements DroolsService{

    public String fireRule() {
        // load up the knowledge base
        KieServices ks = KieServices.Factory.get();
        KieContainer kContainer = ks.getKieClasspathContainer();
        KieSession kSession = kContainer.newKieSession("ksession-rules");

        // go !
        Message message = new Message();
        message.setMessage("Good Bye");
        message.setStatus(Message.HELLO);
        kSession.insert(message);//插入
        int ruleFiredCount = kSession.fireAllRules();//执行规则
        System.out.println("触发了" + ruleFiredCount + "条规则");
        kSession.dispose();
        return message.getMessage();
    }

    public Applicant verifyApplicantAge(){
        // 创建KIE容器
        KieServices kieServices = KieServices.Factory.get();
        KieContainer kContainer = kieServices.getKieClasspathContainer();

        Applicant applicant=new Applicant();
        applicant.setAge(16);
        applicant.setName("David Ji");
        applicant.setValid(true);

        StatelessKieSession kSession = kContainer.newStatelessKieSession("stateless-rules");
        kSession.execute(applicant);

        /*
        KieSession kSession = kContainer.newKieSession("ksession-rules");
        kSession.insert(applicant);//插入
        int ruleFiredCount = kSession.fireAllRules();//执行规则
        System.out.println("触发了" + ruleFiredCount + "条规则");
        kSession.dispose();
        */
        return applicant;
    }

    public void verifyDriverApplication(){
        KieServices kieServices = KieServices.Factory.get();
        KieContainer kContainer = kieServices.getKieClasspathContainer();
        StatelessKieSession ksession = kContainer.newStatelessKieSession("stateless-rules");


        DriverApplicant applicant   = new DriverApplicant();
        applicant.setName("Smith");
        applicant.setAge(16);
        Application application = new Application();

        System.out.println(applicant.toString()+" "+application.toString());

        //1.
        //ksession.execute(Arrays.asList(new Object[] { application, applicant }));

        //2.
        //ksession.execute(CommandFactory.newInsertIterable(new Object[] { application, applicant }));


        //3.
        List<Command> cmds = new ArrayList<Command>();
        cmds.add(CommandFactory.newInsert(applicant, "Driver Applicant"));
        cmds.add(CommandFactory.newInsert(application, "Application"));
        ExecutionResults results=ksession.execute(CommandFactory.newBatchExecution(cmds));

        System.out.println(applicant.toString()+" "+application.toString());
    }

    public void activatingSprinklerAlarm(){
        // 创建KIE容器
        KieServices kieServices = KieServices.Factory.get();
        KieContainer kContainer = kieServices.getKieClasspathContainer();
        // 创建有状态会话
        KieSession kSession = kContainer.newKieSession("ksession-rules");

        // 1. 创建房间和警报器
        String[] names = new String[]{"kitchen", "bedroom", "office", "livingroom"};
        Map<String, Room> name2room = new HashMap<String,Room>();
        for( String name: names ){
            Room room = new Room(name);
            name2room.put( name, room );
            kSession.insert( room );
            Sprinkler sprinkler = new Sprinkler(room);
            kSession.insert( sprinkler );
        }

        int ruleFiredCount = kSession.fireAllRules();//执行规则
        System.out.println("1. 触发了" + ruleFiredCount + "条规则");

        // 2. 创建2个火情
        Fire kitchenFire = new Fire( name2room.get( "kitchen" ) );
        Fire officeFire = new Fire( name2room.get( "office" ) );
        FactHandle kitchenFireHandle = kSession.insert( kitchenFire );
        FactHandle officeFireHandle = kSession.insert( officeFire );

        ruleFiredCount = kSession.fireAllRules();//执行规则
        System.out.println("2. 触发了" + ruleFiredCount + "条规则");

        //3. 撤销了2处火情
        kSession.delete( kitchenFireHandle );
        kSession.delete( officeFireHandle );

        ruleFiredCount = kSession.fireAllRules();//执行规则
        System.out.println("3. 触发了" + ruleFiredCount + "条规则");

        kSession.dispose();
    }

}
