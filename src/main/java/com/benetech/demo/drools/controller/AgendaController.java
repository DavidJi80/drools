package com.benetech.demo.drools.controller;

import com.benetech.demo.drools.service.AgendaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 季舟
 * @copyright 上海智赢健康科技有限公司
 * @since 2021/2/1 18:07
 */
@RestController
@RequestMapping("/agenda")
public class AgendaController {
    @Autowired
    private AgendaService agendaService;

    @GetMapping("/test")
    public String testAgenda(){
        agendaService.testAgenda();
        return "Test Agenda";
    }
}
