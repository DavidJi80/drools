package com.benetech.demo.drools.controller;

import com.benetech.demo.drools.service.SalienceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 季舟
 * @copyright 上海智赢健康科技有限公司
 * @since 2021/2/1 16:39
 */
@RestController
@RequestMapping("/salience")
public class SalienceController {
    @Autowired
    private SalienceService salienceService;

    @GetMapping("/test")
    public String testSalience(){
        salienceService.testSalience();
        return "Test Salience";
    }
}
