package com.benetech.demo.drools.controller;

import com.benetech.demo.drools.service.QueryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 季舟
 * @copyright 上海智赢健康科技有限公司
 * @since 2021/2/1 11:37
 */
@RestController
@RequestMapping("/query")
public class QueryController {
    @Autowired
    private QueryService queryService;

    @GetMapping("/test")
    public String testQuery(){
        queryService.testQuery();
        return "success";
    }

    @GetMapping("/test_live")
    public String testLiveQuery(){
        queryService.testLiveQuery();
        return "testLiveQuery";
    }
}
