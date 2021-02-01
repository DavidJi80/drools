package com.benetech.demo.drools.service.impl;

import com.benetech.demo.drools.domain.testquery.Person;
import com.benetech.demo.drools.domain.testquery.TShirt;
import com.benetech.demo.drools.service.QueryService;
import lombok.extern.slf4j.Slf4j;
import org.kie.api.cdi.KSession;
import org.kie.api.runtime.KieSession;
import org.kie.api.runtime.rule.*;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @author 季舟
 * @copyright 上海智赢健康科技有限公司
 * @since 2021/2/1 10:35
 */
@Slf4j
@Service
public class QueryServiceImpl implements QueryService {

    @Resource
    @KSession("ksession-rules")
    private KieSession ksession;

    @Override
    public void testQuery() {
        Person person1=new Person("Tom",9);
        Person person2=new Person("Jack",19);
        ksession.insert(person1);
        ksession.insert(person2);

        QueryResults results = ksession.getQueryResults( "people under the age of 21" );
        System.out.println( "we have " + results.size() + " people under the age of 21" );
        for (String identifier :results.getIdentifiers()){
            System.out.println( "Identifier : " + identifier  );
        }

        System.out.println( "These people are under the age of 21:" );

        for ( QueryResultsRow row : results ) {
            Object obj=row.get( "$person21" );
            if (obj instanceof Person){
                Person person = (Person) row.get( "$person21" );
                System.out.println( person );
            }
        }
    }

    @Override
    public void testLiveQuery() {
        final List updated = new ArrayList();
        final List removed = new ArrayList();
        final List added = new ArrayList();

        ViewChangedEventListener listener = new ViewChangedEventListener() {
            @Override
            public void rowInserted(Row row) {
                added.add( row.get( "$price" ) );
                log.info("rowInserted");
            }

            @Override
            public void rowDeleted(Row row) {
                removed.add( row.get( "$price" ) );
                log.info("rowDeleted");
            }

            public void rowUpdated(Row row) {
                updated.add( row.get( "$price" ) );
                log.info("rowUpdated");
            }
        };

        // Open the live query:
        LiveQuery query = ksession.openLiveQuery( "colors",
                new Object[] { "red", "blue" },
                listener );
        ksession.insert(new TShirt("red","blue",50));

        // Terminate the live query:
//        query.close();

    }
}
